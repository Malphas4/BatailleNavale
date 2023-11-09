package M1.reseau.model.partie.type;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.partie.Partie;
import M1.reseau.model.joueur.IJoueur;
import M1.reseau.model.joueur.type.JoueurNormal;

public class PartieDistance extends Partie {

    private boolean _statut;

    private JoueurNormal _joueur;

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

    public JoueurNormal get_joueur() {
        return _joueur;
    }

    public void set_joueur(JoueurNormal _joueur) {
        this._joueur = _joueur;
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
        return get_joueur();
    }

    @Override
    public void fin() {
        set_statut(false);
    }
}
