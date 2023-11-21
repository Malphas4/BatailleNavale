package M1.reseau.client.cor;

import M1.reseau.serveur.salon.ISalon;

public interface IClientCOR {
    void set_nextNode(IClientCOR _nextNode);
    void travel(String _message, ISalon _salon);
    void execute(String _message, ISalon _salon);
    boolean isMessageCorrect(String _message);
}
