package M1.reseau.serveur.cor;

import M1.reseau.client2.GameClient;
import M1.reseau.serveur.serveur.game.SalonThread;

public interface IServerCORBuilder {
    void solveServer(String _message, SalonThread _salon);
}
