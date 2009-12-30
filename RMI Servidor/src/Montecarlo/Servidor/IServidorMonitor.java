package Montecarlo.Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Montecarlo.Monitor.IMonitor;


//Interfaz que verá el monitor para enviar comandos al servidor
public interface IServidorMonitor extends Remote {
	// Funciones de registro y deregistro de callback del monitor
	public void deregistrarMonitor() throws RemoteException;
	public void registrarMonitor(IMonitor obj) throws RemoteException;
}
