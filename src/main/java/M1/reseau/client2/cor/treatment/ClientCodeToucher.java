package M1.reseau.client2.cor.treatment;

import M1.reseau.client2.GameClient;
import M1.reseau.client2.cor.ClientCOR;
import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorEstTire;
import M1.reseau.model.world.element.ICase;
import M1.reseau.model.world.element.state.CaseRate;
import M1.reseau.model.world.element.state.CaseTouche;
import M1.reseau.serveur.serveur.game.SalonThread;

public class ClientCodeToucher extends ClientCOR {
    /**
     * toucher;[salon id];[tireur];[victime];[x];[y];[statut touché ou coulé]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, GameClient _game) {
        String[] _sp = _message.split(";");
        String _idSalon = _sp[1];
        String _tireur = _sp[2];
        String _victime = _sp[3];
        int _x = Integer.parseInt(_sp[4]);
        int _y = Integer.parseInt(_sp[5]);
        boolean _statutCase = Boolean.parseBoolean(_sp[6]);

        if (_game.get_partie().get_joueur().get_pseudo().equalsIgnoreCase(_tireur)) {
            try {
                ICase _tCase = _game
                        .get_partie()
                        .get_joueur()
                        .get_grilleTouche()
                        .get_caseParCoord(_x, _y);
                if (_statutCase) {
                    _game.get_partie().get_joueur().get_grilleTouche().get_listeCase().remove(_tCase);
                    _game.get_partie().get_joueur().get_grilleTouche().get_listeCase().add(new CaseTouche(_x, _y));
                } else {
                    _game.get_partie().get_joueur().get_grilleTouche().get_listeCase().remove(_tCase);
                    _game.get_partie().get_joueur().get_grilleTouche().get_listeCase().add(new CaseRate(_x, _y));
                }
            } catch (IGrilleException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                ICase _tCase = _game
                        .get_partie()
                        .get_joueur()
                        .get_grilleJoueur()
                        .get_caseParCoord(_x, _y);
                if (_statutCase) {
                    _game.get_partie().get_joueur().get_grilleJoueur().get_listeCase().remove(_tCase);
                    _game.get_partie().get_joueur().get_grilleJoueur().get_listeCase().add(new CaseTouche(_x, _y));
                } else {
                    _game.get_partie().get_joueur().get_grilleJoueur().get_listeCase().remove(_tCase);
                    _game.get_partie().get_joueur().get_grilleJoueur().get_listeCase().add(new CaseRate(_x, _y));
                }
            } catch (IGrilleException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        return _sp[0].equalsIgnoreCase("toucher");
    }
}
