package M1.reseau.modele.monde.element;

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

    public int getX() {
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
}
