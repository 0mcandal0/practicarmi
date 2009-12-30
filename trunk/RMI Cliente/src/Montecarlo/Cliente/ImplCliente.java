package Montecarlo.Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//Implementaci�n del cliente que ser� utilizada desde el servidor
@SuppressWarnings("serial")
public class ImplCliente extends UnicastRemoteObject implements
		ICliente {

	public ImplCliente() throws RemoteException {
		super();
	}

	public void notificar(int numservidor, long numeropares, long resultado) {
		Cliente.findecalculo(numservidor,numeropares,resultado);
	}
}
