package M1.reseau.serveur.singletons;

import java.io.IOException;
import java.net.*;

public class SingletonUDP {

    /**
     * intance
     */
    private static SingletonUDP _singletonUDP;

    /**
    * Données de l'instance du socket UDP
    *
    */
    static  DatagramSocket socketUDP;//= new DatagramSocket();
    final int _taille = 1024;
    final byte[] _buffer = new byte[_taille];
    int _port = 7777;
    InetAddress _adr= InetAddress.getByName("localhost");

    /**
     * Constructeur privé
     */
    private SingletonUDP(int port, String ad) throws UnknownHostException, SocketException {
        _port = port;
        this._adr = InetAddress.getByName(ad);
        // création d'une socket, sans la lier à un port particulier
        this.socketUDP = new DatagramSocket();

    }
    /**
     * Constructeur privé par défaut
     * un seul socket UDP est nécessaire pour le serveur
     */
    private SingletonUDP() throws UnknownHostException, SocketException {
        //InetAddress _adr = InetAddress.getByName("localhost");
        this.socketUDP = new DatagramSocket();
    }

    /**
     * envoi d'un message apr UDP
     * @param s message à envoyer de type String
    *  @throws IOException gestion IO exception
     * @throws UnknownHostException gestion host inconnu (ici localhost initialement
     * @throws SocketException
     * La demande de message est bloquante pour l'intant
     * Une option pour abandonner la'tente de réponse est à prévoir en 2 3 lignes de codes
     */
    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //creation du packet UDP
        DatagramPacket packet = new DatagramPacket(data, data.length, _adr, _port);
        // envoi du paquet via la socket PROBLEME ICI CAR SERVEUR KAPUT
        socketUDP.send(packet);

    }

    /**
     * reception d'un message avec UDP
     *  @return message recu de type String
     *  @throws IOException
     *  La lecture d'un socket vide est une erreur pour l'intant
     *  une option pour ignorer le message vide est à faire
     */
    public String reception() throws IOException {
        DatagramPacket recu = new DatagramPacket(_buffer, _taille);
        socketUDP.receive(recu);
        String data = new String(recu.getData());
        System.out.println("Data recu : " + data + "\n");
        return data;
    }

    /**
     * fermeture du socket
     */
    public static void fermetureSocket() {
        socketUDP.close();
    }


    /*Holder
     * Pas utilisé
     * */
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
     * Initialisation si nécessaire inclus dans la fonction
     */
    public static SingletonUDP getInstance() throws SocketException, UnknownHostException {
        if (_singletonUDP == null) {
            _singletonUDP = new SingletonUDP();
        }
        return SingletonUDP._singletonUDP;
    }
}