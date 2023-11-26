package M1.reseau.client2;

import M1.reseau.client2.console.ClientTCP;
import M1.reseau.client2.console.ClientUDP;
import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.element.classic.CaseNormal;
import M1.reseau.model.world.element.state.CaseRate;
import M1.reseau.model.world.element.state.CaseTouche;
import M1.reseau.utilities.InformationsUtilisateur;

import java.util.Scanner;

public class ClientGlobal {

    private boolean _menuGlobal = true;
    private boolean _menuConnexion = false;
    private boolean _menuInscription = false;
    private boolean _menuSalon = false;
    private boolean _menuGame = false;

    private GameClient _game;

    private ClientTCP _clientTCP;
    private ClientUDP _clientUDP;

    private static ClientGlobal _clientGlobal;

    public ClientGlobal() {
        set_game(new GameClient());
    }

    public static void main(String[] args) {

    }

    public static ClientGlobal getInstance() {
        if (get_clientGlobal() == null) return new ClientGlobal();
        return get_clientGlobal();
    }

    public boolean is_menuGlobal() {
        return _menuGlobal;
    }

    public void set_menuGlobal(boolean _menuGlobal) {
        this._menuGlobal = _menuGlobal;
    }

    public boolean is_menuConnexion() {
        return _menuConnexion;
    }

    public void set_menuConnexion(boolean _menuConnexion) {
        this._menuConnexion = _menuConnexion;
    }

    public boolean is_menuInscription() {
        return _menuInscription;
    }

    public void set_menuInscription(boolean _menuInscription) {
        this._menuInscription = _menuInscription;
    }

    public boolean is_menuSalon() {
        return _menuSalon;
    }

    public void set_menuSalon(boolean _menuSalon) {
        this._menuSalon = _menuSalon;
    }

    public boolean is_menuGame() {
        return _menuGame;
    }

    public void set_menuGame(boolean _menuGame) {
        this._menuGame = _menuGame;
    }

    public GameClient get_game() {
        return _game;
    }

    public void set_game(GameClient _game) {
        this._game = _game;
    }

    public ClientTCP get_clientTCP() {
        return _clientTCP;
    }

    public void set_clientTCP(ClientTCP _clientTCP) {
        this._clientTCP = _clientTCP;
    }

    public ClientUDP get_clientUDP() {
        return _clientUDP;
    }

    public void set_clientUDP(ClientUDP _clientUDP) {
        this._clientUDP = _clientUDP;
    }

    public static ClientGlobal get_clientGlobal() {
        return _clientGlobal;
    }

    public static void set_clientGlobal(ClientGlobal _clientGlobal) {
        ClientGlobal._clientGlobal = _clientGlobal;
    }

    public void afficherGrille(JoueurNormal _joueur) {
        System.out.println("=============== Grille de " + _joueur.get_pseudo() + " ===============");
        System.out.print("\t");
        for (int i = 1; i <= _joueur.get_grilleJoueur().get_longueur(); i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int i = 1; i <= _joueur.get_grilleJoueur().get_longueur(); i++) {
            System.out.print("");
            for (int j = 1; j <= _joueur.get_grilleJoueur().get_largeur(); j++) {
                System.out.print(j);
                ICase _case = null;
                try {
                    _case = _joueur.get_grilleJoueur().get_caseParCoord(i, j);
                } catch (IGrilleException e) {
                    throw new RuntimeException(e);
                }
                if (_case instanceof CaseNormal) { // E = Eau
                    System.out.print("\tE");
                } else if (_case instanceof CaseBateau) { // B = Bateau
                    System.out.print("\tB");
                } else if (_case instanceof CaseRate) { // R = Raté
                    System.out.print("\tR");
                } else if (_case instanceof CaseTouche) { // T = Touché
                    System.out.println("\t");
                }
            }
        }

        System.out.println("=============== Grille de Touche ===============");
        System.out.print("\t");
        for (int i = 1; i <= _joueur.get_grilleTouche().get_longueur(); i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int i = 1; i <= _joueur.get_grilleTouche().get_longueur(); i++) {
            System.out.print("");
            for (int j = 1; j <= _joueur.get_grilleTouche().get_largeur(); j++) {
                System.out.print(j);
                ICase _case = null;
                try {
                    _case = _joueur.get_grilleTouche().get_caseParCoord(i, j);
                } catch (IGrilleException e) {
                    throw new RuntimeException(e);
                }
                if (_case instanceof CaseNormal) { // E = Eau
                    System.out.print("\tE");
                } else if (_case instanceof CaseBateau) { // B = Bateau
                    System.out.print("\tB");
                } else if (_case instanceof CaseRate) { // R = Raté
                    System.out.print("\tR");
                } else if (_case instanceof CaseTouche) { // T = Touché
                    System.out.println("\t");
                }
            }
        }
    }

    public void menuGlobal() {
        System.out.println("=============== Menu du global ===============");
        System.err.println("Pour quitter faire -1");
        System.out.println("0\\ Connexion");
        System.out.println("1\\ Inscription");
        System.out.println("2\\ Choix du salon");
        System.out.println("3\\ Chat Global");
        System.out.println("4\\ Entrer dans la partie");

        Scanner _scan = new Scanner(System.in);
        int _choice = 0;

        while (_choice <= -1) {
            _choice = _scan.nextInt();
            switch (_choice) {
                case 0 : {
                    menuConnexion();
                }
                case 1 : {
                    menuInscription();
                }
                case 2 : {
                    menuSalon();
                }
                case 3 : {

                }
                case 4: {
                    menuGame();
                }
                default:
                    System.err.println("La sélection n'est pas reconnue.");
            }
        }

    }

    public void menuConnexion() {
        System.out.println("=============== Connexion ===============");
        Scanner _scan = new Scanner(System.in);
        System.out.println("login : ");
        String _login = _scan.next();
        System.out.println("password : ");
        String _password = _scan.next();

        // TODO UDP connexion

    }

    public void menuInscription() {
        System.out.println("=============== Inscription ===============");
        Scanner _scan = new Scanner(System.in);
        System.out.println("login : ");
        String _login = _scan.next();
        System.out.println("password : ");
        String _password = _scan.next();

        // TODO UDP inscription
    }

    public void menuSalon() {
        System.out.println("=============== Menu de sélection de salon ===============");
        // TODO request salon
        int _nbSalon = 10;
        for (int i = 0; i < _nbSalon; i++) {
            System.out.println(i + " - Room(" + i + ")");
        }
        Scanner _scan = new Scanner(System.in);
        int _choice = _scan.nextInt();

        // TODO join le salon
        get_clientTCP().message(
                "join;"
                + _choice
                + ";" + InformationsUtilisateur.getInstance().get_pseudo()
        );
    }

    public void menuGame() {
        System.out.println("=============== Menu du jeu ===============");
        System.out.println("0\\ Initialisation de la grille");
        System.out.println("1\\ Afficher la grille");
        System.out.println("2\\ Tirer une case");
        System.out.println("3\\ Quitter");

        Scanner _scan = new Scanner(System.in);
        int _choice = _scan.nextInt();

        switch (_choice) {
            case 1 : {

            }
            case 2 : {

            }
            case 3 : {

            }
            default:
                System.err.println("Le choix est incorrecte.");
        }
    }

}
