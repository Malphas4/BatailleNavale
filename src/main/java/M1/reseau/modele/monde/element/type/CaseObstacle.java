package M1.reseau.modele.monde.element.type;

import M1.reseau.modele.monde.element.Case;

public class CaseObstacle extends Case {
    /**
     * Constructeur d'une case en fonction d'une position (x,y)
     *
     * @param _x
     * @param _y
     */
    public CaseObstacle(int _x, int _y) {
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
}
