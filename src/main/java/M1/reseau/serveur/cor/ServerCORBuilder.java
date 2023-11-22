package M1.reseau.serveur.cor;

import M1.reseau.serveur.cor.treatment.chat.ServerCodeChat;
import M1.reseau.serveur.cor.treatment.game.ServerCodeCommencer;
import M1.reseau.serveur.cor.treatment.game.ServerCodeInitBateau;
import M1.reseau.serveur.cor.treatment.game.ServerCodeTirer;
import M1.reseau.serveur.cor.treatment.room.ServerCodeJoin;
import M1.reseau.serveur.cor.treatment.room.ServerCodeJoinBot;

public class ServerCORBuilder {

    private static ServerCORBuilder _serverCORBuilder;

    private IServerCOR _serverCOR;

    private ServerCORBuilder() {
        IServerCOR sc1, sc2, sc3, sc4, sc5, sc6;

        /* Init COR */
        sc1 = new ServerCodeChat();
        sc2 = new ServerCodeJoinBot();
        sc3 = new ServerCodeJoin();
        sc4 = new ServerCodeInitBateau();
        sc5 = new ServerCodeCommencer();
        sc6 = new ServerCodeTirer();

        /* Chaining COR */
        sc1.set_nextNode(sc2);
        sc2.set_nextNode(sc3);
        sc3.set_nextNode(sc4);
        sc4.set_nextNode(sc5);
        sc5.set_nextNode(sc6);

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
