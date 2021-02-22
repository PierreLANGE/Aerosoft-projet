package vues.utilisateur;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.*;
import models.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class FicheListUtilisateur extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1;

	DefaultTableModel tableModel;
	JTable data;

	Utilisateur b1;
	UtilisateurDao dao = new UtilisateurDao();

	List<Utilisateur> list = new ArrayList<Utilisateur>();

	String[] tblHead = {"IdUtilisateur", "Mail", "Statut", "IdRole"};

	/**
	 * Création de la frame du tableau d'utilisateur pour modification
	 */
	public FicheListUtilisateur() {

		/* Label */
		l1 = new JLabel("LISTE DES UTILISATEURS");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l1.setBounds(100, 30, 400, 30);

		getContentPane().add(l1);

		tableModel = new UtilisateurTableModel(tblHead);

		data = new JTable(tableModel);

		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);

		data.setBounds(100, 100, 450, 450);

		chargeData();

		data.setDefaultEditor(Object.class, null);

		// Evenement du clic
		data.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent mouseEvent) {

				JTable table = (JTable) mouseEvent.getSource();

				Point point = mouseEvent.getPoint();

				int row = table.rowAtPoint(point);

				// Evenement du double clic
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

					int column = 0;

					int id = (int) table.getModel().getValueAt(row, column);

					b1 = (Utilisateur) dao.get(id);

					if (b1 != null) {

						// Appel de la fiche de modification d'un utilisateur
						new FicheModifUtilisateur(b1);
						dispose();
					}

				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));

		scrollPane.setSize(550, 300);
		scrollPane.setLocation(50, 100);
		getContentPane().add(scrollPane);

		setTitle("LISTE DES UTILISATEURS");

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
	 * @param dao Récupération des données de tous les utilisateurs
	 */
	public void chargeData() {
		UtilisateurDao dao = new UtilisateurDao();

		list = (List<Utilisateur>) dao.getAll();

		ListIterator<Utilisateur> listIterator =
				((java.util.List<Utilisateur>) list).listIterator();

		tableModel.setRowCount(0);

		if (list != null) {

			while (listIterator.hasNext()) {

				b1 = listIterator.next();

				Object[] donnees =
						{b1.getIdUtilisateur(), b1.getMail(), b1.getStatut(), b1.getIdRole()};

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
