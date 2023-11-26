package M1.reseau.client2;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.element.classic.CaseNormal;
import M1.reseau.model.world.element.state.CaseRate;
import M1.reseau.model.world.element.state.CaseTouche;

import java.util.Scanner;

public class Client2 {

    private boolean _menuGlobal = true;
    private boolean _menuConnexion = false;
    private boolean _menuInscription = false;
    private boolean _menuSalon = false;
    private boolean _menuGame = false;

    public static void main(String[] args) {

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

    public void menuGame() {
        System.out.println("=============== Menu du jeu ===============");
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
