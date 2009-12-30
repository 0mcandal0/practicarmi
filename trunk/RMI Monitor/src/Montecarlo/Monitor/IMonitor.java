package Montecarlo.Monitor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMonitor extends Remote {
	public void infoprocesos(int idservidor, long procesos) throws RemoteException;
}
