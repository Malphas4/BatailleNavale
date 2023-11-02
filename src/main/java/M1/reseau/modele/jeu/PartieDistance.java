package M1.reseau.modele.jeu;

import M1.reseau.modele.exception.IPartieException;
import M1.reseau.modele.joueur.IJoueur;
import M1.reseau.modele.joueur.fabrique.FabriqueJoueurNormal;
import M1.reseau.modele.joueur.type.JoueurNormal;
import M1.reseau.modele.monde.grille.Grille;

public class PartieDistance extends Partie {

    private boolean _statut;

    private JoueurNormal _joueur1;
    private JoueurNormal _joueur2;

    public PartieDistance(int _nbjoueur) {
        super(_nbjoueur);
    }

    public PartieDistance() {
        super();
        set_statut(false);
    }

    public boolean is_statut() {
        return _statut;
    }

    public void set_statut(boolean _statut) {
        this._statut = _statut;
    }

    public JoueurNormal get_joueur1() {
        return _joueur1;
    }

    public void set_joueur1(JoueurNormal _joueur1) {
        this._joueur1 = _joueur1;
    }

    public JoueurNormal get_joueur2() {
        return _joueur2;
    }

    public void set_joueur2(JoueurNormal _joueur2) {
        this._joueur2 = _joueur2;
    }

    @Override
    public void commencer() {
        set_statut(true);
    }

    @Override
    public void tourSuivant() throws IPartieException {
        if (!is_statut()) throw new IPartieException("PartieDistance : Le partie n'a pas commencé.");
        set_nbTour(get_nbTour() + 1);
    }

    @Override
    public IJoueur getJoueurCourant() {
        return (get_nbTour() % 2 == 0) ? get_joueur1() : get_joueur2();
    }

    @Override
    public void fin() {
        set_statut(false);
    }
}
