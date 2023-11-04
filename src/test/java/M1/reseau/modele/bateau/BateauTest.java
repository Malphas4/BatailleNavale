package M1.reseau.modele.bateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BateauTest {

    private Bateau _bateau;

    @BeforeEach
    void setUp() {
        _bateau = new Bateau();
    }

    @Test
    void constructeurTest() {
        /* Vérification que bateau n'est pas null */
        assertNotNull(_bateau);

        /* Vérification des attributs */
        assertNotNull(_bateau.get_listeCase());
        assertEquals(0, _bateau.get_listeCase().size());
    }

}