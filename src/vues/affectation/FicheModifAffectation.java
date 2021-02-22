package vues.affectation;

import dao.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import models.Affectation;
import models.*;
import vues.FicheAffectation;

public class FicheModifAffectation extends JFrame {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  AffectationDao bdao;

  /**
   * Create the frame.
   */
  public FicheModifAffectation(Affectation af) {
    //Ajout des labels
    String[] listLabels = {
      "Id de l'affectation",
      "Numéro du vol",
      "Dat du vol",
      "Code de l'affectation",
      "Numéro de l'avion",
      "Pilote",
    };
    //Ajout des text fields pre-remplis
    String[] listTextFields = {
      af.getId(),
      af.getNumVol(),
      af.getDateVol().toString(),
      af.getAffectationCode().toString(),
      "" + af.getNumAvion(),
      af.getPilote().getNomPilote(),
    };

    //Ajout des boutons
    String[] listTextBtns = { "Valider", "Annuler" };

		//Ajout de la methode
	  String[] listMethodeDoa = { "update", "" };
    
    //Ajout de la dao
    bdao = new AffectationDao();

    //Ajout des listes
    ArrayList<Pilote> 	listePilotes 	= new PiloteDao().getAll();
		ArrayList<Vol> 		listeVols 		= new VolDao().getAll();
		ArrayList<Avion> 	listeAvions 	= new AvionDao().getAll();

    //ajout des combo box qui va recevoir les listes
		String[] jComboBoxPiloteTitles 		= new String[listePilotes.size()];
		String[] jComboBoxNumVolTitles 		= new String[listeVols.size()];
		String[] jComboBoxNumAvionTitles 	= new String[listeAvions.size()];

    //Insertion des listes dans les combobox
		int i = 0;
		for (Pilote p : listePilotes) {
			jComboBoxPiloteTitles[i] = p.getNomPilote();
			i++;
		}

		i = 0;
		for (Vol v : listeVols) {
			jComboBoxNumVolTitles[i] = v.getNumVol();
			i++;
		}

		i = 0;
        for (Avion av : listeAvions) {
          jComboBoxNumAvionTitles[i] = "" + av.getNumAvion();
          i++;
        }

    //Nom de la jFrame à rouvrir après la modification
    String jFrameClassName = "vues.affectation.ListeModifAffectations";

    //Creation de la fiche avec les elements ajouté au dessus
    new FicheAffectation(
      "Ajout d'une affectation",
      bdao,
      (Object) af,
      listLabels,
      listTextFields,
      listTextBtns,
      listMethodeDoa, 
      jComboBoxPiloteTitles, 
      jComboBoxNumVolTitles, 
      jComboBoxNumAvionTitles,
      jFrameClassName
      );
  }
}
