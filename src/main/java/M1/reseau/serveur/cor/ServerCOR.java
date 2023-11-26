package M1.reseau.serveur.cor;

import M1.reseau.serveur.serveur.game.SalonThread;

public abstract class ServerCOR implements IServerCOR {

    private IServerCOR _nextNode = null; /* Next node of COR */

    public ServerCOR() {

    }

    /**
     *
     * @return
     */
    public IServerCOR get_nextNode() {
        return _nextNode;
    }

    /**
     * @param _nextNode
     */
    @Override
    public void set_nextNode(IServerCOR _nextNode) {
        this._nextNode = _nextNode;
    }

    /**
     * @param _message
     * @param _salon
     */
    @Override
    public void receive(String _message, SalonThread _salon) {
        if (_message == null) throw new IllegalArgumentException("ServerCOR : Le message ne peut pas être null.");

        if (isMessageCorrect(_message))
            execute(_message, _salon);
        else {
            if (_nextNode != null)
                get_nextNode().receive(_message, _salon);
            else
                throw new IllegalArgumentException("ServerCOR : Next node is null.");
        }
    }
}
