package M1.reseau.model.world.element.state;

import M1.reseau.model.world.element.CaseEtat;

public class CaseRate extends CaseEtat {
    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     *
     * @param _x
     * @param _y
     */
    public CaseRate(int _x, int _y) {
        super(_x, _y);
    }

    @Override
    public String toString() {
        return "CaseRate{" + super.toString() + "}";
    }
}
