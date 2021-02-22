package vues.avion;

import javax.swing.JFrame;

import dao.AvionDao;
import models.Avion;
import vues.Fiche;

public class FicheSupprAvion extends JFrame {

	private static final long serialVersionUID = 1L;
	
	AvionDao bdao;

	/**
	 * Create the frame.
	 */
	public FicheSupprAvion(Avion av) {
		//Ajout des labels
		String[] listLabels = { "Id de l'avion à supprimer"};
		
		//Ajout des text fields remplis avec les infos demandé
		String[] listTextFields = {"" + av.getNumAvion()};

		//Ajout des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		//Ajout de la methode
		String[] listMethodeDoa = { "delete","" };

		//Ajout de la dao
		bdao = new AvionDao();

		//Creation de la fiche avec les elements ajouté au dessus
		new Fiche(
				"Suppression d'un avion", 
				bdao, 
				(Object)av, 
				listLabels, 
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				"",
				true
				);
	}
}
