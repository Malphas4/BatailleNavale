package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseNormal;
import M1.reseau.modele.monde.element.type.CaseRate;

public class FabriqueCaseRate implements IFabriqueCase {
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseRate(_x, _y);
    }
}
