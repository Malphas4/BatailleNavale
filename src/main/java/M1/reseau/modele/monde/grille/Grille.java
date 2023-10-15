package M1.reseau.modele.monde.grille;

import M1.reseau.modele.bateau.IBateau;
import M1.reseau.modele.monde.element.ICase;

import java.util.ArrayList;
import java.util.List;

public abstract class Grille implements IGrille {
    private List<ICase> _cases = new ArrayList<>();
    private List<IBateau> _bateaux = new ArrayList<>();

    public Grille() {

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
