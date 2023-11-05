package M1.reseau.modele.bateau.type;

import M1.reseau.modele.bateau.Bateau;
import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;

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

    @Override
    public void add_case(ICase _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("Bateau1Case ; Le bateau ne peut pas être null.");
        if (get_listeCase().size() >= get_maxCase()) throw new IBateauException("Bateau1Case : Le bateau ne peut pas dépasser le nombre de case maximum.");
        if (get_listeCase().contains(_case)) throw new IBateauException("Bateau : La case est déjà dans la liste.");
        get_listeCase().add(_case);
    }
}
