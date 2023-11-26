package M1.reseau.client2.cor;

import M1.reseau.client2.GameClient;

public interface IClientCOR {
    void set_nextNode(IClientCOR _nextNode);
    void receive(String _message, GameClient _game);
    void execute(String _message, GameClient _game);
    boolean isMessageCorrect(String _message);
}
