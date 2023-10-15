package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.type.CaseNormal;
import M1.reseau.modele.monde.element.ICase;

public class FabriqueCaseNormal implements IFabriqueCase {
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseNormal(_x, _y);
    }
}
