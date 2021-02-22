package vues.utilisateur;

import javax.swing.JFrame;

// import des daos
import dao.*;

// import des models
import models.*;
import vues.Fiche;
// import des fiches

public class FicheDeleteUtilisateur extends JFrame {

	private static final long serialVersionUID = 1L;

	UtilisateurDao utilisateurDao = new UtilisateurDao();

	/**
	 * Crée la frame de suppression d'un utilisateur.
	 */
	public FicheDeleteUtilisateur(Utilisateur utilisateur) {

		// Tableau des labels
		String[] listLabels = {"Id de l'aeroport à supprimer"};

		// Tableau des textField
		String[] listTextFields = {"" + utilisateur.getIdUtilisateur()};

		// Tableau des boutons
		String[] listTextBtns = {"Valider", "Annuler"};

		// Tableau des méthodes
		String[] listMethodeDoa = {"delete", ""};

		new Fiche("Suppression d'un Utilisateur", utilisateurDao,
				(Object) utilisateur, listLabels, listTextFields, listTextBtns, listMethodeDoa,"",true);
	}
}
