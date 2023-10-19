package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseRocher;

public class FabriqueCaseRocher implements IFabriqueCase {
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseRocher(_x, _y);
    }
}
