package M1.reseau.modele.joueur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    private Joueur _joueurDefaut;
    private Joueur _joueur;

    @BeforeEach
    void setUp() {
        this._joueurDefaut = new Joueur();
        this._joueur = new Joueur("Joueur 1");
    }

    @Test
    void constructeurVideTest() {
        /* On vérifie que l'objet est non null */
        assertNotNull(this._joueurDefaut);

        /* On vérifie les attributs */
        assertNotNull(this._joueurDefaut.get_pseudo());
        assertEquals("Joueur", this._joueurDefaut.get_pseudo());
    }

    @Test
    void constructeurTest() {
        /* On vérifie que l'objet est non null */
        assertNotNull(this._joueur);

        /* On vérifie les attributs */
        assertNotNull(this._joueur.get_pseudo());
        assertEquals("Joueur 1", this._joueur.get_pseudo());
    }
}