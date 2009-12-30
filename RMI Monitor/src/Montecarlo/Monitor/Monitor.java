package Montecarlo.Monitor;

import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.ServerException;
import java.util.HashMap;

import Montecarlo.Monitor.GUI.MonitorGUI;
import Montecarlo.Servidor.IServidorMonitor;

public class Monitor {

	public static String URLS[] = { "rmi://192.168.0.11:1099/montecarlo",
									"rmi://192.168.0.12:1099/montecarlo", 
									"rmi://192.168.0.13:1099/montecarlo",
									"rmi://mcandal.dyndns.org:1099/montecarlo" };

	private static HashMap<Integer, IServidorMonitor> hmServidores = new HashMap<Integer, IServidorMonitor>();

	private static IMonitor m;
	
	public static void conectarServers() {
		try {
			m = new ImplMonitor();
			for(int i = 0; i<4 ; i++)
			{
				IServidorMonitor s = null;
				MonitorGUI.InsertarLog("Buscando servicio Montecarlo en: " + URLS[i]);
				try {
					s = (IServidorMonitor) Naming.lookup(URLS[i]);
					MonitorGUI.InsertarLog("Búsqueda completada");
					s.registrarMonitor(m);
					MonitorGUI.InsertarLog("Registrado el callback del monitor en: " + URLS[i]);
					MonitorGUI.ActualizarDatosServidor(i+1,URLS[i]);
					hmServidores.put(i+1, s);
				} catch (ServerException se) {
					MonitorGUI.InsertarLog("Error de servidor: " + se);
				} catch (ConnectException ee) {
					MonitorGUI.InsertarLog("Error de conexión: " + ee);
				} catch (Exception e) {
					MonitorGUI.InsertarLog("Excepción: " + e);
				}
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

	public static void desconectarServers() {
		try {
			for(IServidorMonitor s : hmServidores.values()) {
				s.deregistrarMonitor();
			}
		} catch (RemoteException e) {
			MonitorGUI.InsertarLog("Error al deregistrar: " + e.toString());
		}
	}
}
