package M1.reseau.model.ship;

import M1.reseau.model.exception.IBateauException;
import M1.reseau.model.world.element.ICase;

import java.util.List;

public interface IBateau {
    List<ICase> get_listeCase();
    void set_listeCase(List<ICase> _listeCase);
    void add_case(ICase _case) throws IBateauException;
    void remove_case(ICase _case) throws IBateauException;
}
