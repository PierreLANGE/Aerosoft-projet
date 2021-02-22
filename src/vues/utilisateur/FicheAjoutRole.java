package vues.utilisateur;

import javax.swing.JFrame;

// import des daos
import dao.*;

// import des models
import models.*;

// import de la fiche générique
import vues.Fiche;


public class FicheAjoutRole extends JFrame {

	private static final long serialVersionUID = 1L;

	RoleDao roleDao;

	/**
	 * Crée la frame d'ajout de role.
	 */
	public FicheAjoutRole(Role role) {

		// Tableau des labels
		String[] listLabelsRole = {"IdRole", "RoleNom"};

		// Tableau des champs de textes
		String[] listTextFieldsRole = {"", ""};

		// Tableau des boutons
		String[] listTextBtnsRole = {"Valider", "Annuler"};

		// Tableau des méthodes
		String[] listMethodeDoaRole = {"save", ""};

		roleDao = new RoleDao();

		new Fiche(
				"Création d'un role",
				roleDao,
				(Object) role,
				listLabelsRole,
			    listTextFieldsRole,
				listTextBtnsRole,
				listMethodeDoaRole,
				""
				);
	}
}
