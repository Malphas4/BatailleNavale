package M1.reseau.model.game.local;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.game.Partie;
import M1.reseau.model.player.IJoueur;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.classic.CaseBateau;
import M1.reseau.model.world.grid.Grille;

public class PartieServeur extends Partie {

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
     * Constructeur par défaut
     * @param _longueur Longueur de la grille
     * @param _largeur Largeur de la grille
     */
    public PartieServeur(int _longueur, int _largeur) {
        super(_longueur, _largeur);
    }

    /***************************************
     * Déclaration des getters et setters
     ***************************************/

    /**
     * Getter du joueur 1
     * @return
     */
    public JoueurNormal get_joueur1() {
        return _joueur1;
    }

    /**
     * Setter du joueur 1
     * @param _joueur1
     */
    public void set_joueur1(JoueurNormal _joueur1) {
        if (_joueur1 == null) throw new IllegalArgumentException("PartieServeur : Le joueur1 ne peut pas être null.");
        this._joueur1 = _joueur1;
    }

    /**
     * Getter du statut de victoire du joueur 1
     * @return
     */
    public boolean is_aGagner1() {
        return _aGagner1;
    }

    /**
     * Setter du statut de victoire du joueur 1
     * @param _aGagner1
     */
    public void set_aGagner1(boolean _aGagner1) {
        this._aGagner1 = _aGagner1;
    }

    /**
     * Getter du joueur 2
     * @return
     */
    public JoueurNormal get_joueur2() {
        return _joueur2;
    }

    /**
     * Setter du joueur 2
     * @param _joueur2
     */
    public void set_joueur2(JoueurNormal _joueur2) {
        if (_joueur2 == null) throw new IllegalArgumentException("PartieServeur : Le joueur2 ne peut pas être null.");
        this._joueur2 = _joueur2;
    }

    /**
     * Getter du statut de victoire du joueur 2
     * @return
     */
    public boolean is_aGagner2() {
        return _aGagner2;
    }

    /**
     * Setter du statut de victoire du joueur 2
     * @param _aGagner2
     */
    public void set_aGagner2(boolean _aGagner2) {
        this._aGagner2 = _aGagner2;
    }

    /***************************************
     * Implémentation des méthodes de Partie
     ***************************************/

    /**
     * Méthode de gestion du commencement d'une partie
     * @throws IPartieException Le joueur 1 est null
     * @throws IPartieException Le joueur 2 est null
     * @throws IPartieException La partie a déjà commencée
     * @throws IPartieException La partie est terminée
     */
    @Override
    public void commencer() throws IPartieException {
        if (get_joueur1() == null) throw new IPartieException("PartieServeur : La partie ne peut pas commencer tant que le joueur1 est null.");
        if (get_joueur2() == null) throw new IPartieException("PartieServeur : La partie ne peut pas commencer tant que le joueur2 est null.");
        if (is_commence()) throw new IPartieException("PartieServeur : Le partie a déjà commencé.");
        if (is_termine()) throw new IPartieException("PartieServeur : La partie est terminé.");
        set_commence(true);
    }

    /**
     * Méthode de gestion du tour suivant
     * @throws IPartieException La partie n'a pas commencée
     * @throws IPartieException La partie est terminée
     * @throws IPartieException Le joueur 1 est null
     * @throws IPartieException Le joueur 2 est null
     */
    @Override
    public void tourSuivant() throws IPartieException {
        if (!is_commence()) throw new IPartieException("PartieServeur : Le partie n'a pas commencé.");
        if (is_termine()) throw new IPartieException("PartieServeur : La partie est déjà terminé.");
        if (get_joueur1() == null) throw new IPartieException("PartieServeur : Le joueur1 ne peut pas être null. Le tour ne peut pas passer.");
        if (get_joueur2() == null) throw new IPartieException("PartieServeur : Le joueur2 ne peut pas être null. Le tour ne peut pas passer.");
        /* On modifie le nombre de tour et on change l'état du tour joueur */
        set_nbTour(get_nbTour() + 1);
    }

