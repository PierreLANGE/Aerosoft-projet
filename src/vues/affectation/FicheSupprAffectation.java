package vues.affectation;

import javax.swing.JFrame;

import Dao.AffectationDao;
import Models.Affectation;
import vues.Fiche;

public class FicheSupprAffectation extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	AffectationDao bdao;

	/**
	 * Create the frame.
	 */
	public FicheSupprAffectation(Affectation a) {
		//Ajout des labels
		String[] listLabels = { "Id de l'affectation à supprimer"};
		
		//Ajout des text fields remplis avec les infos demandé
		String[] listTextFields = {"" + a.getId()};
		
		//Ajout des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		//Ajout de la methode
		String[] listMethodeDoa = { "delete","" };

		//Ajout de la dao
		bdao = new AffectationDao();

		//Creation de la fiche avec les elements ajouté au dessus
		new Fiche(
				"Suppression d'une affectation", 
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
