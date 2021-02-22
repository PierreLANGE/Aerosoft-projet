package vues.pilote;

import java.util.*;

import javax.swing.JFrame;

// import daos
import dao.*;
// import models
import models.*;
// import fiches
import vues.Fiche;
import vues.FicheUtilisateurPilote;


public class FicheAjoutPilote extends JFrame {

	private static final long serialVersionUID = 1L;
	
	PiloteDao piloteDao;

    UtilisateurDao utilisateurDao;

    Utilisateur utilisateur = new Utilisateur();

	// Créer un ID unique
    int uniqueID = UUID.randomUUID().hashCode();

	/**
	 * Crée une frame d'ajout pilote.
	 */
	public FicheAjoutPilote(Pilote pilote) {

		// Tableau de labels
		String[] listLabels = { "Id du pilote", "Nom du pilote", "Prénom du pilote", "Matricule"};
	
		// Tableau de champs de textes
		String[] listTextFields = {String.valueOf(uniqueID),"","",""};
			
		// Tableau des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		// Tableau des méthodes
		String[] listMethodeDoa = { "save","" };
		piloteDao = new PiloteDao();

		new Fiche(
				"Ajout d'un pilote", 
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
		 * Crée une frame d'ajout utilisateur pilote.
		 */

		// Tableau de labels
        String[] listLabelUser = { "Id de l'utilisateur", "Mail", "Mot de passe", "satut", "role"};
		
		// Tableau de champs de textes
		String[] listTextFieldUser = {String.valueOf(uniqueID),"","", "false", String.valueOf(11111)};
		
		// Récupération des rôles
		ArrayList<Role> listeRoles = new RoleDao().getAll();

		// Ajout des rôles à la comboBox
		Map<String, String> jComboBoxTitles = new HashMap<>();

		for (Role r : listeRoles) {
			jComboBoxTitles.put(r.getRoleNom(), r.getIdRole());
		}
				
		// Tableau des boutons
		String[] listTextBtnsUser = { "Valider","Annuler" };

		// Tableau des méthodes
		String[] listMethodeDoaUser = { "save","" };
		utilisateurDao = new UtilisateurDao();

		new FicheUtilisateurPilote(
				"Ajout d'un utilisateur pilote", 
				utilisateurDao, 
				(Object)utilisateur, 
				listLabelUser, 
				listTextFieldUser,
				listTextBtnsUser,
				listMethodeDoaUser,
				jComboBoxTitles,
				""
				);
	}

}
