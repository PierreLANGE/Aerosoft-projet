package vues.avion;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.*;
import java.awt.event.*;

import dao.*;
import models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RechercherAvion extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JLabel l1, l2, l3;

	JTextField tf1, tf2;

	//Creation du tableau
	DefaultTableModel tableModel;

	JTable data;

	JButton btn1;

	//Creation de l'objet aeroport
	Avion av1;

	//Creation de la dao aeroport
	AvionDao dao = new AvionDao();

	//Creation des titres de colonnes
	String[] tblHead = { "Numéro avion", "Type avion", "Base aeroport" };

	//Ajout du droit
	private int droitA = 0;

	public RechercherAvion(int droit) {
		//instanciation du droit
		droitA = droit;
		
		/* Label */
		l1 = new JLabel("RECHERCHER UN AVION");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));

		l2 = new JLabel("Numéro de l'avion");

		tf1 = new JTextField();

		/* Bouton */
		btn1 = new JButton("Rechercher");

		/* Placement */
		l1.setBounds(100, 30, 400, 30);
		l2.setBounds(100, 70, 200, 30);

		tf1.setBounds(149, 70, 200, 30);

		btn1.setBounds(361, 69, 176, 30);

		btn1.addActionListener(this);

		getContentPane().add(l1);
		getContentPane().add(l2);
		getContentPane().add(tf1);

		getContentPane().add(btn1);

		tableModel = new DefaultTableModel(tblHead, 0);

		data = new JTable(tableModel);

		//javax.swing.JTable.setInner(5); 

		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);

		data.setBounds(100, 100, 400, 200);

		data.setDefaultEditor(Object.class, null);

		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));

		scrollPane.setSize(388, 200);
		scrollPane.setLocation(149, 143);
		getContentPane().add(scrollPane);

		setTitle("Rechercher un Avion");

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
	
	//Recuperation des données de l'objet
	public void chargeData(){
		Object[] donnees = { 
			av1.getNumAvion(), 
			av1.getTypeAvion(), 
			av1.getBaseAeroport()
		};

		tableModel.setRowCount(0);
		tableModel.addRow(donnees);
		
		data.setModel(tableModel);
	}

	
	/** 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {

			/* instanciation d'un objet */
			// b1 = new Book(tf1.getText(), tf2.getText());

			//int id = Integer.parseInt(tf1.getText());
			Object id = (Object) tf1.getText();
			//Recuperation des infos grace a la method get()					
			av1 = (Avion) dao.get(id);

			if (av1 != null) {
				
				chargeData();	

				data.addMouseListener(new MouseAdapter() {
					//Ajout d'un evenement au clic
					public void mousePressed(MouseEvent mouseEvent) {
						
						JTable table =(JTable) mouseEvent.getSource();
						
						Point point = mouseEvent.getPoint();
						
						int row = table.rowAtPoint(point);
						//Ajout d'un evenement au double clic et teste si l'utilisateur à le droit de modif
						if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && droitA >=2) {
							
							int column = 0;
							
							//int id = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
							Object id = (Object) table.getModel().getValueAt(row, column).toString();
												
							av1 = (Avion) dao.get(id);
		
							if (av1 != null) {
								//Creation d'une fiche modif
								FicheModifAvion fm = new FicheModifAvion(av1);
								
								fm.addWindowListener(new WindowListener() {
		
									@Override
									public void windowOpened(WindowEvent e) {
										// TODO Auto-generated method stub								
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
										// TODO Auto-generated method stub
										
									}
		
									@Override
									public void windowDeiconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
		
									@Override
									public void windowActivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
		
									@Override
									public void windowDeactivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
								});
		
							}				
							
						}
					}
				});

				repaint();

				tf1.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "Avion introuvable");
			}
		}
	}

}
