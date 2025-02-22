package M1.reseau.model.world.element.classic;

import M1.reseau.model.world.element.Case;

public class CaseBateau extends Case {
    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     *
     * @param _x
     * @param _y
     */
    public CaseBateau(int _x, int _y) {
        super(_x, _y);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isPlacable() {
        return false;
    }

    @Override
    public boolean isTirable() {
        return true;
    }

    @Override
    public String toString() {
        return "CaseBateau{" + super.toString() + "}";
    }
}
