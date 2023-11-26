package M1.reseau.client2.cor;

import M1.reseau.client2.GameClient;
import M1.reseau.serveur.serveur.game.SalonThread;

public interface IClientCORBuilder {
    void solveServer(String _message, GameClient _game);
}
