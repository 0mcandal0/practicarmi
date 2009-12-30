package Montecarlo.Cliente.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ClienteLog extends JPanel {

	private JScrollPane jScrollPane = null;
	private JList jList = null;

	/**
	 * This method initializes 
	 * 
	 */
	public ClienteLog() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(1);
        this.setLayout(gridLayout);
        this.setSize(new Dimension(600, 300));
        this.setPreferredSize(new Dimension(600, 300));
        this.add(getJScrollPane(), null);
			
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane.setPreferredSize(new Dimension(600, 300));
			jScrollPane.setViewportView(getJList());
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setModel(new DefaultListModel());
		}
		return jList;
	}
	
	
	public void InsertarElemento(Object o) {
		DefaultListModel l = (DefaultListModel) jList.getModel();
		l.insertElementAt(o, 0);
		// Select the new item and make it visible.
		jList.setSelectedIndex(0);
		jList.ensureIndexIsVisible(0);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
