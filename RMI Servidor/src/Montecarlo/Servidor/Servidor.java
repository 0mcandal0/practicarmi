package Montecarlo.Servidor;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Clase donde se arranca el servidor
//IP será dado por una propiedad del sistema durante el arranque del servidor
//ServidorID también será dado al iniciar el arranque y coincide con la posición
//del servidor en la lista de servidores del cliente como del monitor
public class Servidor {
	public static String Ip = "";
	public static int ServidorID=0;
	public static void main(String args[]) {

		String registryURL;

		try {
			//Arranca el registro RMI en el puerto 1099 si este no está activo
			startRegistry(1099);
			
			ServidorID = Integer.parseInt(System.getProperty("ServidorID"));
			
			Ip = System.getProperty("java.rmi.server.hostname");
			System.out.println("IP:" + Ip + ": Servidor (" + ServidorID + ")");

			ImplServidor obj = new ImplServidor();

			registryURL = "rmi://" + Ip + ":1099/montecarlo";

			//Registra la implementación del servidor (montecarlo) en el registro
			Naming.rebind(registryURL, obj);
			System.out.println("Listo el servidor de Cálculo de Pi por el Método Montecarlo.");
			
		} catch (Exception re) {
			System.out.println("Exception en main: " + re);
		}
	}

	//Función de control del inicio del registro en un puerto dado
	private static void startRegistry(int RMIPortNum) throws RemoteException {
		try {
			Registry registry = LocateRegistry.getRegistry(RMIPortNum);
			registry.list();
		} catch (RemoteException e) {
			LocateRegistry.createRegistry(RMIPortNum);
		}
	}

}
