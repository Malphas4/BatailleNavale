package M1.reseau.modele.bateau.type;

import M1.reseau.modele.bateau.Bateau;
import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;
import M1.reseau.modele.monde.grille.IGrille;

import java.util.List;

public class BateauLigne extends Bateau {

    private int _maxCase;

    public BateauLigne(int _maxCase) {
        set_maxCase(_maxCase);
    }

    public int get_maxCase() {
        return _maxCase;
    }

    public void set_maxCase(int _maxCase) {
        if (_maxCase < 0) throw new IllegalArgumentException("BateauLigne : Le maximum de case doit être supérieur à 0.");
        this._maxCase = _maxCase;
    }

    /**
     *
     * @param _grille
     * @return
     */
    @Override
    public boolean isSurLaGrille(IGrille _grille) {
        for (ICase _case : get_listeCase()) {
            if (!_grille.get_listeCase().contains(_case)) return false;
        }
        return true;
    }

    @Override
    public void ajouterCase(ICase _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("Bateau1Case ; Le bateau ne peut pas être null.");
        if (get_listeCase().size() >= get_maxCase()) throw new IBateauException("Bateau1Case : Le bateau ne peut pas avoir plus de 1 case maximum.");
        get_listeCase().add(_case);
    }
}
