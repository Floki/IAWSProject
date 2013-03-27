package inscription;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import openstreetmap.Coordonnee;
import openstreetmap.OpenStreetMap;

public class Inscrit {
    private String nom;
    private String prenom;
    private String mail;
    private String adresse;
    private Coordonnee coordonnee;
    private static Map<String, Inscrit> inscrits = new HashMap<String, Inscrit>();
    private Inscrit(String nom, String prenom, String mail, String adresse, Coordonnee coordonnee) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.coordonnee = coordonnee;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }
    public static Inscrit getInscrit(String id) {
        if (inscrits.containsKey(id)) {
            return inscrits.get(id);
        }
        return null;
    }
    public static Collection<Inscrit> getListeInscrit() {
        return inscrits.values();
    }
    public String toString() {
        return "{"
             + "  \"nom\": \"" + getNom() + "\","
             + "  \"prenom\": \"" + getPrenom() + "\","
             + "  \"email\": \"" + getMail() + "\","
             + "  \"adresse\": \"" + getAdresse() + "\","
             + "  \"coordonne\": {"
             + "    \"latitude\": \"" + getCoordonnee().getLat() + "\","
             + "    \"longitude\": \"" + getCoordonnee().getLng() + "\""
             + "   }"
             + " }";
    }
    public static String nouvelInscrit(String nom, String prenom, String mail, String adresse) {
        if (!isEmailUps(mail)) {
            //code erreur 110 : Adresse email invalide ;
            return message("KO", 110, "Adresse email invalide.");
        }
        if (inscrits.containsKey(mail)) {
            //code erreur 100 : Adresse email déjà utilisée ;
            return message("KO", 100, "Adresse email déjà utilisée.");
        }
        Coordonnee coordonnee = OpenStreetMap.parse(adresse);
        if (coordonnee.getLat() == 0 && coordonnee.getLng() == 0) {
            //code erreur 200 : Adresse postale non connue de Open Street Map.
            return message("KO", 200, "Adresse postale non connue dans Open Street Map.");
        }
        inscrits.put(mail, new Inscrit(nom, prenom, mail, adresse, coordonnee));
        System.out.println("Inscription " + mail + " Réussie!");
        return message("OK", 0, "Inscription réussie.");
    }
    private static boolean isEmailUps(String email){
         return Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", email)
             && email.endsWith("@univ-tlse3.fr");
    }
    private static String message (String etat, int code, String message) {
        if (etat == "OK") {
            return "{"
                +    "\"etat\": \"" + etat + "\","
                +    "\"code\": \"" + code + "\","
                +    "\"message\": \"" + message + "\""
                +  "}";
        }
        else {
            return "{"
                    +    "\"etat\": \"" + etat + "\","
                    +    "\"code\": \"" + code + "\","
                    +    "\"message\": \"" + message + "\""
                    +  "}";
        }
        
    }
    public static void main(String [] args) {
        String retour; 
        retour = nouvelInscrit( "Florian"
                              , "Clavel"
                              , "florian.clavel@univ-tlse3.fr"
                              , "29 rue Pablo Picasso 09600 Laroque d'Olmes");
        System.out.println(retour);
        retour = nouvelInscrit( "George"
                                , "Whashington"
                                , "florian.clavel@univ-tlse3.fr"
                                , "29 rue Pablo Picasso 09600 Laroque d'Olmes");
        System.out.println(retour);
        retour = nouvelInscrit( "George"
                                , "Whashington"
                                , "florian.clavel@fr"
                                , "29 rue Pablo Picasso 09600 Laroque d'Olmes");
        System.out.println(retour);
        retour = nouvelInscrit( "George"
                , "Whashington"
                , "george.washington@univ-tlse3.fr"
                , "Allons enfant de la patrieeeeeee");
        System.out.println(retour);
    }
}
