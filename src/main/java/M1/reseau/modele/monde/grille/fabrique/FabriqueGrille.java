package M1.reseau.modele.monde.grille.fabrique;

import M1.reseau.modele.exception.IGrilleException;
import M1.reseau.modele.monde.element.type.CaseNormal;
import M1.reseau.modele.monde.grille.Grille;
import M1.reseau.modele.monde.grille.IGrille;

public class FabriqueGrille implements IFabriqueGrille {

    private int _longueur;
    private int _largeur;

    public FabriqueGrille(int _longueur, int _largeur) {
        set_longueur(_longueur);
        set_largeur(_largeur);
    }

    public int get_longueur() {
        return _longueur;
    }

    public void set_longueur(int _longueur) {
        if (_longueur <= 0) throw new IllegalArgumentException("FabriqueGrille : La longueur doit être supérieur à 0.");
        this._longueur = _longueur;
    }

    public int get_largeur() {
        return _largeur;
    }

    public void set_largeur(int _largeur) {
        if (_largeur <= 0) throw new IllegalArgumentException("FabriqueGrille : La largeur doit être supérieur à 0.");
        this._largeur = _largeur;
    }

    @Override
    public IGrille creerGrille() {
        Grille _grille = new Grille(get_longueur(), get_largeur());

        for (int i = 1; i <= _longueur; i++) {
            for (int j = 1; j <= _largeur; j++) {
                CaseNormal _case = new CaseNormal(i, j);
                try {
                    _grille.ajouterCase(_case);
                } catch (IGrilleException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return _grille;
    }
}
