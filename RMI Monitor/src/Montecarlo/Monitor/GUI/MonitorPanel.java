package Montecarlo.Monitor.GUI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MonitorPanel extends JPanel {
	private JLabel jLabelA = null;
	private JLabel jLabelEA = null;
	private JPanel jContentPane = null;
	private JLabel jLabelB = null;
	private JLabel jLabelEB = null;
	private JLabel jLabelC = null;
	private JLabel jLabelEC = null;
	private JLabel jLabelD = null;
	private JLabel jLabelED = null;
	private JLabel procesosA = null;
	private JLabel procesosB = null;
	private JLabel procesosC = null;
	private JLabel procesosD = null;

	/**
	 * This method initializes
	 * 
	 */
	public MonitorPanel() {
		super();
		initialize();
	}

	public void ActualizarProcesos(int servidor, long procesos) {
		switch (servidor) {
		case 1:
			procesosA.setText(String.valueOf(procesos));
			break;
		case 2:
			procesosB.setText(String.valueOf(procesos));
			break;
		case 3:
			procesosC.setText(String.valueOf(procesos));
			break;
		case 4:
			procesosD.setText(String.valueOf(procesos));
			break;
		}
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			procesosD = new JLabel();
			procesosD.setComponentOrientation(ComponentOrientation.UNKNOWN);
			procesosD.setFont(new Font("Dialog", Font.BOLD, 24));
			procesosD.setForeground(new Color(64, 181, 180));
			procesosD.setHorizontalAlignment(SwingConstants.CENTER);
			procesosD.setText("0");
			procesosD.setBackground(new Color(228, 105, 92));
			procesosC = new JLabel();
			procesosC.setComponentOrientation(ComponentOrientation.UNKNOWN);
			procesosC.setFont(new Font("Dialog", Font.BOLD, 24));
			procesosC.setForeground(new Color(64, 181, 180));
			procesosC.setHorizontalAlignment(SwingConstants.CENTER);
			procesosC.setText("0");
			procesosC.setBackground(new Color(228, 105, 92));
			procesosB = new JLabel();
			procesosB.setComponentOrientation(ComponentOrientation.UNKNOWN);
			procesosB.setFont(new Font("Dialog", Font.BOLD, 24));
			procesosB.setForeground(new Color(64, 181, 180));
			procesosB.setHorizontalAlignment(SwingConstants.CENTER);
			procesosB.setText("0");
			procesosB.setBackground(new Color(228, 105, 92));
			procesosA = new JLabel();
			procesosA.setText("0");
			procesosA.setForeground(new Color(64, 181, 180));
			procesosA.setBackground(new Color(228, 105, 92));
			procesosA.setComponentOrientation(ComponentOrientation.UNKNOWN);
			procesosA.setFont(new Font("Dialog", Font.BOLD, 24));
			procesosA.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelED = new JLabel();
			jLabelED.setText("procesos en ejecución");
			jLabelED.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelD = new JLabel();
			jLabelD.setText("?");
			jLabelD.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelEC = new JLabel();
			jLabelEC.setText("procesos en ejecución");
			jLabelEC.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelC = new JLabel();
			jLabelC.setText("?");
			jLabelC.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelEB = new JLabel();
			jLabelEB.setText("procesos en ejecución");
			jLabelEB.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelB = new JLabel();
			jLabelB.setText("?");
			jLabelB.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelEA = new JLabel();
			jLabelEA.setText("procesos en ejecución");
			jLabelEA.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelA = new JLabel();
			jLabelA.setText("?");
			jLabelA.setHorizontalAlignment(SwingConstants.CENTER);

			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(4);
			gridLayout.setHgap(2);
			gridLayout.setVgap(2);
			gridLayout.setColumns(3);
			jContentPane = new JPanel();
			jContentPane.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			jContentPane.setPreferredSize(new Dimension(560, 150));
			jContentPane.setLayout(gridLayout);
			jContentPane.add(jLabelA, null);
			jContentPane.add(procesosA, null);
			jContentPane.add(jLabelEA, null);
			jContentPane.add(jLabelB, null);
			jContentPane.add(procesosB, null);
			jContentPane.add(jLabelEB, null);
			jContentPane.add(jLabelC, null);
			jContentPane.add(procesosC, null);
			jContentPane.add(jLabelEC, null);
			jContentPane.add(jLabelD, null);
			jContentPane.add(procesosD, null);
			jContentPane.add(jLabelED, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(560, 158));
		this.add(getJContentPane(), null);

	}

	public void ActualizarDatos(int nservidor, String datos) {
		switch(nservidor)
		{
			case 1:
				jLabelA.setText(datos);
				break;
			case 2:
				jLabelB.setText(datos);
				break;
			case 3:
				jLabelC.setText(datos);
				break;
			case 4:
				jLabelD.setText(datos);
				break;
		}
		
	}


} // @jve:decl-index=0:visual-constraint="-296,24"
