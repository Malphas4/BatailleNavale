package M1.reseau.model.scenario;

import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.game.local.PartieServeur;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorEstTire;
import M1.reseau.model.player.visitor.VisitorTirer;
import M1.reseau.model.world.element.ICase;
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
        VisitorEstTire _tireSubit = new VisitorEstTire();

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
         * - Le joueur 1 tire sur la case (2,2)
         * - Le joueur 1 passe son tour
         */
        try {
            /**
             * On récupère le joueur courant
             */
            JoueurNormal _joueur = (JoueurNormal) _partie.getJoueurCourant();
            assertEquals("Joueur 1", _joueur.get_pseudo());

            /**
             * Le serveur reçoit la requête pour tirer sur la case (2,2)
             * Le serveur récupère la case du joueur 2 (adverse) au coordonnée (2,2)
             * Le joueur 1 tire sur la case (2,2) du joueur 2
             * Le serveur envoie un message au joueur 1 et joueur 2 pour update le statut de la case (2,2)
             */
            // On récupère le joueur adverse
            JoueurNormal _joueurAdverse = (JoueurNormal) _partie.getJoueurAdverse();
            // On tire sur la grille de touche du joueur 1 (courant)
            _tire.set_grille(_joueurAdverse.get_grilleJoueur());
            // On récupère la case
            ICase _case = _joueurAdverse.get_grilleJoueur().get_caseParCoord(2, 2);
            _tire.set_case(_case);
            _joueur.accepte(_tire);

            // On tire sur la grille du joueur 2 (adverse) sur sa grille
            _tireSubit.set_grille(_joueurAdverse.get_grilleJoueur());
            _tireSubit.set_case(_case);
            _joueurAdverse.accepte(_tireSubit);

            // Le serveur envoit le message au joueur 1 et 2
            // Soit une update de la grille ou soit uniquement de la case

            // On vérifie si le joueur 1 a gagné
            // On ne le fait pas car on n'a placé aucun bateau sur cette partie
            /*if (_partie.gagnant()) {
                _partie.fin();
            }*/

            // Le joueur passe son tour
            _partie.tourSuivant();
            assertEquals(1, _partie.get_nbTour());
            assertEquals("Joueur 2", _partie.getJoueurCourant().get_pseudo());

        } catch (IPartieException | IGrilleException | IJoueurException e) {
            fail();
        }

        /**
         * Tour du joueur 2
         * Le joueur 2 tire sur la case (2,3)
         * Le joueur 2 passe son tour
         */
        try {
            /**
             * On récupère le joueur courant
             */
            JoueurNormal _joueur = (JoueurNormal) _partie.getJoueurCourant();
            assertEquals("Joueur 2", _joueur.get_pseudo());

            /**
             * Le serveur reçoit la requête pour tirer sur la case (2,2)
             * Le serveur récupère la case du joueur 2 au coordonnée (2,2)
             * Le joueur 1 tire sur la case (2,2) du joueur 2
             * Le serveur envoie un message au joueur 1 et joueur 2 pour update le statut de la case (2,2)
             */
            JoueurNormal _joueurAdverse = (JoueurNormal) _partie.getJoueurAdverse();
            // On tire sur la grille de touche du joueur 1 (courant)
            _tire.set_grille(_joueurAdverse.get_grilleJoueur());
            // On récupère la case
            ICase _case = _joueurAdverse.get_grilleJoueur().get_caseParCoord(2, 3);
            _tire.set_case(_case);
            _joueur.accepte(_tire);

            // On tire sur la grille du joueur 2 (adverse) sur sa grille
            _tireSubit.set_grille(_joueurAdverse.get_grilleJoueur());
            _tireSubit.set_case(_case);
            _joueurAdverse.accepte(_tireSubit);

            // Le serveur envoit le message au joueur 1 et 2
            // Soit une update de la grille ou soit uniquement de la case

            // Le joueur passe son tour
            _partie.tourSuivant();
            assertEquals(2, _partie.get_nbTour());
            assertEquals("Joueur 1", _partie.getJoueurCourant().get_pseudo());

        } catch (IPartieException | IGrilleException | IJoueurException e) {
            fail();
        }

        /**
         * La partie continue et le joueur 1 gagne
         */
        // On modifie artificiellement la valeur de gagner pour le joueur 1 car
        // la partie n'est pas réellement terminé, on le ferait normalement avec la méthode gagnant()
        _partie.set_aGagner1(true);

        // On termine la partie
        try {
            _partie.fin();
        } catch (IPartieException e) {
            fail();
        }
    }
}
