package vues.vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// import de VolDao
import dao.VolDao;

// import du model Vol
import models.Vol;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class ListeReadVol extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1;

	DefaultTableModel tableModel;
	JTable data;

	Vol v1;
	VolDao dao = new VolDao();

	List<Vol> list = new ArrayList<Vol>();

	String[] tblHead = {
			"Id du vol",
			"Aeroport de depart",
			"Heure de depart",
		   	"Aeroport d'arrivé",
			"Heure d'arrivé"
		};

	/**
	 * Création de la frame du tableau des vols
	 */
	public ListeReadVol() {

		/* Label */
		l1 = new JLabel("LISTE DES VOLS");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));

		/* Placement */
		l1.setBounds(100, 30, 400, 30);

		getContentPane().add(l1);

		tableModel = new DefaultTableModel(tblHead, 0);

		data = new JTable(tableModel);
		data.setEnabled(false);

		// javax.swing.JTable.setInner(5);

		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);

		data.setBounds(100, 100, 450, 450);

		chargeData(dao);

		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));

		scrollPane.setSize(900, 300);
		scrollPane.setLocation(50, 100);
		getContentPane().add(scrollPane);

		setTitle("LISTE DES AEROPORT");

		setSize(1000, 540);
		getContentPane().setLayout(null);

		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - this.getWidth()) / 2;
		final int y = (screenSize.height - this.getHeight()) / 2;

		setLocation(x, y);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	/**
	 * @param dao Récupération des données pour tous les vols
	 */
	public void chargeData(VolDao dao) {

		list = (List<Vol>) dao.getAll();

		ListIterator<Vol> listIterator = ((java.util.List<Vol>) list).listIterator();

		tableModel.setRowCount(0);

		if (list != null) {

			while (listIterator.hasNext()) {

				v1 = listIterator.next();

				Object[] donnees = {
						v1.getNumVol(),
						v1.getAeroportDepart(),
						v1.getHeureDepart(),
						v1.getAeroportArrive(),
						v1.getHeureArrive()
					};

				tableModel.addRow(donnees);

			}

			tableModel.fireTableDataChanged();
			data.setModel(tableModel);
			data.repaint();
		}
	}

	/**
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
