package vues.affectation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import dao.AffectationDao;
import models.Affectation;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

 
public class ListeModifAffectations extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JLabel l1;
	//Creation du tableau
	DefaultTableModel tableModel;

	JTable data;

	//Creation de l'objet affectation
	Affectation b1;
	
	//Creation de la liste affectation
	List<Affectation> list = new ArrayList<Affectation>();
	
	//Creation des titres de colonnes
	String[] tblHead = { 
			"IdAffectation", 
			"NumVol", 
			"DateVol",
			"AffectationCode", 
			"NumAvion", 
			"IdPilote"  
	};
	 

	public ListeModifAffectations() {
		
		/* Label */
		l1 = new JLabel("LISTE DES AFFECTATIONS");
		l1.setForeground(Color.blue);
		l1.setFont(new Font("Serif", Font.BOLD, 20));
		l1.setBounds(100, 30, 400, 30);

		getContentPane().add(l1);

		tableModel = new AffectationTableModel(tblHead);
		
		data = new JTable(tableModel);		
		data.setFont(new Font("Chandas", Font.BOLD, 15));
		data.setRowHeight(25);		
		data.setBounds(100, 100, 450, 450);
		
		chargeData();

		((DefaultTableCellRenderer) data.getTableHeader().getDefaultRenderer())
		.setHorizontalAlignment(JLabel.CENTER);
			
		DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
		rendar.setHorizontalAlignment(JLabel.CENTER);
		
		TableModel tableModel = data.getModel();

		for (int x = 1; x < tableModel.getColumnCount(); x++) {

			if (x != 3) {

				data.getColumnModel().getColumn(x).setCellRenderer(rendar);
			}

		}
		
		data.setDefaultEditor(Object.class, null);
		
		data.addMouseListener(new MouseAdapter() {
		    
			public void mousePressed(MouseEvent mouseEvent) {

				AffectationDao dao = new AffectationDao();
		        
				JTable table =(JTable) mouseEvent.getSource();
				
		        Point point = mouseEvent.getPoint();
		        
				int row = table.rowAtPoint(point);
				
		        //Ajout d'un evenement au double clic
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	
					int column = 0;
					
					//int id = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
					Object id = (Object) table.getModel().getValueAt(row, column).toString();

					//Recuperation des infos de l'affectation cliquer grace a get()					
					b1 = (Affectation) dao.get(id);

					if (b1 != null) {

						//Creation de la fiche modif pour l'element cliquer
						new FicheModifAffectation(b1);
						dispose();
					}					
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(data);
		scrollPane.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
		
		scrollPane.setSize(850, 300);
		scrollPane.setLocation(50, 100);
		getContentPane().add(scrollPane);

		setTitle("LISTE DES AFFECTATIONS");

		setSize(950, 540);
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
		
		//Creation de la dao affectation
		AffectationDao dao = new AffectationDao();
			
		//Recuperation des données de la requete getAll() dans une list
		list = (List<Affectation>) dao.getAll();
						
		ListIterator<Affectation> listIterator = ((java.util.List<Affectation>) list).listIterator();
		
		tableModel.setRowCount(0);
		//Isertion des données dans le tableau			
		if (list != null) {

			while(listIterator.hasNext()) {
				b1 = listIterator.next();

				Object[] donnees = { 
					b1.getId(), 
					b1.getNumVol(), 
					b1.getDateVol(), 
					b1.getAffectationCode(), 
					b1.getNumAvion(), 
					b1.getPilote().getNomPilote() 
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