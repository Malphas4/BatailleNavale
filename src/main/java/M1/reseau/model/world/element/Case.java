package M1.reseau.model.world.element;

import java.util.Objects;
import java.util.UUID;

public abstract class Case implements ICase {
    /**
     * Déclaration des variables
     */
    private UUID _uuid = UUID.randomUUID();
    private int _x;
    private int _y;

    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     * @param _x
     * @param _y
     */
    protected Case(int _x, int _y) {
        set_x(_x);
        set_y(_y);
    }

    public UUID get_uuid() {
        return _uuid;
    }

    public void set_uuid(UUID _uuid) {
        this._uuid = _uuid;
    }

    /**
     *
     * @return
     */
    public int get_x() {
        return _x;
    }

    /**
     *
     * @param _x
     */
    public void set_x(int _x) {
        if (_x < 1) throw new IllegalArgumentException("CaseNormal : x est inférieur à 1.");
        this._x = _x;
    }

    /**
     *
     * @return
     */
    public int get_y() {
        return this._y;
    }

    @Override
    public boolean isPlacable() {
        return true;
    }

    /**
     *
     * @param _y
     */
    public void set_y(int _y) {
        if (_y < 1) throw new IllegalArgumentException("CaseNormal : y est inférieur à 1.");
        this._y = _y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return get_x() == aCase.get_x() && get_y() == aCase.get_y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_uuid(), get_x(), get_y());
    }

    @Override
    public String toString() {
        return "Case{" +
                "_uuid=" + _uuid +
                ", _x=" + _x +
                ", _y=" + _y +
                '}';
    }
}
