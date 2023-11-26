package M1.reseau.client2.cor;

import M1.reseau.client2.GameClient;
import M1.reseau.serveur.cor.IServerCOR;
import M1.reseau.serveur.serveur.game.SalonThread;

public abstract class ClientCOR implements IClientCOR {

    private IClientCOR _nextNode = null; /* Next node of COR */

    public ClientCOR() {

    }

    /**
     *
     * @return
     */
    public IClientCOR get_nextNode() {
        return _nextNode;
    }

    /**
     * @param _nextNode
     */
    @Override
    public void set_nextNode(IClientCOR _nextNode) {
        this._nextNode = _nextNode;
    }

    /**
     * @param _message
     * @param _game
     */
    @Override
    public void receive(String _message, GameClient _game) {
        if (_message == null) throw new IllegalArgumentException("ClientCOR : Le message ne peut pas être null.");

        if (isMessageCorrect(_message))
            execute(_message, _game);
        else {
            if (_nextNode != null)
                get_nextNode().receive(_message, _game);
            else
                throw new IllegalArgumentException("ClientCOR : Next node is null.");
        }
    }
}
