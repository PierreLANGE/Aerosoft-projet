package vues.home;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import models.Pilote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import vues.pilote.*;

public class MenuPilote extends JMenu{
    
    private static final long serialVersionUID = 1L;

    //Creation de la variable droit qui va recevoir le code droits du module actuel
    private int droit = 0;
    
    private JMenuItem itemReadPilote, itemAddPilote, itemModifPilote, itemDeletePilote, itemSearchPilote;

    private JMenu menuPilote;

    /** 
     * @return JMenu
     */
    public JMenu getMenu() {
        return this.menuPilote;
    }

    public MenuPilote(int droitInt) {
        //Affectation du droit recuperer en paramètre dans la variable droit
        this.droit = droitInt;

        menuPilote = new JMenu("Pilotes");
        Font font = new Font("Serial", Font.BOLD, 16);
        menuPilote.setFont(font);

        //Selon le niveau de droits les modules s'affichent
        if(droit >=1){
            itemReadPilote=new JMenuItem("Lister les pilotes");
            itemReadPilote.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    new FicheReadPilote();
                }
            });
            menuPilote.add(itemReadPilote);

            itemSearchPilote=new JMenuItem("Rechercher un pilote");
            itemSearchPilote.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    new FicheSearchPilote(droit);
                }
            });
            menuPilote.add(itemSearchPilote);
        }

        if(droit >=2){
            itemModifPilote=new JMenuItem("Modifier les pilotes");
            itemModifPilote.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    new ListeModifPilote();
                }
            });
            menuPilote.add(itemModifPilote);
        }

        if(droit >=3){
            itemAddPilote=new JMenuItem("Ajouter un pilote");
            itemAddPilote.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    
                    Pilote pilote = new Pilote();
                    new FicheAjoutPilote(pilote);
                }
            });
            menuPilote.add(itemAddPilote);
        }

        if(droit >=3){

            itemDeletePilote=new JMenuItem("Supprimer un pilote");
            itemDeletePilote.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    new FicheListDeletePilot();
                }
            });
            menuPilote.add(itemDeletePilote);
        }     
    }
}
