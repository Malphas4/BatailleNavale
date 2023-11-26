package M1.reseau.client2.cor;


import M1.reseau.client2.GameClient;
import M1.reseau.serveur.serveur.game.SalonThread;

public class ClientCORBuilder implements IClientCORBuilder {

    private static ClientCORBuilder _serverCORBuilder = null;

    private IClientCOR _clientCOR;

    private ClientCORBuilder() {
        IClientCOR cc1;

    }

    public static ClientCORBuilder getInstance() {
        if (get_serverCORBuilder() == null) return new ClientCORBuilder();
        return get_serverCORBuilder();
    }

    public static ClientCORBuilder get_serverCORBuilder() {
        return _serverCORBuilder;
    }

    public static void set_serverCORBuilder(ClientCORBuilder _serverCORBuilder) {
        ClientCORBuilder._serverCORBuilder = _serverCORBuilder;
    }

    public IClientCOR get_clientCOR() {
        return _clientCOR;
    }

    public void set_clientCOR(IClientCOR _clientCOR) {
        this._clientCOR = _clientCOR;
    }

    /**
     * @param _message
     * @param _salon
     */
    public void solveClient(String _message, GameClient _game) {
        get_clientCOR().receive(_message, _game);
    }

    /**
     * @param _message
     * @param _salon
     */
    @Override
    public void solveServer(String _message, GameClient _game) {
        get_clientCOR().execute(_message, _game);
    }
}
