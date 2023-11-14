package M1.reseau.model.game.distance;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.grid.Grille;
import M1.reseau.model.game.Partie;
import M1.reseau.model.player.IJoueur;
import M1.reseau.model.player.classic.JoueurNormal;

public class PartieClient extends Partie {

    /***************************************
     * Déclaration des variables de classe
     ***************************************/

    /**
     * Définition du joueur
     * Gestion de l'autre joueur à partir du serveur, ici la grille du joueur adverse,
     * se trouvera dans la grille de touche du joueur
     */
    private JoueurNormal _joueur;
    private boolean _aGagner = false;
    private boolean _tourJoueur = false;

    /***************************************
     * Déclaration des constructeurs
     ***************************************/
    /**
     *
     * @param _longueur
     * @param _largeur
     */
    public PartieClient(int _longueur, int _largeur) {
        super(_longueur, _largeur);
    }

    /***************************************
     * Déclaration des getters et setters
     ***************************************/

    /**
     *
     * @return
     */
    public JoueurNormal get_joueur() {
        return _joueur;
    }

    /**
     *
     * @param _joueur
     */
    public void set_joueur(JoueurNormal _joueur) {
        if (_joueur == null) throw new IllegalArgumentException("PartieClient : Le joueur ne peut pas être null.");
        this._joueur = _joueur;
    }

    /**
     *
     * @return
     */
    public boolean is_aGagner() {
        return _aGagner;
    }

    /**
     *
     * @param _aGagner
     */
    public void set_aGagner(boolean _aGagner) {
        this._aGagner = _aGagner;
    }

    /**
     *
     * @return
     */
    public boolean is_tourJoueur() {
        return _tourJoueur;
    }

    /**
     *
     * @param _tourJoueur
     */
    public void set_tourJoueur(boolean _tourJoueur) {
        this._tourJoueur = _tourJoueur;
    }

    /***************************************
     * Implémentation des méthodes de Partie
     ***************************************/

    /**
     *
     * @throws IPartieException On vérifie que le joueur n'est pas null
     * @throws IPartieException La partie n'a pas encore commencé
     * @throws IPartieException La partie n'est pas terminé
     */
    @Override
    public void commencer() throws IPartieException {
        if (_joueur == null) throw new IPartieException("PartieClient : La partie ne peut pas commencer tant que le joueur est null.");
        if (is_commence()) throw new IPartieException("PartieClient : Le partie a déjà commencé.");
        if (is_termine()) throw new IPartieException("PartieClient : La partie est terminé.");
        set_commence(true);
    }

    /**
     *
     * @throws IPartieException
     */
    @Override
    public void tourSuivant() throws IPartieException {
        if (!is_commence()) throw new IPartieException("PartieClient : Le partie n'a pas commencé.");
        if (is_termine()) throw new IPartieException("PartieClient : La partie est déjà terminé.");
        if (get_joueur() == null) throw new IPartieException("PartieClient : Le joueur ne peut pas être null. Le tour ne peut pas passer.");
        /* On modifie le nombre de tour et on change l'état du tour joueur */
        set_nbTour(get_nbTour() + 1);
        set_tourJoueur(!is_tourJoueur());
    }

    /**
     *
     * @return
     * @throws IPartieException
     */
    @Override
    public IJoueur getJoueurCourant() throws IPartieException {
        if (_joueur == null) throw new IPartieException("PartieClient : Le joueur n'est pas défini. Impossible de récupérer le joueur courant.");
        if (!is_tourJoueur()) throw new IPartieException("PartieClient : Ce n'est pas le tour du joueur.");
        return get_joueur();
    }

    /**
     * @return
     * @throws IPartieException
     */
    @Override
    public IJoueur getJoueurAdverse() throws IPartieException {
        if (_joueur == null) throw new IPartieException("PartieClient : Le joueur n'est pas défini. Impossible de récupérer le joueur courant.");
        if (is_tourJoueur()) throw new IPartieException("PartieClient : Ce n'est pas le tour du joueur.");
        return get_joueur();
    }

    /**
     *
     * @param _pseudo
     */
    @Override
    public void ajouterJoueur(String _pseudo) throws IPartieException {
        if (is_commence()) throw new IPartieException("PartieClient : Le joueur ne peut pas être ajouter en cours de partie.");

        try {
            set_joueur(new JoueurNormal(_pseudo, (Grille) get_fabriqueGrille().creerGrille(), (Grille) get_fabriqueGrille().creerGrille()));
        } catch (IJoueurException e) {
            System.err.println(e.getErr());
        }
    }

    /**
     *
     * @throws IPartieException
     */
    @Override
    public void fin() throws IPartieException {
        if (!is_commence()) throw new IPartieException("PartieClient : La partie n'a pas encore commencé.");
        if (is_termine()) throw new IPartieException("PartieClient : La partie est déjà terminé.");
        set_termine(true);
    }

    /**
     * @return
     */
    @Override
    public boolean gagnant() {
        for (ICase _case : get_joueur().get_grilleJoueur().get_listeCase())
            if (_case instanceof CaseBateau) return false;
        return true;
    }

    /***************************************
     * Override des méthodes de Partie
     ***************************************/
    @Override
    public String toString() {
        return "PartieClient{" +
                "_joueur=" + _joueur +
                ", _aGagner=" + _aGagner +
                ", _tourJoueur=" + _tourJoueur +
                ", " + super.toString() +
                "}";
    }
}
