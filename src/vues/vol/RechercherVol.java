package vues.vol;

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

public class RechercherVol extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JLabel l1, l2, l3;
	JTextField tf1, tf2;

	DefaultTableModel tableModel;
	JTable data;

	JButton btn1;
	Vol v1;
	VolDao dao = new VolDao();

	String[] tblHead = { "Id du vol", "Aeroport de depart", "Heure de depart", "Aeroport d'arrivé", "Heure d'arrivé" };
	//Ajout du droit
	private int droitA = 0;
	
	/**
	 * Création de la frame de Recherche d'un vol
	 * @param droit
	 */
	public RechercherVol(int droit) {
		//instanciation du droit
		droitA = droit;

		/* Label 1 */
		l1 = new JLabel("RECHERCHER UN VOL");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l1.setBounds(100, 30, 400, 30);

		/* Label 2 */
		l2 = new JLabel("Numéro de vol");
		l2.setBounds(50, 70, 200, 30);

		/* JTextField recherche */
		tf1 = new JTextField();
		tf1.setBounds(180, 70, 200, 30);

		// Evenement du bouton Entrée sur le textField
		tf1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					rechercherVol();

				}
			}
		});

		/* Bouton */
		btn1 = new JButton("Rechercher");
		btn1.setBounds(400, 69, 176, 30);
		btn1.addActionListener(this);

		getContentPane().add(l1);
		getContentPane().add(l2);
		getContentPane().add(tf1);

		getContentPane().add(btn1);

		tableModel = new DefaultTableModel(tblHead, 0);

		data = new JTable(tableModel);

		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);

		data.setBounds(100, 100, 400, 200);

		data.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));

		scrollPane.setSize(900, 200);
		scrollPane.setLocation(50, 143);
		getContentPane().add(scrollPane);

		setTitle("Rechercher un vol");

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
	 * Récupération du vol recherchée
	 */
	public void chargeData() {
		
		Object[] donnees = { 
			v1.getNumVol(), 
			v1.getAeroportDepart(), 
			v1.getHeureDepart(),
			v1.getAeroportArrive(),
			v1.getHeureArrive() 
		};

		tableModel.setRowCount(0);
		tableModel.addRow(donnees);
		
		data.setModel(tableModel);
	}
	
	/** 
	 * @param e
	 * Evenement du bouton rechercher
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn1) {
			
			rechercherVol();
			
		}
	}

	/**
	 * Création de l'élément du vol rechercher
	 */
	private void rechercherVol() {		

		Object id = (Object) tf1.getText();
		
		// récupération du vol
		v1 = (Vol) dao.get(id);

		if (v1 != null) {
			
			chargeData();	

			// Evenement clic
			data.addMouseListener(new MouseAdapter() {
		
				public void mousePressed(MouseEvent mouseEvent) {
					
					JTable table =(JTable) mouseEvent.getSource();
					
					Point point = mouseEvent.getPoint();
					
					int row = table.rowAtPoint(point);
					
					//Ajout d'un evenement au double clic et teste si l'utilisateur à le droit de modif
					if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && droitA >=2) {
						
						int column = 0;
						
						// récupération du vol sélectionné
						Object id = (Object) table.getModel().getValueAt(row, column).toString();
											
						v1 = (Vol) dao.get(id);
	
						if (v1 != null) {
							
							// Appel de la fiche de modification du vol
							FicheModifVol fm = new FicheModifVol(v1);
							
							fm.addWindowListener(new WindowListener() {
	
								@Override
								public void windowOpened(WindowEvent e) {
															
								}
	
								@Override
								public void windowClosing(WindowEvent e) {
										
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
		}else {
			JOptionPane.showMessageDialog(null, "Vol introuvable");
		}
	}
	
}
