package M1.reseau.model.ship;

import M1.reseau.model.exception.IBateauException;
import M1.reseau.model.world.element.ICase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Bateau implements IBateau {

    private UUID _uuid = UUID.randomUUID();
    private List<ICase> _listeCase;

    public Bateau() {
        set_listeCase(new ArrayList<>());
    }

    /**
     *
     * @return
     */
    public UUID get_uuid() {
        return _uuid;
    }

    /**
     *
     * @param _uuid
     */
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

    @Override
    public String toString() {
        return "Bateau{" +
                "_uuid=" + _uuid +
                ", _listeCase=" + _listeCase +
                '}';
    }
}
