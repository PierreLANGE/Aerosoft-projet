package vues.aeroport;

import javax.swing.JFrame;

import dao.AeroportDao;
import models.Aeroport;
import vues.Fiche;


public class FicheAjoutAeroport extends JFrame {

	private static final long serialVersionUID = 1L;
	
	AeroportDao bdao;

	/**
	 * Create the frame.
	 */
	//Constructeur
	public FicheAjoutAeroport(Aeroport a) {
		
		//Ajout des labels
		String[] listLabels = { "Id de l'aeroport", "Nom de l'aeroport", "Ville de l'aeroport"};
		
		//Ajout des text fields
		String[] listTextFields = {"","",""};
		
		//Ajout des boutons
		String[] listTextBtns = { "Valider","Annuler" };
		
		//Ajout de la methode
		String[] listMethodeDoa = { "save","" };
		
		//Ajout de la dao
		bdao = new AeroportDao();
		
		//Creation de la fiche avec les elements ajouté au dessus
		new Fiche(
				"Ajout d'un aeroport", 
				bdao, 
				(Object)a, 
				listLabels, 
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				""
				);
	}
}
