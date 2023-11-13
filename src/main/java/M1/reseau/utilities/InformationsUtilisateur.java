package M1.reseau.utilities;

public class InformationsUtilisateur {

    private static InformationsUtilisateur _informationsUtilisateur;

    /* Informations utilisateurs*/

    private InformationsUtilisateur() {

    }

    public static InformationsUtilisateur getInstance() {
        if (_informationsUtilisateur == null) {
            _informationsUtilisateur = new InformationsUtilisateur();
        }
        return _informationsUtilisateur;
    }

    public static String setPseudo(String _pseudo) {

    }

}
