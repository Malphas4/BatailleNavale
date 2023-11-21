package M1.reseau.serveur.cor;

import M1.reseau.serveur.serveur.game.SalonThread;

public interface IServerCOR {
    void set_nextNode(IServerCOR _nextNode);
    void receive(String _message, SalonThread _salon);
    void execute(String _message, SalonThread _salon);
    boolean isMessageCorrect(String _message);
}
