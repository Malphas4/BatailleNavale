package M1.reseau.serveur.singletons;

import java.io.IOException;
import java.net.*;

public class SingletonUDP {

    //instance
    private static SingletonUDP _singletonUDP;

    /*
    * Données de l'instance du socket UDP
    * */
    final DatagramSocket socketUDP;//= new DatagramSocket();
    final int _taille = 1024;
    final byte[] _buffer = new byte[_taille];
    int _port = 7777;
    ;
    InetAddress _adr= InetAddress.getByName("localhost");;

    /**
     * Constructeur privé
     * un seul socket UDP est nécessaire pour le serveur
     */
    private SingletonUDP(int port, String ad) throws UnknownHostException, SocketException {
        _port = 7777;
        InetAddress _adr = InetAddress.getByName(ad);
        // création d'une socket, sans la lier à un port particulier
        this.socketUDP = new DatagramSocket();

    }
    /**
     * Constructeur privé par défaut
     * un seul socket UDP est nécessaire pour le serveur
     */
    private SingletonUDP() throws UnknownHostException, SocketException {
        InetAddress _adr = InetAddress.getByName("localhost");
        this.socketUDP = new DatagramSocket();
    }

    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //creation du packet UDP
        DatagramPacket packet = new DatagramPacket(data, data.length, _adr, _port);
        // envoi du paquet via la socket PROBLEME ICI CAR SERVEUR KAPUT
        socketUDP.send(packet);

    }

    public String reception() throws IOException {
        DatagramPacket recu = new DatagramPacket(_buffer, _taille);
        socketUDP.receive(recu);
        String data = new String(recu.getData());
        System.out.println("Data recu : " + data + "\n");
        return data;
    }

    public void fermetureSocket() {
        socketUDP.close();
    }


    /** Holder */
    /*private static class SingletonHolder
    {
        /** Instance unique non préinitialisée *//*
        private final static SingletonUDP instance;
        static {
            try {
                instance = new SingletonUDP();
            } catch (UnknownHostException | SocketException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static SingletonUDP getInstance() throws SocketException, UnknownHostException {
        if (_singletonUDP == null) {
            _singletonUDP = new SingletonUDP();
        }
        return SingletonUDP._singletonUDP;
    }
}
/*
*
*         DatagramPacket dataSent = new DatagramPacket(data,data.length,serveur,7777 );
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);
        DatagramPacket dataRecieved = new DatagramPacket(new byte[taille],taille);
        socket.receive(dataRecieved);*/