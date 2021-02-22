package vues.vol;

import java.util.ArrayList;

import javax.swing.JFrame;

// import des daos
import dao.*;

// import des models
import models.*;

// Import de Fiche vol générique
import vues.*;


public class FicheModifVol extends JFrame {

	private static final long serialVersionUID = 1L;

	VolDao bdao;

	/**
	 * Creation de la frame de modification d'un vol.
	 */
	public FicheModifVol(Vol v) {

		// Tableau des labels
		String[] listLabels = {
				"Id du vol",
				"Aeroport de depart",
			  	"Heure de depart",
				"Aeroport d'arrivé'",
				"Heure d'arrivé'"
			};

		// tableau des textFields
		String[] listTextFields = {
				v.getNumVol(),
				v.getAeroportDepart(),
				v.getHeureDepart(),
				v.getAeroportArrive(), 
				v.getHeureArrive()
			};

		// Tableau des boutons
		String[] listTextBtns = {"Valider", "Annuler"};

		// Tableau des méthodes
		String[] listMethodeDoa = {"update", ""};
		bdao = new VolDao();

		ArrayList<Aeroport> listeDept = new AeroportDao().getAll();
		ArrayList<Aeroport> listeArr = new AeroportDao().getAll();

		String[] jComboBoxDeptTitles = new String[listeDept.size()];
		String[] jComboBoxArrTitles = new String[listeArr.size()];

		int i = 0;
		for (Aeroport aeroport : listeDept) {
		jComboBoxDeptTitles[i] = aeroport.getIdAeroport();
		i++;
		}

		i = 0;
		for (Aeroport aeroport : listeArr) {
			jComboBoxArrTitles[i] = aeroport.getIdAeroport();
			i++;
		}

		//Nom de la jFrame à rouvrir après la modification
    String jFrameClassName = "vues.vol.ListeVol";

		new FicheVolUpdate(
				"Modification d'un vol",
				bdao,
				(Object) v,
				listLabels,
				listTextFields,
				listTextBtns,
				listMethodeDoa,
				jComboBoxDeptTitles,
				jComboBoxArrTitles,
				jFrameClassName
			);
	}
}
