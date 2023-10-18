package M1.reseau.modele.monde.element;

import M1.reseau.modele.monde.element.visitor.IVisitorCase;

public interface ICase {
    void accepte(IVisitorCase _ivc);
}
