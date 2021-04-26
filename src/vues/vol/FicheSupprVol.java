package vues.vol;

import javax.swing.JFrame;

// import de VolDao
import Dao.VolDao;

// import du model Vol
import Models.Vol;
import vues.Fiche;
// Import de Fiche générique avec le premier textField disabled

public class FicheSupprVol extends JFrame {

	private static final long serialVersionUID = 1L;

	VolDao bdao;

	/**
	 * Création de la frame de suppression de vol.
	 */
	public FicheSupprVol(Vol v) {

		// Tableau des labels
		String[] listLabels = {"Id du Vol à supprimer"};

		// Tableau des textFields
		String[] listTextFields = {"" + v.getNumVol()};

		// Tableau des boutons
		String[] listTextBtns = {"Valider", "Annuler"};

		// Tableau des méthodes
		String[] listMethodeDoa = {"delete", ""};
		bdao = new VolDao();

		new Fiche(
				"Suppression d'un Vol",
				bdao,
				(Object) v,
			   	listLabels,
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				"",
				true
			);
	}
}
