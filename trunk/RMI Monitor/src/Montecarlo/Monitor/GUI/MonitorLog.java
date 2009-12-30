package Montecarlo.Monitor.GUI;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class MonitorLog extends JPanel {

	private JList jList = null;
	private JScrollPane jScrollPane = null;

	/**
	 * This method initializes
	 * 
	 */
	public MonitorLog() {
		super();
		initialize();
	}

	public void EliminarElemento() {
		DefaultListModel l = (DefaultListModel) jList.getModel();
		int index = jList.getSelectedIndex();
		l.remove(index);

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
			jList.setSize(new Dimension(600, 300));
			jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		return jList;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane
					.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPane
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane.setPreferredSize(new Dimension(600, 300));
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(600, 300));
		this.setPreferredSize(new Dimension(600, 300));
		this.add(getJScrollPane(), null);

	}

	public void InsertarElemento(Object o) {
		DefaultListModel l = (DefaultListModel) jList.getModel();
		l.insertElementAt(o, 0);
		// Select the new item and make it visible.
		jList.setSelectedIndex(0);
		jList.ensureIndexIsVisible(0);
	}

} // @jve:decl-index=0:visual-constraint="30,72"
