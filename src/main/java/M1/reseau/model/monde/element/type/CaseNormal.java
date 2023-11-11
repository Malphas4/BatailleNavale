package M1.reseau.model.monde.element.type;

import M1.reseau.model.monde.element.Case;

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

}
