package M1.reseau.modele.compteur;

public class CBateauTouche implements ICompteur {

    private int _nbTouche;

    public CBateauTouche(int _nbTouche) {
        set_nbTouche(_nbTouche);
    }

    public int get_nbTouche() {
        return _nbTouche;
    }

    public void set_nbTouche(int _nbTouche) {
        if (_nbTouche < 0) throw new IllegalArgumentException("CBateauTouche : Le nombre de touche doit être supérieur ou égal à 0.");
        this._nbTouche = _nbTouche;
    }

    @Override
    public void incremente(int i) {
        // TODO Incrémentation des bateaux touchés
    }

    @Override
    public void decremente(int i) {
        // TODO Décrémentation des bateaux touchés
    }
}
