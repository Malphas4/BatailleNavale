package M1.reseau.modele.monde.element;

import M1.reseau.modele.monde.element.visitor.IVisitorCase;

public interface ICase {
    int get_x();
    int get_y();
    boolean isPlacable();
    void accepte(IVisitorCase _ivc);
}
