package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseObstacle;

public class FabriqueCaseObstacle implements IFabriqueCase {
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseObstacle(_x, _y);
    }
}
