package Montecarlo.Monitor.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Montecarlo.Monitor.Monitor;


@SuppressWarnings("serial")
public class MonitorGUI extends JFrame {

	private static MonitorLog monitorLog = null;
	private static MonitorPanel monitorPanel = null;

	public static void ActualizarServidor(int nservidor, long procesos) {
		monitorPanel.ActualizarProcesos(nservidor, procesos);
	}
	
	public static void ActualizarDatosServidor(int nservidor, String datos) {
		monitorPanel.ActualizarDatos(nservidor,datos);
	}

	public static void InsertarLog(String texto) {
		monitorLog.InsertarElemento(texto);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MonitorGUI app = new MonitorGUI();
		app.pack();
		app.setVisible(true);
		Monitor.conectarServers();
	}

	private JPanel jPanel = null;

	/**
	 * This method initializes
	 * 
	 */
	public MonitorGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(2);
			gridLayout.setColumns(1);
			jPanel = new JPanel();
			jPanel.setLayout(gridLayout);
			jPanel.add(getMonitorPanel(), null);
			jPanel.add(getMonitorLog(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes monitorLog
	 * 
	 * @return Montecarlo.Monitor.GUI.MonitorLog
	 */
	private MonitorLog getMonitorLog() {
		if (monitorLog == null) {
			monitorLog = new MonitorLog();
			monitorLog.setLayout(new BoxLayout(getMonitorLog(),
					BoxLayout.Y_AXIS));
			monitorLog.setPreferredSize(new Dimension(600, 300));
		}
		return monitorLog;
	}

	/**
	 * This method initializes monitorPanel
	 * 
	 * @return Montecarlo.Monitor.GUI.MonitorPanel
	 */
	private MonitorPanel getMonitorPanel() {
		if (monitorPanel == null) {
			monitorPanel = new MonitorPanel();
			monitorPanel.setLayout(new BoxLayout(getMonitorPanel(),
					BoxLayout.Y_AXIS));
			monitorPanel.setPreferredSize(new Dimension(600, 150));
		}
		return monitorPanel;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setTitle("Monitor de Servidores");
		this.setPreferredSize(new Dimension(620, 450));
		this.setMinimumSize(new Dimension(620, 450));
		this.setContentPane(getJPanel());
		this.setSize(new Dimension(620, 425));
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					Monitor.desconectarServers();
				} catch (Exception ex) {
				} finally {
					System.exit(0);
				}
			}
		});
	}
} // @jve:decl-index=0:visual-constraint="10,10"
