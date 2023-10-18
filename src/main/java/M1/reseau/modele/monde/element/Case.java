package M1.reseau.modele.monde.element;

import M1.reseau.modele.monde.element.visitor.IVisitorCase;

import java.util.Objects;

public abstract class Case implements ICase {
    /**
     * Déclaration des variables
     */
    private int _x;
    private int _y;

    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     * @param _x
     * @param _y
     */
    public Case(int _x, int _y) {
        set_x(_x);
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        if (_x < 1) throw new IllegalArgumentException("CaseNormal : x est inférieur à 1.");
        this._x = _x;
    }

    public int get_y() {
        return this._y;
    }

    public void set_y(int _y) {
        if (_y < 1) throw new IllegalArgumentException("CaseNormal : y est inférieur à 1.");
        this._y = _y;
    }

    @Override
    public void accepte(IVisitorCase _ivc) {
        _ivc.visite(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return _x == aCase._x && get_y() == aCase.get_y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, get_y());
    }
}
