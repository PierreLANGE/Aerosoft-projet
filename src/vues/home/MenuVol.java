package vues.home;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import models.Vol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import vues.vol.*;

public class MenuVol extends JMenu {

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

    public MenuVol(int droitInt) {
        //Affectation du droit recuperer en paramètre dans la variable droit
        this.droit = droitInt;

        menu = new JMenu("Vols");
        Font font = new Font("Serial", Font.BOLD, 16);
        menu.setFont(font);
        //Selon le niveau de droits les modules s'affichent
        if(droit >=1){

            i1 = new JMenuItem("Lister les vols");
            i1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new ListeReadVol();
                }
            });
            menu.add(i1);

            i2 = new JMenuItem("Rechercher un vol");
            i2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new RechercherVol(droit);
                }
            });
            menu.add(i2);
        }

        if(droit >=2){

            i3 = new JMenuItem("Modifier un vol");
            i3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    new ListeVol();
                }
            });
            menu.add(i3);
        }

        if(droit >=3){
            i4 = new JMenuItem("Ajouter un vol");
            i4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    Vol v = new Vol();
                    new FicheAjoutVol(v);
                }
            });
            menu.add(i4);
        }

        if(droit >=4){
        i5 = new JMenuItem("Suprimer un vol");
        i5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                new ListeSupprVol();
            }
        });
        menu.add(i5);
        }
    }     
}
