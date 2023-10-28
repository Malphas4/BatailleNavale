package M1.reseau.modele.bateau;

import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;
import M1.reseau.modele.monde.grille.IGrille;

import java.util.List;

public abstract class Bateau implements IBateau {

    private int _orientation;
    private List<ICase> _listCase;

    /**
     *
     * @param _orientation
     */
    public Bateau(int _orientation) {
        set_orientation(_orientation);
    }

    /**
     *
     * @param _grille
     * @param _orientation
     * @param _listCase
     * @throws IBateauException
     */
    public Bateau(Grille _grille, int _orientation, List<ICase> _listCase) throws IBateauException {
        set_orientation(_orientation);
        set_case(_grille, _listCase);
    }

    /**
     *
     * @return
     */
    public int get_orientation() {
        return _orientation;
    }

    /**
     *
     * @param _orientation
     */
    public void set_orientation(int _orientation) {
        if (_orientation != 0 && _orientation != 1) throw new IllegalArgumentException("Bateau : L'orientation du bateau doit être 0 (vertical) ou 1 (horizontal).");
        this._orientation = _orientation;
    }

    /**
     *
     * @return
     */
    public List<ICase> get_listeCase() {
        return _listCase;
    }

    /**
     *
     * @param _grille
     * @param _case
     * @throws IBateauException
     */
    public void set_case(Grille _grille, List<ICase> _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("Bateau : La case d'ancrage ne peut pas être vide");
        if (!isSurLaGrille(_grille)) throw new IBateauException("Bateau : La case doit être dans la grille.");
        this._listCase = _case;
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

    public abstract int get_maxCase();
}
