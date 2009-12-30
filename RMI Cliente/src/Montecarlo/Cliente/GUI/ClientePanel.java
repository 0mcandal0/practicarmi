package Montecarlo.Cliente.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;


@SuppressWarnings("serial")
public class ClientePanel extends JPanel {

	public class EventoClick extends EventObject {
		long pares;
		public EventoClick(Object source, long pares) {
			super(source);
			this.pares = pares;
		}
	}
	
	// Declare the listener class. It must extend EventListener.
	// A class must implement this interface to get MyEvents.
	public interface EventoClickListener extends EventListener {
		public void onEventoClick(EventoClick evt);
	}
	private EventListenerList listeners = new EventListenerList(); // @jve:decl-index=0:
	private ActionListener bclick = null;  //  @jve:decl-index=0:
	
	private JButton jButtonEnviar = null;
	private JTextField jTextPares = null;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JLabel jLabelResultado1 = null;
	private JTextField jTextServidor1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextServidor4 = null;
	private JTextField jTextServidor2 = null;
	private JTextField jTextServidor3 = null;
	private JLabel jLabelResultado4 = null;
	private JLabel jLabelResultado3 = null;
	private JLabel jLabelResultado2 = null;
	private JPanel jPanel2 = null;
	private JLabel jLabel1 = null;
	private JTextField jTextPI = null;
	
	public void ActualizarResultados(int servidor, long resultado) {
		switch (servidor) {
		case 1:
			jLabelResultado1.setText(String.valueOf(resultado));
			break;
		case 2:
			jLabelResultado2.setText(String.valueOf(resultado));
			break;
		case 3:
			jLabelResultado3.setText(String.valueOf(resultado));
			break;
		case 4:
			jLabelResultado4.setText(String.valueOf(resultado));
			break;
		}
	}

	public void ActualizarDatos(int servidor,String datos) {
		switch (servidor) {
		case 1:
			jTextServidor1.setText(datos);
			break;
		case 2:
			jTextServidor2.setText(datos);
			break;
		case 3:
			jTextServidor3.setText(datos);
			break;
		case 4:
			jTextServidor4.setText(datos);
			break;
		}
	}
	
	public void ActualizarPI(String pi) {
		jTextPI.setText(pi);
	}
	
	public void BloquearEnvio(boolean b) {
		jButtonEnviar.setEnabled(!b);
		jTextPares.setEnabled(!b);
	}
	
	// register new listener
	public void addEventoClickListener(EventoClickListener l) {
		listeners.add(EventoClickListener.class, l);
	}

