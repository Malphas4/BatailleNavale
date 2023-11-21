package M1.reseau.serveur.cor;

import M1.reseau.serveur.salon.ISalon;

public interface IServerCOR {
    void set_nextNode(IServerCOR _nextNode);
    void travel(String _message, ISalon _salon);
    void execute(String _message, ISalon _salon);
    boolean isMessageCorrect(String _message);
}
