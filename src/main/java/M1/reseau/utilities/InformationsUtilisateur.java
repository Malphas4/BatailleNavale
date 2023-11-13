package M1.reseau.utilities;

public class InformationsUtilisateur {

    private static InformationsUtilisateur _informationsUtilisateur;

    /* Informations utilisateurs */
    private String _pseudo;

    private InformationsUtilisateur() {
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

}
