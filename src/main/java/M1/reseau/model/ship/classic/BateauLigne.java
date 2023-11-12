package M1.reseau.model.ship.classic;

import M1.reseau.model.ship.Bateau;
import M1.reseau.model.exception.IBateauException;
import M1.reseau.model.world.element.ICase;

public class BateauLigne extends Bateau {

    private int _maxCase;

    public BateauLigne() {
        super();
        set_maxCase(1);
    }

    public BateauLigne(int _maxCase) {
        super();
        set_maxCase(_maxCase);
    }

    public int get_maxCase() {
        return _maxCase;
    }

    public void set_maxCase(int _maxCase) {
        if (_maxCase <= 0) throw new IllegalArgumentException("BateauLigne : Le maximum de case doit être supérieur à 0.");
        this._maxCase = _maxCase;
    }

    /**
     *
     * @param _case
     * @throws IBateauException
     */
    @Override
    public void add_case(ICase _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("BateauLigne ; Le bateau ne peut pas être null.");
        if (get_listeCase().size() >= get_maxCase()) throw new IBateauException("BateauLigne : Le bateau ne peut pas dépasser le nombre de case maximum.");
        if (get_listeCase().contains(_case)) throw new IBateauException("BateauLigne : La case est déjà dans la liste.");
        get_listeCase().add(_case);
    }

    /**
     *
     * @param _case
     * @throws IBateauException
     */
    @Override
    public void remove_case(ICase _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("BateauLigne : La case ne peut pas être null.");
        if (get_listeCase().isEmpty()) throw new IBateauException("BateauLigne : La liste de case ne peut pas être vide.");
        if (!get_listeCase().contains(_case)) throw new IBateauException("BateauLigne : La case n'est pas dans la liste.");
        get_listeCase().remove(_case);
    }
}
