package Montecarlo.Cliente;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import Montecarlo.Cliente.GUI.ClienteGUI;
import Montecarlo.Servidor.IServidorCliente;

public class Cliente {

	//Servidores habilitados para el c�lculo.
	public static String URLS[] = { "rmi://192.168.0.11:1099/montecarlo",
									"rmi://192.168.0.12:1099/montecarlo", 
									"rmi://192.168.0.13:1099/montecarlo",
									"rmi://mcandal.dyndns.org:1099/montecarlo" };
	
	//Se guardan los objectos de conexiones a cada uno de los servidores en un HashMap
	//el n�mero entero indica el id de servidor
	private static HashMap<Integer, IServidorCliente> hmServidores = new HashMap<Integer, IServidorCliente>();
	
	
	//Variable con el n�mero de pares de puntos enviados
	private static long numpares=0;
	
	//Vector de resultados devueltos por los servidores
	private static Vector<Long> resultados = new Vector<Long>();
	
	//Variable est�tica que guarda la implementaci�n del cliente que ver� el servidor
	private static ICliente c;
	
	//Funci�n de conexi�n a los servidores
	public static void conectarServers() {
		try {
			c = new ImplCliente();
			
			//Bucle por la lista de servidores intentando encontrar el servicio
			for(int i = 0; i<4 ; i++)
			{
				IServidorCliente s = null;
				ClienteGUI.InsertarLog("Buscando servicio Montecarlo en: " + URLS[i]);
				try {
					
					//B�squeda del servicio montecarlo indicado en la direcciones URLS dadas
					s = (IServidorCliente) Naming.lookup(URLS[i]);
					
					ClienteGUI.InsertarLog("B�squeda completada");
					
					//Registro del cliente en el servidor a trav�s de la funci�n registrarCliente del servidor
					s.registrarCliente(c);
					
					ClienteGUI.InsertarLog("Registrado el callback del cliente en: " + URLS[i]);
					ClienteGUI.ActualizarDatosServidor(i+1,URLS[i]);
					//Guardado del �ndice de servidor y la conexi�n al servidor
					hmServidores.put(i+1, s);
				} catch (ServerException se) {
					ClienteGUI.InsertarLog("Error de servidor: " + se);
				} catch (ConnectException ee) {
					ClienteGUI.InsertarLog("Error de conexi�n: " + ee);
				} catch (Exception e) {
					ClienteGUI.InsertarLog("Excepci�n: " + e);
				}
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	//Funci�n de desconexi�n de los servidores
	public static void desconectarServers() {
		try {
			//Se recorren los servidores y se invoca al m�todo de deregistrarCliente del servidor 
			for(IServidorCliente s : hmServidores.values()) {
				s.deregistrarCliente(c);
			}
		} catch (RemoteException e) {
			ClienteGUI.InsertarLog("Error al deregistrar: " + e.toString());
		}
	}
	
	
	//Funci�n para el env�o del n�mero de pares de puntos a calcular
	@SuppressWarnings("unchecked")
	public static void enviar(long puntos) {
		try {
			if(hmServidores.size()>0) {
				numpares = puntos;
				resultados.clear();
				
				ClienteGUI.Bloquear(true);
				ClienteGUI.InsertarLog("Calculando: " + String.valueOf(puntos) + " puntos");
				ClienteGUI.ActualizarResultadoPI("Calculando...");

				
				//El n�mero de pares se reparten entre los servidores disponibles
				int numservidores = hmServidores.size();
				long p = (long) (puntos/numservidores);

				//Recorrido sobre el HashMap de servidores disponibles
				//para el env�o del n�mero de pares
				Set set = hmServidores.entrySet();
				Iterator iter = set.iterator();
				while(iter.hasNext()) {
					Map.Entry<Integer, IServidorCliente> o = (Map.Entry<Integer, IServidorCliente>)iter.next();
					IServidorCliente s = o.getValue();
					int numservidor= o.getKey();

					ClienteGUI.InsertarLog("Enviando puntos a " + URLS[numservidor-1]);
					ClienteGUI.ActualizarDatosServidor(numservidor, URLS[numservidor-1] + " (Calculando)");
					ClienteGUI.ActualizarResultadosServidor(numservidor,0);

					//Env�o de puntos al servidor
					if(!iter.hasNext() && numservidores*p<puntos)
						s.puntos(c,p + puntos%numservidores);
					else
						s.puntos(c,p);
					
				}
			}
			else
				ClienteGUI.InsertarLog("No hay servidores a donde enviar los puntos");
			
		} catch (RemoteException e) {
			ClienteGUI.InsertarLog("Error al enviar puntos: " + e.toString());
		} catch (Exception e) {
			ClienteGUI.InsertarLog("Error en el env�o de puntos: " + e.toString());
		}
	}

	
	//Funci�n para el control de fin de c�lculo
	//S�lo calcula el valor de Pi cuando el �ltimo servidor
	//devuelve el �ltimo resultado
	public static void findecalculo(int numservidor, long numeropares,
			long resultado) {
		
		ClienteGUI.InsertarLog("Resultado del servidor (" + String.valueOf(numservidor) + ") : Pares "  
				+ String.valueOf(numeropares) + " Resultado "+ String.valueOf(resultado));
		ClienteGUI.ActualizarResultadosServidor(numservidor, resultado);
		ClienteGUI.ActualizarDatosServidor(numservidor, Cliente.URLS[numservidor-1]);

		resultados.add(resultado);
		//En el caso que el n�mero de resultados coincida con el n�mero de servidores activos
		//Se termina el c�lculo
		if(resultados.size()==hmServidores.size())
		{
			long res=0;
			for(Long l : resultados) {
				res+=l;
			}
			ClienteGUI.Bloquear(false);
			ClienteGUI.ActualizarResultadoPI(String.valueOf(  ( (double)res/(double)numpares )*4.0f));
				
		}
		
	}
	


}
