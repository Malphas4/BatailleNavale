package M1.reseau.modele.monde.grille.fabrique;

import M1.reseau.modele.monde.grille.IGrille;
import M1.reseau.modele.monde.grille.type.Grille8x8;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FabriqueGrille8x8Test {

    private FabriqueGrille8x8 _fabrique;

    @BeforeEach
    void setUp() {
        this._fabrique = new FabriqueGrille8x8();
    }

    @Test
    void creerGrille() {
        IGrille _grille;
        _grille = this._fabrique.creerGrille();
        assertInstanceOf(Grille8x8.class, _grille);
    }
}