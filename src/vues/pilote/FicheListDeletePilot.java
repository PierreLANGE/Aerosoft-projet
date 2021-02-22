package vues.pilote;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// import des daos
import dao.*;

// import des models
import models.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

 
public class FicheListDeletePilot extends JFrame implements ActionListener{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	JLabel l1;
	
	DefaultTableModel tableModel;
	JTable data;
	
	Pilote b1;
	PiloteDao dao = new PiloteDao();
	
	List<Pilote> list = new ArrayList<Pilote>();
	 
	String[] tblHead = { "IdPilote", "NomPilote", "PrenomPilote", "Matricule" };
	 
	/**
	 * Crée la frame de la liste des pilotes pour suppression
	 */
	public FicheListDeletePilot() {
		
		/* Label */
		l1 = new JLabel("LISTE DES PILOTES");
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
		
		chargeData(dao);
		
		data.setDefaultEditor(Object.class, null);
		
		//Crée un évènement de clique		
		data.addMouseListener(new MouseAdapter() {
		    
			public void mousePressed(MouseEvent mouseEvent) {
		        
				JTable table =(JTable) mouseEvent.getSource();
				
		        Point point = mouseEvent.getPoint();
		        
		        int row = table.rowAtPoint(point);
		        
				// Set l'évènement lors d'un double clic
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	
					int column = 0;
					
					//int id = Integer.parseInt(table.getModel().getValueAt(row, column).toString());
					int id = (int) table.getModel().getValueAt(row, column);
					
					// Récupération du pilote de la liste ayant été cliqué
					b1 = (Pilote) dao.get(id);

					if (b1 != null) {
						// Appel de la frame de suppression du pilote				
						FicheDeletePilote fm = new FicheDeletePilote(b1);
						
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
								chargeData(dao);
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
	 * @param dao
	 * Récupère tout les pilotes en BDD pour les ajouter au tableau
	 */
	public void chargeData(PiloteDao dao) {
			
			
			list = (List<Pilote>) dao.getAll();
		
			// Itération de la liste de pilote
			ListIterator<Pilote> listIterator = ((java.util.List<Pilote>) list).listIterator();
			
			tableModel.setRowCount(0);
						
			if (list != null) {

				while(listIterator.hasNext()) {
					b1 = listIterator.next();

					Object[] donnees = { 
						b1.getIdPilote(), 
						b1.getNomPilote(), 
						b1.getPrenomPilote(),
                        b1.getMatricule()
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