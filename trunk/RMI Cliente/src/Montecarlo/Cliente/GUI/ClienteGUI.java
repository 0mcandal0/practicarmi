package Montecarlo.Cliente.GUI;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Montecarlo.Cliente.Cliente;
import Montecarlo.Cliente.GUI.ClientePanel.EventoClick;
import Montecarlo.Cliente.GUI.ClientePanel.EventoClickListener;

@SuppressWarnings("serial")
public class ClienteGUI extends JFrame {

	private static JPanel jPanel = null;
	private static ClienteLog clienteLog = null;
	private static ClientePanel clientePanel = null;
	
	/**
	 * This method initializes 
	 * 
	 */
	public ClienteGUI() {
		super();
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(600, 560));
        this.setMinimumSize(new Dimension(600, 600));
        this.setPreferredSize(new Dimension(600, 400));
        this.setContentPane(getJPanel());
        this.setTitle("Cliente Montecarlo");
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        	public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					Cliente.desconectarServers();
				} catch (Exception ex) {
				} finally {
					System.exit(0);
				}
        	}
        });
		
        ClienteGUI.clientePanel
			.addEventoClickListener(new EventoClickListener() {
				@Override
				public void onEventoClick(EventoClick evt) {
					ClienteGUI.Bloquear(true);
					Cliente.enviar(evt.pares); 
				}
			});
	}

	public static void Bloquear(boolean b) {
		clientePanel.BloquearEnvio(b);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getClientePanel(), null);
			jPanel.add(getClienteLog(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes clienteLog	
	 * 	
	 * @return Montecarlo.Cliente.GUI.ClienteLog	
	 */
	private ClienteLog getClienteLog() {
		if (clienteLog == null) {
			clienteLog = new ClienteLog();
		}
		return clienteLog;
	}
	
	public static void InsertarLog(String texto) {
		Calendar currentDate = Calendar.getInstance();
	    SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
		String dateNow = formatter.format(currentDate.getTime());
		clienteLog.InsertarElemento(dateNow + ": " + texto);
	}

	public static void ActualizarResultadosServidor(int nservidor, long resultado) {
		clientePanel.ActualizarResultados(nservidor, resultado);
	}
	
	public static void ActualizarDatosServidor(int nservidor, String datos) {
		clientePanel.ActualizarDatos(nservidor,datos);
	}
	
	public static void ActualizarResultadoPI(String pi) {
		clientePanel.ActualizarPI(pi);
	}
	
	
	/**
	 * This method initializes clientePanel	
	 * 	
	 * @return Montecarlo.Cliente.GUI.ClientePanel	
	 */
	private ClientePanel getClientePanel() {
		if (clientePanel == null) {
			clientePanel = new ClientePanel();
		}
		return clientePanel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClienteGUI app = new ClienteGUI();
		app.pack();
		app.setVisible(true);
		Bloquear(true);
		Cliente.conectarServers();
		Bloquear(false);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
