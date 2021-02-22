package vues.home;

import javax.swing.*;
import java.awt.Font;
import dao.PiloteDao;
import models.Pilote;
import vues.Login;
import vues.compte.MaFiche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MenuMaFiche extends JMenu{

    private static final long serialVersionUID = 1L;
    
    private JMenuItem itemMe, itemMePilote, itemLogOut;

    private JMenu menuMaFiche;

    PiloteDao piloteDao = new PiloteDao();

    Pilote pilote;

    public JMenu getMenu() {
        return this.menuMaFiche;
    }

    public MenuMaFiche(int idUser, JFrame frame) {

    menuMaFiche = new JMenu("Ma Fiche");
    Font font = new Font("Serial", Font.BOLD, 16);
    menuMaFiche.setFont(font);

    itemMe=new JMenuItem("Mon Compte");
    itemMe.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt){
            
            new MaFiche(idUser);
    	}
    });

    pilote = (Pilote) piloteDao.get(idUser);

    itemMePilote=new JMenuItem("Mes informations pilotes");
    itemMePilote.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
            new InfosPilote(pilote);
    	}
    });
        //Deconexion 
        itemLogOut=new JMenuItem("Déconnexion");
        itemLogOut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                frame.dispose();
                new Login();
            }
        });

        menuMaFiche.add(itemMe);  
        if (pilote != null) {
            menuMaFiche.add(itemMePilote);   
        }
        menuMaFiche.add(itemLogOut);
    }     
}
