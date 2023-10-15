package M1.reseau.modele.monde.element.type;

import M1.reseau.modele.monde.element.Case;

public class CaseNormal extends Case {
    /**
     * Définition des status des cases
     */
    enum EStatutCase {
        DEFAUT,
        TOUCHE,
        RATE,
        BATEAU
    }

    private EStatutCase _statut = EStatutCase.DEFAUT;

    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     * @param _x
     * @param _y
     */
    public CaseNormal(int _x, int _y) {
        super(_x, _y);
        setStatut(getDefaultStatut());
    }

    public void setStatut(EStatutCase _statut) {
        if (_statut == null) throw new IllegalArgumentException("CaseNormal : Le statut ne doit pas être null.");
        this._statut = _statut;
    }

    public EStatutCase getDefaultStatut() {
        return EStatutCase.DEFAUT;
    }

    public EStatutCase getToucheStatut() {
        return EStatutCase.TOUCHE;
    }

    public EStatutCase getRateStatut() {
        return EStatutCase.RATE;
    }

    public EStatutCase getBateauStatut() {
        return EStatutCase.BATEAU;
    }
}
