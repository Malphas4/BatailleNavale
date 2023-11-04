package M1.reseau.modele.bateau;

import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.grille.Grille;
import M1.reseau.modele.monde.grille.IGrille;

import java.util.List;
import java.util.Objects;

public class Bateau implements IBateau {

    private List<ICase> _listCase;

    public Bateau() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bateau bateau = (Bateau) o;
        return Objects.equals(_listCase, bateau._listCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_listCase);
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
        get_listeCase().add(_case);
    }
}
