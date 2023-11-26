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

    public void menuConnexion() {
        System.out.println("=============== Menu de connexion ===============");
        Scanner _scan = new Scanner();
        System.out.println("votre login : ");
        String _login = _scan.next();
        System.out.println("mot de passe : ");
        String _password = _scan.next();

        // TODO Vérificaiton de conenxion
    }

    public void menuInscription() {
        System.out.println("=============== Menu d'incription ===============");
        Scanner _scan = new Scanner(System.in);
        System.out.println("votre login : ");
        String _login = _scan.next();
        System.out.println("mot de passe : ");
        String _password = _scan.next();
    }

    public void menuSalon() {
        // TODO requête salon
        int _nbSalon = 10;

        for (int i = 0; i < _nbSalon; i++)
            System.out.println();
    }
}
