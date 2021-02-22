package vues.vol;

import java.util.ArrayList;

import javax.swing.JFrame;

// import des daos
import dao.*;

// import des models
import models.*;

// import des fiches génériques
import vues.*;


public class FicheAjoutVol extends JFrame {

	private static final long serialVersionUID = 1L;

	VolDao bdao;

	/**
	 * Creation de la frame d'ajout d'un vol.
	 */
	public FicheAjoutVol(Vol a) {

		// Tableau de la liste des vols
		String[] listLabels = {"Id du vol", "Aeroport de depart", "Heure de depart",
				"Aeroport d'arrivé", "Heure d'arrivé"};

		// Tableau des TextFields
		String[] listTextFields = {"", "", "", "", ""};

		// Tableau des boutons
		String[] listTextBtns = {"Valider", "Annuler"};

		// Tableau des méthodes
		String[] listMethodeDoa = {"save", ""};
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
		String jFrameClassName = "vues.aeropo";

		new FicheVol(
				"Ajout d'un vol",
				bdao, (Object) a,
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
