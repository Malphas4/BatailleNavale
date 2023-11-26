package M1.reseau.serveur.cor.treatment.room;

import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.player.bot.EasyBot;
import M1.reseau.model.world.grid.Grille;
import M1.reseau.serveur.cor.ServerCOR;
import M1.reseau.serveur.serveur.game.GameService;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;

public class ServerCodeJoinBot extends ServerCOR {

    public ServerCodeJoinBot() {

    }

    /**
     * Receive : joinbot;[salon id];[joueur id]
     * @param _message
     * @param _salon
     */
    @Override
    public void execute(String _message, SalonThread _salon) {
        System.out.println("\t> execute Code joinbot");
        GameService _gameService = _salon.get_gameService();

        String[] _sp = _message.split(";");

        try {
            // Define player in salon
            _salon.set_j1(_sp[2]);

            // Add player in the game
            _gameService.get_partie().ajouterJoueur(_sp[2]);

            // Add bot in game
            try {
                _gameService.get_partie().set_joueur2(
                        new EasyBot(
                                (Grille) _gameService.get_partie().get_fabriqueGrille().creerGrille(),
                                (Grille) _gameService.get_partie().get_fabriqueGrille().creerGrille()
                        )
                );
            } catch (IJoueurException e) {
                System.err.println("ServerCodeJoinBot : Le joueur a un problème.");
            }

        } catch (IPartieException e) {
            System.err.println("ServerCodeJoinBot : La partie a un problème.");
        }

        try {
            _salon.get_j1().message(
                    "join;"
                            + _salon.get_id()
                            + ";" + _gameService.get_partie().get_joueur2().get_pseudo()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\tEnd execute code join");
    }

    /**
     * @param _message
     * @return
     */
    @Override
    public boolean isMessageCorrect(String _message) {
        String[] _sp = _message.split(";");
        System.out.println("> ServerCodeJoinBot : " + _sp[0] + " - " + _sp[0].equalsIgnoreCase("joinbot"));
        return _sp[0].equalsIgnoreCase("joinbot");
    }
}
