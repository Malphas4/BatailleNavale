package M1.reseau.model.game.distance;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.grid.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartieDistanceTest {

    private PartieDistance _partie;

    @BeforeEach
    void setUp() {
        _partie = new PartieDistance(8, 8);
    }

    @Test
    void set_joueur() {
        try {
            JoueurNormal _joueur = new JoueurNormal("Joueur",
                                                    (Grille) _partie.get_fabriqueGrille().creerGrille(),
                                                    (Grille) _partie.get_fabriqueGrille().creerGrille());
        } catch (IJoueurException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void set_fabriqueGrille() {
    }

    @Test
    void commencer() {
    }

    @Test
    void tourSuivant() {
    }

    @Test
    void getJoueurCourant() {
    }

    @Test
    void ajouterJoueur() {
    }

    @Test
    void fin() {
    }
}