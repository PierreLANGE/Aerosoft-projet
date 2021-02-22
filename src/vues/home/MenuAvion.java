package vues.home;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import models.Avion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import vues.avion.*;

public class MenuAvion extends JMenu {

    private static final long serialVersionUID = 1L;

    //Creation de la variable droit qui va recevoir le code droits du module actuel
    private int droit = 0;

    private JMenuItem i1, i2, i3, i4, i5;

    private JMenu menu;

    
    /** 
     * @return JMenu
     */
    public JMenu getMenu() {
        return this.menu;
    }

    public MenuAvion(int droitInt) {
        //Affectation du droit recuperer en paramètre dans la variable droit
        this.droit = droitInt;

        menu = new JMenu("Avions");
        Font font = new Font("Serial", Font.BOLD, 16);
        menu.setFont(font);
        
        //Selon le niveau de droits les modules s'affichent
        if(droit >= 1){
            i1 = new JMenuItem("Lister les Avions");
            i1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new ListeAvion();

                }
            });
            menu.add(i1);

            i2= new JMenuItem("Rechercher un avion");
            i2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new RechercherAvion(droit);
                }
            });
            menu.add(i2);
        }
        
        if(droit >= 2){
            i3 = new JMenuItem("Modifier les Avions");
            i3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new ListeModifAvion();
                }
            });
            menu.add(i3);
        }

        if(droit >=3){
            i4 = new JMenuItem("Ajouter un avion");
            i4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    Avion av = new Avion();
                    new FicheAjoutAvion(av);
                }
            });
            menu.add(i4);
        }
        if(droit >=4){
                i5 = new JMenuItem("Suprimer un avion");
                i5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                    new ListeSupprAvion();
                }
            });
            menu.add(i5);
        }
    }     
}
