package M1.reseau.modele.jeu;

import M1.reseau.modele.joueur.IJoueur;

import java.util.ArrayList;
import java.util.List;

public abstract class Partie implements IPartie {
    private int _nbTour;
    private List<IJoueur> _joueurs = new ArrayList<>();

    public Partie(String pseudo) {
        set_nbTour(0);
    }

    public Partie(int _nbjoueur) {
        set_nbTour(0);
    }

    public int get_nbTour() {
        return _nbTour;
    }

    public void set_nbTour(int _nbTour) {
        if (_nbTour < 0) throw new IllegalArgumentException("Partie : Le nombre de tour doit être supérieur ou égal à 0.");
        this._nbTour = _nbTour;
    }

    public List<IJoueur> get_joueurs() {
        return _joueurs;
    }

    public void set_joueurs(List<IJoueur> _joueurs) {
        this._joueurs = _joueurs;
    }
}
