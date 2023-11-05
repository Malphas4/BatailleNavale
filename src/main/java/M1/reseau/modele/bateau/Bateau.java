package M1.reseau.modele.bateau;

import M1.reseau.modele.exception.IBateauException;
import M1.reseau.modele.monde.element.ICase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Bateau implements IBateau {

    private UUID _uuid = UUID.randomUUID();
    private List<ICase> _listeCase;

    public Bateau() {
        set_listeCase(new ArrayList<>());
    }

    public UUID get_uuid() {
        return _uuid;
    }

    public void set_uuid(UUID _uuid) {
        if (_uuid == null) throw new IllegalArgumentException("Bateau : Le uuid ne peut pas être null.");
        this._uuid = _uuid;
    }

    /**
     *
     * @return
     */
    public List<ICase> get_listeCase() {
        return _listeCase;
    }

    /**
     *
     * @param _case
     * @throws IBateauException
     */
    public void set_listeCase(List<ICase> _case) {
        if (_case == null) throw new IllegalArgumentException("Bateau : La case ne peut pas être vide");
        this._listeCase = _case;
    }

    public void add_case(ICase _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("Bateau : La case ne peut pas être null.");
        if (get_listeCase().contains(_case)) throw new IBateauException("Bateau : La case est déjà dans la liste.");
        get_listeCase().add(_case);
    }

    public void remove_case(ICase _case) throws IBateauException {
        if (_case == null) throw new IllegalArgumentException("Bateau : La case ne peut pas être null.");
        if (get_listeCase().isEmpty()) throw new IBateauException("Bateau : La liste de case ne peut pas être vide.");
        if (!get_listeCase().contains(_case)) throw new IBateauException("Bateau : La case n'est pas dans la liste.");
        get_listeCase().remove(_case);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bateau bateau = (Bateau) o;
        return Objects.equals(get_uuid(), bateau.get_uuid()) && Objects.equals(get_listeCase(), bateau.get_listeCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_uuid(), get_listeCase());
    }
}
