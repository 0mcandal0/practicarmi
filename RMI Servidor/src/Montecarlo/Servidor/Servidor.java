package Montecarlo.Servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Clase donde se arranca el servidor
//IP ser� dado por una propiedad del sistema durante el arranque del servidor
//ServidorID tambi�n ser� dado al iniciar el arranque y coincide con la posici�n
//del servidor en la lista de servidores del cliente como del monitor
public class Servidor {
	public static String Ip = "";
	public static int ServidorID=0;
	public static void main(String args[]) {

		String registryURL;

		try {
			//Arranca el registro RMI en el puerto 1099 si este no est� activo
			startRegistry(1099);
			
			ServidorID = Integer.parseInt(System.getProperty("ServidorID"));
			
			Ip = System.getProperty("java.rmi.server.hostname");
			System.out.println("IP:" + Ip + ": Servidor (" + ServidorID + ")");

			ImplServidor obj = new ImplServidor();

			registryURL = "rmi://" + Ip + ":1099/montecarlo";

			//Registra la implementaci�n del servidor (montecarlo) en el registro
			Naming.rebind(registryURL, obj);
			System.out.println("Listo el servidor de C�lculo de Pi por el M�todo Montecarlo.");
			
		} catch (Exception re) {
			System.out.println("Exception en main: " + re);
		}
	}

	//Funci�n de control del inicio del registro en un puerto dado
	private static void startRegistry(int RMIPortNum) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
			registry.list();
		} catch (RemoteException e) {
			LocateRegistry.createRegistry(RMIPortNum);
		}
	}

}
