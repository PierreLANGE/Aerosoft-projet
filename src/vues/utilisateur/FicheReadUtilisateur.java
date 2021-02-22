package vues.utilisateur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// import des daos
import dao.*;

// import des models
import models.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class FicheReadUtilisateur extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel l1;

	DefaultTableModel tableModel;
	JTable data;

	Utilisateur b1;
	UtilisateurDao dao = new UtilisateurDao();

	List<Utilisateur> list = new ArrayList<Utilisateur>();

	String[] tblHead = {"IdUtilisateur", "Mail"};

	public FicheReadUtilisateur() {

		/* Label */
		l1 = new JLabel("LISTE DES UTILISATEURS");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l1.setBounds(100, 30, 400, 30);

		getContentPane().add(l1);

		tableModel = new DefaultTableModel(tblHead, 0);

		data = new JTable(tableModel);
		data.setEnabled(false);

		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);

		data.setBounds(100, 100, 450, 450);

		chargeData(dao);

		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));

		scrollPane.setSize(550, 300);
		scrollPane.setLocation(50, 100);
		getContentPane().add(scrollPane);

		setTitle("LISTE DES PILOTES");

		setSize(639, 540);
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
	 * @param dao Récupération de tous les utilisateurs à afficher dans le tableau
	 */
	public void chargeData(UtilisateurDao dao) {

		list = (List<Utilisateur>) dao.getAll();

		ListIterator<Utilisateur> listIterator =
				((java.util.List<Utilisateur>) list).listIterator();

		tableModel.setRowCount(0);

		if (list != null) {

			while (listIterator.hasNext()) {

				b1 = listIterator.next();

				Object[] donnees = {b1.getIdUtilisateur(), b1.getMail()};

				tableModel.addRow(donnees);

			}

			tableModel.fireTableDataChanged();
			data.setModel(tableModel);
			data.repaint();
		}
	}
}
