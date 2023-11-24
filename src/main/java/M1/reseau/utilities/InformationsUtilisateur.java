package M1.reseau.utilities;

public class InformationsUtilisateur {

    private static InformationsUtilisateur _informationsUtilisateur;

    /* Informations utilisateurs */
    private String _pseudo;
    private int _salon;
    int _bot=1;

    //1 pour multi, 0 pour solo
    private int _typePartie;
    private InformationsUtilisateur() {
        _salon=-1;
    }

    public static InformationsUtilisateur getInstance() {
        if (_informationsUtilisateur == null) {
            _informationsUtilisateur = new InformationsUtilisateur();
        }
        return _informationsUtilisateur;
    }

    public String get_pseudo() {
        return _pseudo;
    }

    public void set_pseudo(String _pseudo) {
        if (_pseudo == null) throw new IllegalArgumentException("InformationsUtilisateur : Le pseudo ne peut pas être null.");
        if (_pseudo.trim().isEmpty()) throw new IllegalArgumentException("InformationsUtilisateur : Le pseudo ne peut pas être vide.");
        this._pseudo = _pseudo;
    }
    public int get_typePartie() {
        return _typePartie;
    }

    public void set_typePartie(int typePartie) {
        if (_typePartie>=2||_typePartie<0) throw new IllegalArgumentException("InformationsUtilisateur : Le type de partie ne peut pas être différent de 1 ou 0.");
        this._typePartie = typePartie;
    }

    public int get_salon() {
        return _salon;
    }

    public void set_salon(int salon) {
        this._salon = salon;
    }

    public void set_bot(int bot) {
        this._bot = bot;
    }

    public int get_bot() {
        return _bot;
    }
}
