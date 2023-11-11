package M1.reseau.model.game.local;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.IJoueur;
import M1.reseau.model.game.Partie;
import M1.reseau.model.player.type.JoueurNormal;

public class PartieLocale extends Partie {

    /***************************************
     * Déclaration des variables de classe
     ***************************************/

    private JoueurNormal _joueur1;
    private boolean _aGagner1 = false;
    private JoueurNormal _joueur2;
    private boolean _aGagner2 = false;

    /***************************************
     * Déclaration des constructeurs
     ***************************************/

    /**
     *
     * @param _longueur
     * @param _largeur
     */
    public PartieLocale(int _longueur, int _largeur) {
        super(_longueur, _largeur);
    }

    /***************************************
     * Déclaration des getters et setters
     ***************************************/

    /**
     *
     * @return
     */
    public JoueurNormal get_joueur1() {
        return _joueur1;
    }

    /**
     *
     * @param _joueur1
     */
    public void set_joueur1(JoueurNormal _joueur1) {
        if (_joueur2 == null) throw new IllegalArgumentException("PartieLocale : Le joueur1 ne peut pas être null.");
        this._joueur1 = _joueur1;
    }

    /**
     *
     * @return
     */
    public boolean is_aGagner1() {
        return _aGagner1;
    }

    /**
     *
     * @param _aGagner1
     */
    public void set_aGagner1(boolean _aGagner1) {
        this._aGagner1 = _aGagner1;
    }

    /**
     *
     * @return
     */
    public JoueurNormal get_joueur2() {
        return _joueur2;
    }

    /**
     *
     * @param _joueur2
     */
    public void set_joueur2(JoueurNormal _joueur2) {
        if (_joueur2 == null) throw new IllegalArgumentException("PartieLocale : Le joueur2 ne peut pas être null.");
        this._joueur2 = _joueur2;
    }

    /**
     *
     * @return
     */
    public boolean is_aGagner2() {
        return _aGagner2;
    }

    /**
     *
     * @param _aGagner2
     */
    public void set_aGagner2(boolean _aGagner2) {
        this._aGagner2 = _aGagner2;
    }

    /***************************************
     * Implémentation des méthodes de Partie
     ***************************************/

    @Override
    public void commencer() throws IPartieException {
        // TODO
    }

    @Override
    public void tourSuivant() throws IPartieException {
        // TODO
    }

    @Override
    public IJoueur getJoueurCourant() throws IPartieException {
        // TODO
        return null;
    }

    @Override
    public void ajouterJoueur(String _pseudo) throws IPartieException {
        // TODO
    }

    @Override
    public void fin() throws IPartieException {
        // TODO
    }
}
