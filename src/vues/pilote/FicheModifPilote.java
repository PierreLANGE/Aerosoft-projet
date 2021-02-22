package vues.pilote;

import javax.swing.JFrame;

// import des daos
import dao.*;

// import des models
import models.*;

// import de la fiche générique
import vues.*;

public class FicheModifPilote extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	PiloteDao piloteDao;

	/**
	 * Crée la frame d'affichage des pilotes.
	 */
	public FicheModifPilote(Pilote pilote) {

		// Tableau des labels
		String[] listLabels = { "Nom du pilote", "Prenom du Pilote", "Matricule"};
		
		// Tableau des champs de textes
		String[] listTextFields = {pilote.getNomPilote(),pilote.getPrenomPilote(),pilote.getMatricule()};
				
		// Tableau des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		// Tableau des méthodes
		String[] listMethodeDoa = { "update","" };
		piloteDao = new PiloteDao();

		//Nom de la jFrame à rouvrir après la modification
	    String jFrameClassName = "vues.pilote.ListeModifPilote";

		new FichePilote(
				"Modification d'un pilote", 
				piloteDao, 
				(Object)pilote, 
				listLabels, 
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				jFrameClassName,
				false
				);
	}
}
