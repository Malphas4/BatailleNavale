package M1.reseau.model.world.element.state;

import M1.reseau.model.world.element.CaseEtat;

public class CaseTouche extends CaseEtat {
    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     * @param _x
     * @param _y
     */
    public CaseTouche(int _x, int _y) {
        super(_x, _y);
    }

    @Override
    public String toString() {
        return "CaseTouche{" + super.toString() + "}";
    }
}
