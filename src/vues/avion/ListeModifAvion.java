package vues.avion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.AvionDao;
import models.Avion;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

 
public class ListeModifAvion extends JFrame implements ActionListener{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JLabel l1;
	//Creation du tableau
	DefaultTableModel tableModel;

	JTable data;

	//Creation de l'objet avion
	Avion av1;

	//Creation de la dao avion
	AvionDao dao = new AvionDao();
	
	//Creation de la liste avion
	List<Avion> list = new ArrayList<Avion>();
	
	//Creation des titres de colonnes
	String[] tblHead = { "Numéro avion", "Type avion", "Base aeroport" };
	 

	public ListeModifAvion() {
		
		/* Label */
		l1 = new JLabel("LISTE DES AVIONS");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));

		/* Placement */
		l1.setBounds(100, 30, 400, 30);

		getContentPane().add(l1);

		tableModel = new DefaultTableModel(tblHead, 0);
		
		data = new JTable(tableModel);
		
		//javax.swing.JTable.setInner(5); 
		
		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);
		
		data.setBounds(100, 100, 450, 450);
		
		chargeData();
		
		data.setDefaultEditor(Object.class, null);
		
		data.addMouseListener(new MouseAdapter() {
		    
			public void mousePressed(MouseEvent mouseEvent) {
		        //Ajout d'un evenement au clic
				JTable table =(JTable) mouseEvent.getSource();
				
		        Point point = mouseEvent.getPoint();
		        
		        int row = table.rowAtPoint(point);
		        //pour que l'event soit pris au double clic
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	
					int column = 0;
					
					//int id = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
					Object id = (Object) table.getModel().getValueAt(row, column).toString();
					//Recuperation des données de l'element cliquer avec la fonction get()					
					av1 = (Avion) dao.get(id);

					if (av1 != null) {
						//Creation de la fiche pour modif l'element cliquer
						new FicheModifAvion(av1);
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

		setTitle("LISTE DES Avions");

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
	 * @param dao
	 */
	public void chargeData() {
		AvionDao dao = new AvionDao();
			
		//Recuperation des données de la requete getAll() dans une list
		list = (List<Avion>) dao.getAll();
						
		ListIterator<Avion> listIterator = ((java.util.List<Avion>) list).listIterator();
		
		tableModel.setRowCount(0);
		
		//Isertion des données dans le tableau
		if (list != null) {

			while(listIterator.hasNext()) {
				av1 = listIterator.next();

				Object[] donnees = { 
					av1.getNumAvion(), 
					av1.getTypeAvion(), 
					av1.getBaseAeroport()
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
		// TODO Auto-generated method stub
		
	}
	
}