package vues.home;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import models.Affectation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import vues.affectation.*;

public class MenuAffectation extends JMenu{
    
    private static final long serialVersionUID = 1L;

    //Creation de la variable droit qui va recevoir le code droits du module actuel
    private int droit = 0;
    
    private JMenuItem i1, i2, i3, i4, i5;

    private JMenu menu;

    public JMenu getMenu() {
        return this.menu;
    }

    public MenuAffectation(int droitInt) {
        //Affectation du droit recuperer en paramètre dans la variable droit
        this.droit = droitInt;

        menu = new JMenu("Affectations");
        Font font = new Font("Serial", Font.BOLD, 16);
        menu.setFont(font);

        //Selon le niveau de droits les modules s'affichent
        if(droit >=1){
            
            i1 = new JMenuItem("Lister les affectations");
            i1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
            
                    new ListeAffectations();
                }
            });
            menu.add(i1);

            i2 = new JMenuItem("Trouver une affectation");
            i2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
            
                    new RechercherAffectation(droit);
                }
            });
            menu.add(i2);
        }

        if(droit >=2){

            i3 = new JMenuItem("Modifier une affectation");
            i3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new ListeModifAffectations();

                }
            });
            menu.add(i3);
        }

        if(droit >=3){

            i4 = new JMenuItem("Ajouter une affectation");
            i4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
            
                    Affectation ae = new Affectation();
                    new FicheAjoutAffectation(ae);
                }
            });
            menu.add(i4);
        }

        if(droit >=4){

            i5 = new JMenuItem("Supprimer une affectation");
            i5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                            
                    new ListeDeleteAffectations();
                }
            });
            menu.add(i5);
        }
    }
}
