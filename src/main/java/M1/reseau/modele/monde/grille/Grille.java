package M1.reseau.modele.monde.grille;

import M1.reseau.modele.bateau.IBateau;
import M1.reseau.modele.monde.element.ICase;

import java.util.ArrayList;
import java.util.List;

public abstract class Grille implements IGrille {
    private int _largeur;
    private int _longueur;

    private List<ICase> _cases = new ArrayList<>();
    private List<IBateau> _bateaux = new ArrayList<>();

    public Grille() {

    }

    public int get_largeur() {
        return _largeur;
    }

    public void set_largeur(int _largeur) {
        if (_largeur <= 0) throw new IllegalArgumentException("Grille : La largeur doit être supérieur à 0");
        this._largeur = _largeur;
    }

    public int get_longueur() {
        return _longueur;    }

    public void set_longueur(int _longueur) {
        if (_longueur <= 0) throw new IllegalArgumentException("Grille : La longueur doit être supérieur à 0.");
        this._longueur = _longueur;
    }

    public List<ICase> getCases() {
        return _cases;
    }

    public void setCases(List<ICase> _cases) {
        this._cases = _cases;
    }

    public List<IBateau> getBateaux() {
        return _bateaux;
    }

    public void setBateaux(List<IBateau> _bateaux) {
        this._bateaux = _bateaux;
    }
}
