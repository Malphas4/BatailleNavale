package M1.reseau.modele.monde.element.fabrique;

import M1.reseau.modele.monde.element.ICase;
import M1.reseau.modele.monde.element.type.CaseBateau;

public class FabriqueCaseBateau implements IFabriqueCase {

    /**
     *
     * @param _x
     * @param _y
     * @return
     */
    @Override
    public ICase creerCase(int _x, int _y) {
        return new CaseBateau(_x, _y);
    }

}
