package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseRate;

public class FabriqueCaseRate implements IFabriqueCase {
    /**
     *
     * @param _x
     * @param _y
     * @return
     */
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseRate(_x, _y);
    }
}
