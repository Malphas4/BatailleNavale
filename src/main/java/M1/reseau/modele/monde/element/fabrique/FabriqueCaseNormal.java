package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseNormal;

public class FabriqueCaseNormal implements IFabriqueCase {
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseNormal(_x, _y);
    }
}
