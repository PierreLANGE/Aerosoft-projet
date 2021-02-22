package vues.home;

import java.awt.FlowLayout;

import java.awt.Toolkit;

import dao.*;
import models.*;
import vues.Fiche;
import vues.LogoAeroSoft;

import javax.swing.*;
import java.awt.*;

public class Home {

    //Creer le tableau qui va recevoir les elements convertis
    private int[] droitInt = new int [5];

    //Creer la variable qui va recevoir le droit depuis la fonction get()
    private String getDroit;

    // getter
    public int[] getDroitInt() {
        return this.droitInt;
    }

    //setter
    public void setDroitInt(int[] droitInt) {
        this.droitInt = droitInt;
    }
    //marge à droite
    private JPanel pan;

    //constructeur
    public Home(int idUser) {
        System.out.println(idUser);
        Utilisateur u;
        UtilisateurDao daoU = new UtilisateurDao();
        u = (Utilisateur) daoU.get(idUser);
        
        //Recuperation du droit depuis la base via la fonction get() : "55555"
        getDroit = u.getIdRole();
        //System.out.println(getDroit);

        //Split le droit en un tableau : String{"5","5","5","5","5"}
        String[] droitArray = getDroit.split("");

        //Conversion des elements du tableau en int en les ajoutant à un nouveau tableau : int{5,5,5,5,5}
        this.droitInt = new int[droitArray.length];

        for (int i = 0; i < droitInt.length; i++){ 
            this.droitInt[i] = Integer.parseInt(droitArray[i]);

        }
        initialize(idUser);
	}
    
    public void initialize(int idUser) {

        /*Fenetre principal*/
        JFrame frame = new JFrame("Tableau de bord");

        /*Jpanel*/
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JMenuBar mb = new JMenuBar();

        /* Mise en place du logo  */
        ImageIcon icon = new LogoAeroSoft().getLogoAerosoft();
        
        JMenuItem item = new JMenuItem(icon);
        mb.add(item);
  
        /*
        On autorise les utilisateurs à acceder aux elements du menu par rapport a leurs droits.
        Chaque indice du tableau droitInt correspond à un module :
        [0] Aéroport
        [1] Vol
        [2] Pilotes
        [3] Affectations
        [4] Avions
        Pour afficher le module il ne faut pas que l'indice soit à 0
        0 = pas d'acces le module ne s'affichera pas
        */

        /*MenuAeroport*/
        if(this.droitInt[0] != 0){
            //On passe en paramètre le droit à lindice du module pour afficher les composant du menu ou pas  
            mb.add(new MenuAeroport(this.droitInt[0]).getMenu());
        }

        /*Menu vol*/
        if(this.droitInt[1] != 0){
            mb.add(new MenuVol(this.droitInt[1]).getMenu());
        }

        /*Menu pilote*/
        if(this.droitInt[2] != 0){
            mb.add(new MenuPilote(this.droitInt[2]).getMenu());
        }
        
        /*MenuAffectation*/
        if(this.droitInt[3] != 0){
            mb.add(new MenuAffectation(this.droitInt[3]).getMenu());
        }

        /*Menu avion*/
        if(this.droitInt[4] != 0){
            mb.add(new MenuAvion(this.droitInt[4]).getMenu());
        }

        //MenuUtilisateur uniquement pour un administrateur
        if(this.getDroit.equals("55555")){
            mb.add(new MenuUtilisateur().getMenu());
        }

        // Mon Compte
        mb.add(new MenuMaFiche(idUser, frame).getMenu());

        pan = new JPanel();
        pan.setLayout(null);

        mb.add(pan);

        frame.setJMenuBar(mb);
        /*Ajout d'une image*/
        ImageIcon background = new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(Fiche.class.getResource("/images/Banniere_aerosoft.jpg"))
        /*.getScaledInstance(160, 50, Image.SCALE_DEFAULT)*/
        );

        /*label pour afficher l'image*/
        JLabel labelimage = new JLabel();
        labelimage.setIcon(background);
        panel.add(labelimage);

        UIManager.put("TableHeader.font", new Font("Arial", Font.BOLD, 15));

        //Fenetre
        frame.add(panel);
        frame.setSize(1280, 780);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }    
}
