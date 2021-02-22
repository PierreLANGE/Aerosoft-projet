package vues.aeroport;

import javax.swing.JFrame;

import dao.AeroportDao;
import models.Aeroport;
import vues.Fiche;


public class FicheModifAeroport extends JFrame {

	private static final long serialVersionUID = 1L;
	
	AeroportDao bdao;

	/**
	 * Create the frame.
	 */
	//Constructeur
	public FicheModifAeroport(Aeroport a) {
		//Ajout des labels
		String[] listLabels = { "Id de l'aeroport", "Nom de l'aeroport", "Ville de l'aeroport"};
		
		//Ajout des text fields remplis avec les infos demandé
		String[] listTextFields = {a.getIdAeroport(),a.getNomAeroport(),a.getNomVille()};
		
		//Ajout des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		//Ajout de la methode
		String[] listMethodeDoa = { "update","" };

		//Ajout de la dao
		bdao = new AeroportDao();

		//Nom de la jFrame à rouvrir après la modification
		String jFrameClassName = "vues.aeroport.ListeModifAeroports";

		//Creation de la fiche avec les elements ajouté au dessus
		new Fiche(
				"Modification d'un aeroport", 
				bdao, 
				(Object)a, 
				listLabels, 
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				jFrameClassName
				);
	}
}