    /**
     * Méthode de gestion du joueur courant
     * @return Joueur courant
     * @throws IPartieException Le joueur 1 est null
     * @throws IPartieException Le joueur 2 est null
     */
    @Override
    public IJoueur getJoueurCourant() throws IPartieException {
        if (get_joueur1() == null)
            throw new IPartieException("PartieServeur : Le joueur1 ne peut pas être null. La récupération du joueur courant est impossible.");
        if (get_joueur2() == null)
            throw new IPartieException("PartieServeur : Le joueur2 ne peut pas être null. La récupération du joueur courant est impossible.");
        return (get_nbTour() % 2 == 0) ? get_joueur1() : get_joueur2();
    }

    /**
     * Méthode de gestion du joueur courant
     * @return Joueur courant
     * @throws IPartieException Le joueur 1 est null
     * @throws IPartieException Le joueur 2 est null
     */
    @Override
    public IJoueur getJoueurAdverse() throws IPartieException {
        if (get_joueur1() == null)
            throw new IPartieException("PartieServeur : Le joueur1 ne peut pas être null. La récupération du joueur courant est impossible.");
        if (get_joueur2() == null)
            throw new IPartieException("PartieServeur : Le joueur2 ne peut pas être null. La récupération du joueur courant est impossible.");
        return (get_nbTour() % 2 == 1) ? get_joueur1() : get_joueur2();
    }

    /**
     * Gestion de l'ajout de joueur dans la partie
     * @param _pseudo
     * @throws IPartieException Le joueur 1 et 2 ne sont pas null
     */
    @Override
    public void ajouterJoueur(String _pseudo) throws IPartieException {
        if (get_joueur1() != null && get_joueur2() != null)
            throw new IPartieException("PartieServeur : L'ajout d'un joueur est impossible. Les joueurs sur la partie sont déjà définis.");

        /* Création du nouveau joueur */
        JoueurNormal _joueur = null;
        try {
            _joueur = new JoueurNormal(_pseudo,
                                    (Grille) get_fabriqueGrille().creerGrille(),
                                    (Grille) get_fabriqueGrille().creerGrille());
        } catch (IJoueurException e) {
            throw new RuntimeException(e);
        }

        /* Assignation du joueur dans les variables */
        if (get_joueur1() == null) set_joueur1(_joueur);
        else set_joueur2(_joueur);
    }

    /**
     * Méthode de gestion de fin d'une partie
     * @throws IPartieException La partie n'a pas commencée
     * @throws IPartieException La partie est terminée
     * @throws IPartieException Le joueur 1 ou 2 n'ont pas gagné
     */
    @Override
    public void fin() throws IPartieException {
        if (!is_commence()) throw new IPartieException("PartieServeur : La partie n'a pas encore commencé.");
        if (is_termine()) throw new IPartieException("PartieServeur : La partie est déjà terminé.");
        if (!is_aGagner1() && !is_aGagner2()) throw new IPartieException("PartieServeur : Aucun des 2 joueurs n'ont gagnés.");
        set_termine(true);
    }

    /**
     * @return
     */
    @Override
    public boolean gagnant() {
        JoueurNormal _joueur = null;
        try {
            _joueur = (JoueurNormal) getJoueurCourant();
        } catch (IPartieException e) {
            throw new RuntimeException(e);
        }
        for (ICase _case : _joueur.get_grilleJoueur().get_listeCase())
            if (_case instanceof CaseBateau) return false;

        // On update le statut gagnant
        if (get_nbTour() % 2 == 1) set_aGagner1(true);
        else set_aGagner2(true);

        return true;
    }

    @Override
    public String toString() {
        return "PartieServeur{" +
                "_joueur1=" + _joueur1 +
                ", _aGagner1=" + _aGagner1 +
                ", _joueur2=" + _joueur2 +
                ", _aGagner2=" + _aGagner2 +
                "," + super.toString() + "}";
    }
}
