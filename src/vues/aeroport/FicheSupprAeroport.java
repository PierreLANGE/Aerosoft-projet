package vues.aeroport;

import javax.swing.JFrame;

import dao.AeroportDao;
import models.Aeroport;
import vues.Fiche;

public class FicheSupprAeroport extends JFrame {

	private static final long serialVersionUID = 1L;
	
	AeroportDao bdao;

	/**
	 * Create the frame.
	 */
	public FicheSupprAeroport(Aeroport a) {
		//Ajout des labels
		String[] listLabels = { "Id de l'aeroport à supprimer"};
		
		//Ajout des text fields remplis avec les infos demandé
		String[] listTextFields = {"" + a.getIdAeroport()};
		
		//Ajout des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		//Ajout de la methode
		String[] listMethodeDoa = { "delete","" };

		//Ajout de la dao
		bdao = new AeroportDao();
		
		//Creation de la fiche avec les elements ajouté au dessus
		new Fiche(
				"Suppression d'un Aeroport", 
				bdao, 
				(Object)a, 
				listLabels, 
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				"",
				true
				);
	}
}
