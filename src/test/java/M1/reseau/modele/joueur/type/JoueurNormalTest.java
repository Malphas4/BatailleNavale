package M1.reseau.modele.joueur.type;

import M1.reseau.modele.monde.grille.Grille;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class JoueurNormalTest {

    private JoueurNormal _joueur1;
    private JoueurNormal _joueur2;
    private JoueurNormal _joueur3;

    @BeforeEach
    void setUp() {
        Grille _grille1 = new Grille(8, 8);
        Grille _grille2 = new Grille(8, 8);
        Grille _grille3 = new Grille(4, 4);


    }
}