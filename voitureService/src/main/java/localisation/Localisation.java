package localisation;

import java.util.Collection;

import inscription.Inscrit;

public class Localisation {
    public static String localisation(String id, int rayon) {
        String retour = "";
        Inscrit reference = Inscrit.getInscrit(id);
        Collection<Inscrit> listInscrits = Inscrit.getListeInscrit();
        for(Inscrit inscrit: listInscrits) {
            if( inscrit.getCoordonnee().distanceWith(reference.getCoordonnee()) < rayon 
            &&  !inscrit.getMail().equals(reference.getMail())) {
                retour += inscrit;
            }
        }
        return retour;
    }
    
    public static void main(String[] args) {
        Inscrit.nouvelInscrit("Ours", "Ariegeois1", "1@univ-tlse3.fr", "Laroque d'olmes");
        Inscrit.nouvelInscrit("Bambino", "Ariegeois2", "2@univ-tlse3.fr", "MontFerrier");
        Inscrit.nouvelInscrit("Suki", "Ariegeois3", "3@univ-tlse3.fr", "Lavelanet");
        Inscrit.nouvelInscrit("Loukas", "Ariegeois4", "4@univ-tlse3.fr", "Dun");
        Inscrit.nouvelInscrit("Dante", "Tarnais1", "5@univ-tlse3.fr", "Mazamet");
        Inscrit.nouvelInscrit("Boffbad", "Toulousain1", "6@univ-tlse3.fr", "Toulouse");
        System.out.println(localisation("1@univ-tlse3.fr",25));
    }
}
