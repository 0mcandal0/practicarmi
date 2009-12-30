package Montecarlo.Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Montecarlo.Cliente.ICliente;

//Interface que verá el cliente para enviar comandos al server
public interface IServidorCliente extends Remote {
	
	public void puntos(ICliente obj, long i) throws RemoteException;
	
	// Funciones de registro y deregistro de callback del monitor
	public void deregistrarCliente(ICliente obj) throws RemoteException;
	public void registrarCliente(ICliente obj) throws RemoteException;
}
