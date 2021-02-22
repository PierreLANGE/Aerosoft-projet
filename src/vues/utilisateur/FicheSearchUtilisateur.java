package vues.utilisateur;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.*;
import java.awt.event.*;

// import des daos
import dao.*;

// import des models
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FicheSearchUtilisateur extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel l1, l2, l3;
	JTextField tf1, tf2;

	DefaultTableModel tableModel;
	JTable data;

	JButton btn1;
	Utilisateur utilisateur;
	UtilisateurDao utilisateurDao = new UtilisateurDao();

	String[] tblHead = {"Id Utilisateur", "Mail", "Statut"};

	/**
	 * Création de la frame de recherche d'un utilisateur
	 */
	public FicheSearchUtilisateur() {

		/* Label */
		l1 = new JLabel("RECHERCHER UN UTILISATEUR");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l1.setBounds(100, 30, 400, 30);

		l2 = new JLabel("ID Utilisateur");
		l2.setBounds(100, 70, 200, 30);

		tf1 = new JTextField();
		tf1.setBounds(149, 70, 200, 30);

		/* Bouton */
		btn1 = new JButton("Rechercher");
		btn1.setBounds(361, 69, 176, 30);
		btn1.addActionListener(this);

		getContentPane().add(l1);
		getContentPane().add(l2);
		getContentPane().add(tf1);

		getContentPane().add(btn1);

		tableModel = new DefaultTableModel(tblHead, 0);

		data = new JTable(tableModel);
		data.setEnabled(false);

		// javax.swing.JTable.setInner(5);

		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);

		data.setBounds(100, 100, 400, 200);

		data.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));

		scrollPane.setSize(388, 200);
		scrollPane.setLocation(149, 143);
		getContentPane().add(scrollPane);

		setTitle("Rechercher un utilisateur");

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
	 * récupération des données d'un utilisateur
	 */
	public void chargeData() {
		Object[] donnees =
				{utilisateur.getIdUtilisateur(), utilisateur.getMail(), utilisateur.getStatut()};

		tableModel.setRowCount(0);
		tableModel.addRow(donnees);

		data.setModel(tableModel);
	}


	/**
	 * @param e Evenement de recherche
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn1) {

			String id = (String) tf1.getText();

			utilisateur = (Utilisateur) utilisateurDao.get(Integer.parseInt(id));

			if (utilisateur != null) {

				chargeData();

				// Evenement de clic
				data.addMouseListener(new MouseAdapter() {

					public void mousePressed(MouseEvent mouseEvent) {

						JTable table = (JTable) mouseEvent.getSource();

						Point point = mouseEvent.getPoint();

						int row = table.rowAtPoint(point);

						// Evenement de double clic
						if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {

							int column = 0;

							Object id = (Object) table.getModel().getValueAt(row, column);

							utilisateur = (Utilisateur) utilisateurDao.get(id);

							if (utilisateur != null) {

								// Appel de la fiche de modification d'un pilote
								FicheModifUtilisateur fm = new FicheModifUtilisateur(utilisateur);

								fm.addWindowListener(new WindowListener() {

									@Override
									public void windowOpened(WindowEvent e) {										
									}

									@Override
									public void windowClosing(WindowEvent e) {
										chargeData();
									}

									@Override
									public void windowClosed(WindowEvent e) {
										chargeData();
									}

									@Override
									public void windowIconified(WindowEvent e) {										

									}

									@Override
									public void windowDeiconified(WindowEvent e) {										

									}

									@Override
									public void windowActivated(WindowEvent e) {										

									}

									@Override
									public void windowDeactivated(WindowEvent e) {										

									}

								});
							}
						}
					}
				});

				repaint();

				tf1.setText("");

			} else {
				
				JOptionPane.showMessageDialog(null, "Utilisateur introuvable");
				
			}
		}
	}

}
