package Montecarlo.Servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import Montecarlo.Cliente.ICliente;
import Montecarlo.Monitor.IMonitor;
import Montecarlo.Servidor.ImplCalculo.EventoFinCalculo;
import Montecarlo.Servidor.ImplCalculo.EventoFinCalculoListener;

//Clase principal con la implementación de los métodos de control sobre el cliente
//y sobre el monitor
//Implementa dos interfaces distintos, uno para el control del monitor y otro para
//el control de los clientes
@SuppressWarnings("serial")
public class ImplServidor extends UnicastRemoteObject implements IServidorMonitor,
		IServidorCliente {

	//Se guarda el monitor en una variable
	IMonitor monitor;
	
	//Los posibles clientes se guardan en un vector
	private Vector<ICliente> clientes  = new Vector<ICliente>();
	
	//Las llamadas a inicio de cálculo también se guardan en un vector
	
	private Vector<ImplCalculo> trabajos = new Vector<ImplCalculo>();

	public ImplServidor() throws RemoteException {
		super();
	}

	//Envío al monitor de información sobre los procesos activos en el servidor
	public synchronized void informarmonitor() throws RemoteException {
		if(monitor!=null)
			monitor.infoprocesos(Servidor.ServidorID, Thread.activeCount());
	}

	//Función principal de control de cálculo
	//En esta función se crea un thread independiente asociado a un cliente
	//Además se controla la finalización del cálculo
	public synchronized void puntos(ICliente obj, long i) throws RemoteException {
		System.out.println("Ejecución por threads");
		ImplCalculo sc = new ImplCalculo(obj,i);

		sc.addEventoFinCalculoListener( new EventoFinCalculoListener() {
			@Override
			public void onEventoFinCalculo(EventoFinCalculo evt) {
				ICliente c =evt.cliente;
				try {
					//Cuando termina se envía notificación al cliente y al monitor
					c.notificar(Servidor.ServidorID, evt.numeropares, evt.contador);
					informarmonitor();
				} catch (RemoteException e) {
					e.printStackTrace();
				} 
			}
			
		});
		trabajos.add(sc); //Se añade el cálculo a un vector de trabajos
		
		//Se crea el hilo y se arranca
		Thread t = new Thread(sc);
		t.setName("Calculo");
		t.start();
		informarmonitor();
	}

	//Función de deregistro del monitor
	public synchronized void deregistrarMonitor() throws RemoteException {
		if (monitor != null) {
			monitor = null;
			System.out.println("Monitor deregistrado");
		} else {
			System.out.println("deregistrarMonitor: el monitor no fue registrado.");
		}	
	}
	
	//Función de registro del monitor
	public synchronized void registrarMonitor(IMonitor obj) throws RemoteException {
		try {
			monitor = obj;
			System.out.println("Registrado monitor para callback " + obj.toString());
			
			informarmonitor();
		} catch (Exception e) {
			System.out.println("Error al registrar callback de monitor: " + e);
		}
	}

	//Función de deregistro del cliente
	public synchronized void deregistrarCliente(ICliente obj) throws RemoteException {
		if (clientes.remove(obj)) {
			cancelarhilos(obj); //Se cancelan los hilos de cálculo pertenecientes al cliente
			System.out.println("Cliente deregistrado");
			informarmonitor();
		} else {
			System.out.println("deregistrarCliente: el cliente no fue registrado.");
		}
	}

	//Función de registro de cliente
	public synchronized void registrarCliente(ICliente obj) throws RemoteException {
		try {
			if (!clientes.contains(obj))
				clientes.add(obj);
			System.out.println("Registrado cliente para callback " + obj.toString());
		} catch (Exception e) {
			System.out.println("Error al registrar callback de cliente: " + e);
		}
	}

	//Función de cancelación de hilos de un cliente
	private void cancelarhilos(ICliente obj) {
		//Se recorren los hijos de cálculo de un cliente y se establece
		//la variable de control matar a true		
		for(ImplCalculo o: trabajos) {
			if(obj.equals(o.getCliente())) {
				o.setMatar(true);
			}
			trabajos.remove(o);
		}
	}

}
