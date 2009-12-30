package Montecarlo.Monitor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Montecarlo.Monitor.GUI.MonitorGUI;

@SuppressWarnings("serial")
public class ImplMonitor extends UnicastRemoteObject implements IMonitor {
	public ImplMonitor() throws RemoteException {
		super();
	}

	public void infoprocesos(int idservidor, long procesos) {
		MonitorGUI.InsertarLog("Mensaje del servidor " + idservidor);
		MonitorGUI.ActualizarServidor(idservidor, procesos);
	}
}
