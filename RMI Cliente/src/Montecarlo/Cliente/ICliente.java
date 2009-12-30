package Montecarlo.Cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Interfaz remota que será ejecutada en el servidor (Callback)
public interface ICliente extends Remote {
	public void notificar(int numservidor, long numeropares, long resultado) throws RemoteException;
}

