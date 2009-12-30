package Montecarlo.Servidor;

import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

import Montecarlo.Cliente.ICliente;
import Montecarlo.Servidor.randomX.randomLEcuyer;
import Montecarlo.Servidor.randomX.randomX;

//Implementaci�n de la funci�n de c�lculo
//Esta clase es runnable para permitir crear threads independientes
public class ImplCalculo implements Runnable {
	
	private long numeropares=0;
	private ICliente cliente;
	
	//Esta variable servir� para matar el proceso
	//en el caso de que el cliente que inici� la petici�n
	//se deregistre
	private boolean matar=false;
	
	ImplCalculo(ICliente obj, long numeropares) {
		this.numeropares=numeropares;
		this.cliente=obj;
	}
	
	public ICliente getCliente() {
		return cliente;
	}
	
	public void setMatar(boolean m) {
		matar=m;
	}
	
	//Creaci�n del evento fin de c�lculo
	//En el momento que el thread termine el c�lculo
	@SuppressWarnings("serial")
	public class EventoFinCalculo extends EventObject {
		ICliente cliente;
		long numeropares;
		long contador;
		public EventoFinCalculo(Object source, ICliente obj, long numeropares, long contador) {
			super(source);
			this.cliente = obj;
			this.numeropares = numeropares;
			this.contador=contador;
		}
	}

	public interface EventoFinCalculoListener extends EventListener {
		public void onEventoFinCalculo(EventoFinCalculo evt);
	}
	
	private EventListenerList listeners = new EventListenerList(); 
	
	// register new listener
	public void addEventoFinCalculoListener(EventoFinCalculoListener l) {
		listeners.add(EventoFinCalculoListener.class, l);
	}
	// removes the listener
	public void removeEventoFinCalculoListener(EventoFinCalculoListener l) {
		listeners.remove(EventoFinCalculoListener.class, l);
	}
	
	// This private class is used to fire MyEvents
	void fireEventoFinCalculo(EventoFinCalculo evt) {
		Object[] list = listeners.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < list.length; i += 2) {
			if (list[i] == EventoFinCalculoListener.class) {
				((EventoFinCalculoListener) list[i + 1]).onEventoFinCalculo(evt);
			}
		}
	}

	//Funci�n con el c�lculo
	//Se utiliza un generador de n�meros aleatorios (randomX)
	public void run() {
		long contador=0;
		randomX r;
		r = new randomLEcuyer();
		try {
			for (long i = 0; i < numeropares; i++) {
				if(matar) return; //Salida forzada del c�lculo
				int x = r.nextByte() & 0xFF;
				int y = r.nextByte() & 0xFF;
				contador += ((Math.pow(x, 2.0) + Math.pow(y, 2.0) <= 65025.0) ? 1
						: 0);
			}
			System.out.println("PI calculado en el servidor con " + numeropares
					+ " pares : " + 4.0 * contador / numeropares);
		} catch (Exception ex) {
			System.out.println("Error puntos: " + ex);
		}
		finally {
			//Si el c�lculo termina, se env�a el evento a la clase ImplServidor
			if(!matar)
				fireEventoFinCalculo(new EventoFinCalculo(this, cliente, numeropares, contador));
		}
	}
}
