package vues.avion;

import java.util.ArrayList;

import javax.swing.JFrame;

import dao.*;
import models.*;
import vues.*;


public class FicheModifAvion extends JFrame {

	private static final long serialVersionUID = 1L;
	
	AvionDao bdao;

	/**
	 * Create the frame.
	 */
	public FicheModifAvion(Avion av) {
		//Ajout des labels
		String[] listLabels = { "Numéro avion", "Type avion", "Base aeroport"};
		
		//Ajout des text fields remplis avec les infos demandé
		String[] listTextFields = { ""+av.getNumAvion(),av.getTypeAvion(),av.getBaseAeroport()};
		
		//Ajout des boutons
		String[] listTextBtns = { "Valider","Annuler" };

		//Ajout de la methode
		String[] listMethodeDoa = { "update","" };

		//Ajout de la dao
		bdao = new AvionDao();
		ArrayList<DetailAvion> listeType = new DetailAvionDao().getAll();
		ArrayList<Aeroport> listeBase = new AeroportDao().getAll();

		String[] jComboBoxTypeTitles = new String[listeType.size()];
		String[] jComboBoxBaseTitles = new String[listeBase.size()];

		int i = 0;
		for (DetailAvion detailAvion : listeType) {
		jComboBoxTypeTitles[i] = detailAvion.getTypeAvion();
		i++;
		}

		i = 0;
		for (Aeroport aeroport : listeBase) {
			jComboBoxBaseTitles[i] = aeroport.getIdAeroport();
			i++;
		}

		//Nom de la jFrame à rouvrir après la modification
	    String jFrameClassName = "vues.avion.ListeModifAvion";

		new FicheAvion(
		"Modification d'un vol",
		bdao,
		(Object) av,
		listLabels,
		listTextFields,
		listTextBtns,
		listMethodeDoa,
		jComboBoxTypeTitles,
		jComboBoxBaseTitles,
		jFrameClassName
		);
	}
}
