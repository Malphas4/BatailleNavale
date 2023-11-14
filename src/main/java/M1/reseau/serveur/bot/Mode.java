package M1.reseau.serveur.bot;

public class Mode {
    int _hauteur;
    int _largeur;

    void Mode(int hauteur, int largeur){
        _hauteur = hauteur;
        _largeur = largeur;
    }
    void Mode(){
        _hauteur = 0;
        _largeur = 0;
    }
    public void setHauteur(int hauteur) {
        _hauteur=hauteur;
    }

    public void setLargeur(int largeur) {
    _largeur=largeur;
    }

    public int getHauteur() {
        return _hauteur;
    }

    public int getLargeur() {
        return _largeur;
    }
}