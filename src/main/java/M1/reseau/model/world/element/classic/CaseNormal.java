package M1.reseau.model.world.element.classic;

import M1.reseau.model.world.element.Case;

public class CaseNormal extends Case {

    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     * @param _x
     * @param _y
     */
    public CaseNormal(int _x, int _y) {
        super(_x, _y);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isPlacable() {
        return true;
    }

    @Override
    public boolean isTirable() {
        return true;
    }

    @Override
    public String toString() {
        return "CaseNormal{" + super.toString() + "}";
    }
}
