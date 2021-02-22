package vues.home;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import vues.utilisateur.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MenuUtilisateur extends JMenu{

    private static final long serialVersionUID = 1L;
    
    private JMenuItem itemReadUtilisateur,
                        itemAddUtilisateur, 
                        itemListUtilisateur, 
                        itemDeleteUtilisateur, 
                        itemSearchUtilisateur, 
                        itemAddRole;

    private JMenu menuUtilisateur;

    
    /** 
     * @return JMenu
     */
    public JMenu getMenu() {
        return this.menuUtilisateur;
    }

    public MenuUtilisateur() {

            menuUtilisateur = new JMenu("Utilisateurs");
            Font font = new Font("Serial", Font.BOLD, 16);
            menuUtilisateur.setFont(font);

    itemReadUtilisateur = new JMenuItem("Lister les utilisateurs");
    itemReadUtilisateur.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
    		
            new FicheReadUtilisateur();
    	}
    });
    menuUtilisateur.add(itemReadUtilisateur);

    itemSearchUtilisateur=new JMenuItem("Rechercher un utilisateur");
    itemSearchUtilisateur.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
            new FicheSearchUtilisateur();
    	}
    });
    menuUtilisateur.add(itemSearchUtilisateur);

    itemListUtilisateur=new JMenuItem("Modifier les utilisateurs");
    itemListUtilisateur.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
    		new FicheListUtilisateur();
    	}
    });
    menuUtilisateur.add(itemListUtilisateur);

    itemAddUtilisateur=new JMenuItem("Ajouter un utilisateur");
    itemAddUtilisateur.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
			new FicheAjoutUtilisateur();
			
    	}
    });
    menuUtilisateur.add(itemAddUtilisateur);

    itemDeleteUtilisateur=new JMenuItem("Supprimer un utilisateur");
    itemDeleteUtilisateur.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
            new FicheListDeleteUtilisateur();
    	}
    });
    menuUtilisateur.add(itemDeleteUtilisateur);

    itemAddRole=new JMenuItem("Créer ou modifier un rôle");
    itemAddRole.addActionListener(new ActionListener()
    {
    	public void actionPerformed(ActionEvent evt)
    	{
            new TableRole();
    	}
    });
        menuUtilisateur.add(itemReadUtilisateur);
        menuUtilisateur.add(itemSearchUtilisateur);
        menuUtilisateur.add(itemListUtilisateur);
        menuUtilisateur.add(itemAddUtilisateur);
        menuUtilisateur.add(itemDeleteUtilisateur);
        menuUtilisateur.add(itemAddRole);
    }
  
}
