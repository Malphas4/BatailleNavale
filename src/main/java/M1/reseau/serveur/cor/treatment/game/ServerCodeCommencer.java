package M1.reseau.serveur.cor.treatment.game;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeCommencer extends ServerCOR {

    public ServerCodeCommencer() {

    }

    /**
     * Receive : commencer;[salon id]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        System.out.println("\t> ServerCodeCommencer : Execute");
        GameService _gameService = _salon.get_gameService();

        String[] _sp = _message.split(";");
        if (_sp[2].equalsIgnoreCase(_salon.get_j1().get_pseudo()))
            _salon.set_ready_j1(true);
        if (_sp[2].equalsIgnoreCase(_salon.get_j2().get_pseudo()))
            _salon.set_ready_j2(true);

        try {
            if (_salon.is_ready_j1() && _salon.is_ready_j2()) {
                System.out.println("\t\t> La partie va commencer pour le salon " + _sp[1]);
                _gameService.get_partie().commencer();
                String _jc = _gameService.get_partie().getJoueurCourant().get_pseudo();

                System.out.println("\t\t> ServerCodeCommencer : commencer;" + _salon.get_id() + ";" + _jc);
                _salon.get_j1().message("commencer;" + _salon.get_id() + ";" + _jc);
                _salon.get_j2().message("commencer;" + _salon.get_id() + ";" + _jc);
                // _salon.get_chrono().set_time(30);
            }
        } catch (IPartieException | IOException e) {
            System.err.println("ServerCodeCommencer : La partie ne peut pas commencer.");
        }
        System.out.println("\t> ServerCodeCommencer : Fin Execute");
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        System.out.println("> ServerCodeCommencer : " + _sp[0] + " - " + _sp[0].equalsIgnoreCase("commencer"));
        return _sp[0].equalsIgnoreCase("commencer");
    }
}
