package vues.utilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import dao.*;
import models.*;
import vues.FicheUtilisateur;

public class FicheModifUtilisateur extends JFrame {

	private static final long serialVersionUID = 1L;

	UtilisateurDao utilisateurDao;

	/**
	 * Create the frame.
	 */
	public FicheModifUtilisateur(Utilisateur utilisateur) {

		String[] listLabels = {"IdUtilisateur", "Mail", "MotDePasse", "Statut", "IdRole"};

		String[] listTextFields = {
				String.valueOf(utilisateur.getIdUtilisateur()),
				utilisateur.getMail(),
				utilisateur.getMotDePasse(),
				String.valueOf(utilisateur.getStatut()),
				utilisateur.getIdRole()
			};

		ArrayList<Role> listeRoles = new RoleDao().getAll();

		Map<String, String> jComboBoxTitles = new HashMap<>();

		for (Role r : listeRoles) {

			jComboBoxTitles.put(r.getRoleNom(), r.getIdRole());
		}

		String[] listTextBtns = {"Valider", "Annuler"};
		String[] listMethodeDoa = {"update", ""};

		utilisateurDao = new UtilisateurDao();

		//Nom de la jFrame à rouvrir après la modification
    String jFrameClassName = "vues.utilisateur.FicheListUtilisateur";

		new FicheUtilisateur(
				"Modification d'un utilisateur",
				utilisateurDao,
			  	(Object) utilisateur,
				listLabels,
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				jComboBoxTitles,
				jFrameClassName
				);
	}
}
