package M1.reseau.model.scenario;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.game.local.PartieServeur;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorTirer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScenarioTest {

    /**
     * Scénario 1
     * Imaginons que nous soyons le serveur
     * - La partie est démarré sur un serveur
     */
    @Test
    void scenario1Test() {
        /* Création de la partie */
        PartieServeur _partie = new PartieServeur(8, 8);
        VisitorTirer _tire = new VisitorTirer();

        /**
         *  Imaginons : On reçoit les informations comme quoi le joueur 1 se connecte
         *  Méthode pour recevoir la première connexion => On reçoit son pseudonyme
         *  Méthode pour assigner le joueur à la partie
         */
        try {
            _partie.ajouterJoueur("Joueur 1");
            assertEquals("Joueur 1", _partie.get_joueur1().get_pseudo());
        } catch (IPartieException e) {
            fail();
        }

        /**
         * Imaginons : On reçoit les informations comme quoi le joueur 2 se connecte
         * Méthode pour recevoir la première connexion => On reçoit son pseudonyme
         * Méthode pour assigner le joueur à la partie
         */
        try {
            _partie.ajouterJoueur("Joueur 2");
            assertEquals("Joueur 2", _partie.get_joueur2().get_pseudo());
        } catch (IPartieException e) {
            fail();
        }

        /**
         * La partie est full, le serveur envoit un message pour dire que la partie est prête
         * La partie se met en mode commence
         * */
        try {
            _partie.commencer();
            assertTrue(_partie.is_commence());
        } catch (IPartieException e) {
            fail();
        }

        /**
         * C'est le tour du joueur 1
         * Le joueur 1 tire sur la case (2,2)
         * Le joueur 1 passe son tour
         */
        try {
            // On récupère le joueur courant
            JoueurNormal _joueur = (JoueurNormal) _partie.getJoueurCourant();
            assertEquals("Joueur 1", _joueur.get_pseudo());

            // On tire sur la case (2,2)
            _tire.set_case(_joueur.get_grilleTouche().get_caseParCoord(2, 2));
        } catch (IPartieException e) {
            fail();
        }

    }
}
