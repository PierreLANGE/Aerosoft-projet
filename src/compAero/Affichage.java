package compAero;

import java.sql.SQLException;
import java.util.List;

import Models.Aeroport;
import Models.Affectation;
import Models.Pilote;
import Models.Vol;

import java.sql.ResultSet;

public class Affichage
{
    public void menu() {
        System.out.println("|----------------------------------------|");
        System.out.println("|          --=<{[ AeroSoft ]}>=--        |");
        System.out.println("|----------------------------------------|");
        System.out.println("| Pour:                                  |");
        System.out.println("|                                        |");
        System.out.println("| Ajouter un Pilote                  (1) |");
        System.out.println("| La liste des Vols                  (2) |");
        System.out.println("| Rechercher un Vol                  (3) |");
        System.out.println("| La liste des Aeroports par Ville   (4) |");
        System.out.println("| Modifier la date d'une Affectation (5) |");
        System.out.println("| Supprimer une Affectation          (6) |");
        System.out.println("|                                        |");
        System.out.println("| Quitter                            (7) |");
        System.out.println("|----------------------------------------|");
    }
    
    public void titre(final String titre,int sleep) {
        final double diffEsp = 40 - titre.length();
        final int espBefore = (int)diffEsp / 2;
        final float a = (float)diffEsp / 2.0f;
        final int espAfter = Math.round(a);
        final String titreConstruit = String.valueOf(this.construitEsp(espBefore)) + titre + this.construitEsp(espAfter);
        System.out.println(" ");
        System.out.println("|----------------------------------------|");
        System.out.println("|" + titreConstruit + "|");
        System.out.println("|----------------------------------------|");
        System.out.println(" ");
        try {
			Thread.currentThread().sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public void titreVol(final int nbLigne) {
        if (nbLigne == 1) {
            this.titre("Liste du Vol trouv\u00e9",0);
        }
        else if (nbLigne > 1) {
            this.titre("Liste des Vols",0);
        }
        else if (nbLigne < 1) {
            this.titre("Pas de vol trouv\u00e9",0);
        }
    }
    
    public void ligneVol( List<Vol> vols) {
		
    	for (Vol vol : vols) {
            final String numVol = vol.getNumVol();
            final String aeroportDept = vol.getAeroportDept();
            final String hDepart = vol.gethDepart().toString();
            final String aeroportArr = vol.getAeroportArr();
            final String hArrivee = vol.gethArrivee().toString();
            
            System.out.print("N\u00B0: " + numVol);
            System.out.print(", D\u00e9part  \u00e0: " + hDepart);
            System.out.println(" de  l'ar\u00e9oport de " + aeroportDept);
            System.out.print(String.valueOf(this.construitEsp(11)) + "Arriv\u00e9e \u00e0: " + hArrivee);
            System.out.println(" sur l'ar\u00e9oport de " + aeroportArr + "\n");
        }
    }
    
    public void lignePilotes(List<Pilote> pilotes){
    	
    	System.out.println("lignePilotes pilotes.size() : " + pilotes.size());
        int ecartId = 0;
        int espAfter = 0;
        for (Pilote p : pilotes)  {
            int idPilote = p.getIdPilote();
            String prenomPilote = p.getPrenomPilote();
            String nomPilote = p.getNomPilote();
            if (idPilote < 10) {
                ecartId = 1;
            }
            else {
                ecartId = 0;
            }
            espAfter = 8 - prenomPilote.length();
            
            System.out.print("id: " + this.construitEsp(ecartId) + idPilote);
            System.out.print(", Pr\u00e9nom: " + prenomPilote + this.construitEsp(espAfter));
            System.out.println(", Nom: " + nomPilote);
        }
        System.out.println(" ");
    }
    //public void ligneAeroport(final ResultSet rs) throws SQLException {
    public void ligneAeroport(List<Aeroport> aeroports) {
        int espAfter = 0;
        for (Aeroport aeroport :aeroports) {
            final String idAeroport = aeroport.getIdAeroport();
            final String nomAeroprt = aeroport.getNomAeroport();
            final String nomVilleDesservie = aeroport.getNomVilleDesservie();
            
            espAfter = 16 - nomAeroprt.length();
            System.out.print("id: " + idAeroport);
            System.out.print(", Aeroprt: " + nomAeroprt + this.construitEsp(espAfter));
            System.out.println(", Ville desservie: " + nomVilleDesservie);
        }
        System.out.println(" ");
    }
    
    //public void ligneAffectation(final ResultSet rs) throws SQLException {
	public void ligneAffectation(List<Affectation> affectations) throws SQLException {
        int espAfter = 0;
        int i= 1;
        
        //System.out.println("nb enr in affectations on af.ligneAffectation:" + affectations.size());
        
        for (Affectation affectation : affectations) {
            final String numVol = affectation.getNumVol();
            final String dateVol = affectation.getDateVol().toString();
            final String numAvion = affectation.getNumAvion();
            
            Pilote p = affectation.getPilote();
            
            
            final String prenomPilote = p.getPrenomPilote();
            final String nomPilote = p.getNomPilote();
            
            System.out.printf("(%-2s) ", i);
            
            espAfter = 5 - numAvion.length();
            
            System.out.print("N\u00B0" + numVol);            
            
            System.out.print(", Affect\u00e9 le: " + dateVol);
            System.out.print(" sur l'avion: " + numAvion + this.construitEsp(espAfter));
            
            espAfter = 8 - prenomPilote.length();
            System.out.println(" pilot\u00e9 par : " + prenomPilote + this.construitEsp(espAfter) + nomPilote);
            System.out.println(" ");
            
            i++;
        }
    }
    
    private String construitEsp(final int nbEspace) {
        String espaces = "";
        for (int i = 0; i != nbEspace; ++i) {
            espaces = String.valueOf(espaces) + " ";
        }
        return espaces;
    }
}
