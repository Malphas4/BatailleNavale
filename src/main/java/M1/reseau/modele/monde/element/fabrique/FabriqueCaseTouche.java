package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseRate;
import M1.reseau.modele.monde.element.type.CaseTouche;

public class FabriqueCaseTouche implements IFabriqueCase {
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseTouche(_x, _y);
    }
}
