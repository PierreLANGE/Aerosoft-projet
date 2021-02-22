package vues.pilote;

import javax.swing.JFrame;

// import des daos
import dao.*;

// import des models
import models.*;

// import de la fiche avec le premier text field non-modifiable
import vues.Fiche;


public class FicheDeletePilote extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	PiloteDao piloteDao;

	UtilisateurDao utilisateurDao;

    Utilisateur utilisateur = new Utilisateur();

	/**
	 * Crée la Frame de suppression d'un pilote.
	 */
	public FicheDeletePilote(Pilote pilote) {

		// Tableau des labels
		String[] listLabels = { "Id de l'aeroport à supprimer"};

		// Tableau des champs de textes
		String[] listTextFields = {"" + pilote.getIdPilote()};
				
		// Tableau des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		// Tableau des méthodes
		String[] listMethodeDoa = { "delete","" };
		piloteDao = new PiloteDao();

		new Fiche(
				"Suppression d'un Pilote", 
				piloteDao, 
				(Object)pilote, 
				listLabels, 
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				"",
				true
				);

		/**
		 * Crée une frame de suppression pour le compte utilisateur associé.
		 */

		// Tableau des labels
		String[] listLabelUser = { "Id de l'utilisateur à supprimer"};

		// Tableau des champs de textes
		String[] listTextFieldUser = {"" + pilote.getIdPilote()};
			
		// Tableau des boutons
		String[] listTextBtnUser = { "Valider","Annuler" };

		// Tableau des méthodes
		String[] listMethodeDoaUser = { "delete","" };
		utilisateurDao = new UtilisateurDao();

		new Fiche(
				"Supression d'un utilisateur pilote", 
				utilisateurDao, 
				(Object)utilisateur, 
				listLabelUser, 
				listTextFieldUser,
				listTextBtnUser,
				listMethodeDoaUser,
				"",
				true
				);
	}
}