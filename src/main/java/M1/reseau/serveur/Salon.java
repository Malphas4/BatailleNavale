package M1.reseau.serveur;

public class Salon implements ISalon {

    public String _nom="";
    String _description="";
    int _nbMaxJoueur=2;
    int _nbConnecte=0;


    public  Salon(String nom, String description, int nbMax) {
        this._nom = nom;
        this._description = description;
        this._nbMaxJoueur = nbMax;
        this._nbConnecte = 0;
    }
    public  Salon() {
        this._nom = "NomDeBase";
        this._description = "partie classique";
;
    }

    public String getNom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public String getDescription() {
        return _description;
    }

    public int getNbMax() {
        return _nbMaxJoueur;
    }

    public int getNbConnecte() {
        return _nbConnecte;
    }

    public void setNbConnecte(int nbConnecte) {
        this._nbConnecte = nbConnecte;
    }

    public void incrementNbConnecte() {
        this._nbConnecte++;
    }

    public void decrementNbConnecte() {
        this._nbConnecte--;
    }
}