	// This private class is used to fire MyEvents
	void fireEventoClick(EventoClick evt) {
		Object[] list = listeners.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < list.length; i += 2) {
			if (list[i] == EventoClickListener.class) {
				((EventoClickListener) list[i + 1]).onEventoClick(evt);
			}
		}
	}

	
	/**
	 * This method initializes 
	 * 
	 */
	public ClientePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		bclick = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (b.equals(jButtonEnviar))
					fireEventoClick(new EventoClick(this, Long.valueOf(jTextPares.getText())));
			}
		};
		
        jLabel = new JLabel();
        jLabel.setText("Número de Pares");
        jLabel.setPreferredSize(new Dimension(99, 27));
        jLabel.setName("jLabel");
        jLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(600, 27));
        this.setBounds(new Rectangle(0, 0, 672, 327));
        this.add(getJPanel(), null);
        this.add(getJPanel1(), null);
        this.add(getJPanel2(), null);
			
	}

	/**
	 * This method initializes jButtonEnviar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonEnviar() {
		if (jButtonEnviar == null) {
			jButtonEnviar = new JButton();
			jButtonEnviar.setText("Enviar");
			jButtonEnviar.setName("jButtonEnviar");
			jButtonEnviar.setFont(new Font("Dialog", Font.BOLD, 12));
			jButtonEnviar.setPreferredSize(new Dimension(69, 27));
			jButtonEnviar.addActionListener(bclick);
		}
		return jButtonEnviar;
	}

	/**
	 * This method initializes jTextPares	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextPares() {
		if (jTextPares == null) {
			jTextPares = new JTextField();
			jTextPares.setHorizontalAlignment(JTextField.CENTER);
			jTextPares.setText("500000");
			jTextPares.setPreferredSize(new Dimension(82, 27));
			jTextPares.setName("jTextPares");
			jTextPares.setFont(new Font("Dialog", Font.BOLD, 24));
		}
		return jTextPares;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(1);
			gridLayout1.setColumns(3);
			jPanel = new JPanel();
			jPanel.setLayout(gridLayout1);
			jPanel.add(jLabel, null);
			jPanel.add(getJTextPares(), null);
			jPanel.add(getJButtonEnviar(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabelResultado2 = new JLabel();
			jLabelResultado2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelResultado2.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabelResultado2.setText("0");
			jLabelResultado3 = new JLabel();
			jLabelResultado3.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelResultado3.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabelResultado3.setText("0");
			jLabelResultado4 = new JLabel();
			jLabelResultado4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelResultado4.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabelResultado4.setText("0");
			jLabel3 = new JLabel();
			jLabel3.setText("Servidor");
			jLabel3.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2 = new JLabel();
			jLabel2.setText("Resultado");
			jLabel2.setFont(new Font("Dialog", Font.BOLD, 18));
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelResultado1 = new JLabel();
			jLabelResultado1.setText("0");
			jLabelResultado1.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabelResultado1.setHorizontalAlignment(SwingConstants.CENTER);
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(5);
			gridLayout2.setColumns(2);
			jPanel1 = new JPanel();
			jPanel1.setLayout(gridLayout2);
			jPanel1.add(jLabel3, null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(getJTextServidor1(), null);
			jPanel1.add(jLabelResultado1, null);
			jPanel1.add(getJTextServidor2(), null);
			jPanel1.add(jLabelResultado2, null);
			jPanel1.add(getJTextServidor3(), null);
			jPanel1.add(jLabelResultado3, null);
			jPanel1.add(getJTextServidor4(), null);
			jPanel1.add(jLabelResultado4, null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextServidor1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextServidor1() {
		if (jTextServidor1 == null) {
			jTextServidor1 = new JTextField();
			jTextServidor1.setText("?");
			jTextServidor1.setEditable(false);
			jTextServidor1.setHorizontalAlignment(JTextField.CENTER);
		}
		return jTextServidor1;
	}

	/**
	 * This method initializes jTextServidor4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextServidor4() {
		if (jTextServidor4 == null) {
			jTextServidor4 = new JTextField();
			jTextServidor4.setText("?");
			jTextServidor4.setEditable(false);
			jTextServidor4.setHorizontalAlignment(JTextField.CENTER);
		}
		return jTextServidor4;
	}

	/**
	 * This method initializes jTextServidor2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextServidor2() {
		if (jTextServidor2 == null) {
			jTextServidor2 = new JTextField();
			jTextServidor2.setText("?");
			jTextServidor2.setEditable(false);
			jTextServidor2.setHorizontalAlignment(JTextField.CENTER);
		}
		return jTextServidor2;
	}

	/**
	 * This method initializes jTextServidor3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextServidor3() {
		if (jTextServidor3 == null) {
			jTextServidor3 = new JTextField();
			jTextServidor3.setText("?");
			jTextServidor3.setEditable(false);
			jTextServidor3.setHorizontalAlignment(JTextField.CENTER);
		}
		return jTextServidor3;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setColumns(2);
			jLabel1 = new JLabel();
			jLabel1.setText("PI Calculado");
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
			jPanel2 = new JPanel();
			jPanel2.setLayout(gridLayout);
			jPanel2.add(jLabel1, null);
			jPanel2.add(getJTextPI(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jTextPI	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextPI() {
		if (jTextPI == null) {
			jTextPI = new JTextField();
			jTextPI.setFont(new Font("Dialog", Font.PLAIN, 18));
			jTextPI.setEditable(false);
			jTextPI.setHorizontalAlignment(JTextField.CENTER);
			jTextPI.setText("?");
			jTextPI.setBackground(Color.yellow);
		}
		return jTextPI;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
