package M1.reseau.modele.bateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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

    @Test
    void set_uuidTest() {
        // TODO
    }

    @Test
    void set_uuidErreurNullTest() {
        // TODO
    }

    @Test
    void ajouterCaseTest() {
        // TODO
    }

    @Test
    void ajouterCaseErreurNullTest() {
        // TODO
    }

    @Test
    void ajouterCaseErreurDansLaListeTest() {
        // TODO
    }

    @Test
    void supprimerCaseTest() {
        // TODO
    }

    @Test
    void supprimerCaseErreurListeVideTest() {
        // TODO
    }

    @Test
    void supprimerCaseErreurPasDansLaListeTest() {
        // TODO
    }

}