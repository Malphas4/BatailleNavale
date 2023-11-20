package M1.reseau.serveur.singletons;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SingletonTCP implements Serializable
{
    InetAddress host = InetAddress.getLocalHost();
   //pour socket client AF
   Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    private static ServerSocket serveur;
    int port = 9876;
    /** Constructeur privé */
    private SingletonTCP() throws IOException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        serveur = new ServerSocket(port);

    }

    /** Instance unique pré-initialisée */
    private static SingletonTCP INSTANCE;

    static {
        try {
            INSTANCE = new SingletonTCP();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonTCP getInstance()
    {
        return INSTANCE;
    }

    /** Sécurité anti-désérialisation */
    private Object readResolve() {
        return INSTANCE;
    }

    public static void fermetureSocket()  {
        try{
            if(serveur != null) serveur.close();
        }catch (IOException e){ e.printStackTrace();}

    }
}