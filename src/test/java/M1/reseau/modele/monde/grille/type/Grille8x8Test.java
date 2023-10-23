package M1.reseau.modele.monde.grille.type;

import M1.reseau.modele.monde.grille.fabrique.FabriqueGrille8x8;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Grille8x8Test {

    private Grille8x8 _grille;

    @BeforeEach
    void setUp() {
        this._grille = (Grille8x8) (new FabriqueGrille8x8()).creerGrille();
    }

    @Test
    void ajouterCase() {

    }

    @Test
    void ajouterBateau() {
    }

    @Test
    void supprimerCase() {
    }

    @Test
    void supprimerBateau() {
    }
}