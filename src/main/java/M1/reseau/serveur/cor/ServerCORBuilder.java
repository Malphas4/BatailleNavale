package M1.reseau.serveur.cor;

import M1.reseau.serveur.cor.treatment.chat.ServerCodeChat;
import M1.reseau.serveur.cor.treatment.game.ServerCodeCommencer;
import M1.reseau.serveur.cor.treatment.game.ServerCodeInitBateau;
import M1.reseau.serveur.cor.treatment.game.ServerCodeTirer;
import M1.reseau.serveur.cor.treatment.room.ServerCodeJoin;

public class ServerCORBuilder {

    private static ServerCORBuilder _serverCORBuilder;

    private IServerCOR _serverCOR;

    private ServerCORBuilder() {
        IServerCOR sc1, sc2, sc3, sc4, sc5;

        /* Init COR */
        sc1 = new ServerCodeChat();
        sc2 = new ServerCodeJoin();
        sc3 = new ServerCodeInitBateau();
        sc4 = new ServerCodeCommencer();
        sc5 = new ServerCodeTirer();

        /* Chaining COR */
        sc1.set_nextNode(sc2);
        sc2.set_nextNode(sc3);
        sc3.set_nextNode(sc4);
        sc4.set_nextNode(sc5);

        set_serverCOR(sc1);
    }

    public static ServerCORBuilder getInstance() {
        if (get_serverCORBuilder() == null) return new ServerCORBuilder();
        return get_serverCORBuilder();
    }

    public static ServerCORBuilder get_serverCORBuilder() {
        return _serverCORBuilder;
    }

    public static void set_serverCORBuilder(ServerCORBuilder _serverCORBuilder) {
        ServerCORBuilder._serverCORBuilder = _serverCORBuilder;
    }

    public IServerCOR get_serverCOR() {
        return _serverCOR;
    }

    public void set_serverCOR(IServerCOR _serverCOR) {
        this._serverCOR = _serverCOR;
    }
}
