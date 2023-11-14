package M1.reseau.serveur.singletons;

import M1.reseau.serveur.serveur.ServeurUDP;

import java.io.IOException;
import java.net.*;


public class SingletonUDP
{

    /** Constructeur privé
     un seul socket UDP est nécessaire pour le serveur
     */
    final DatagramSocket socketUDP;
    final  int _taille = 1024;
    final byte[] _buffer = new byte[_taille];
    final int _port;
    InetAddress _adr ;
    private SingletonUDP( int port,String ad) throws UnknownHostException, SocketException {
        _port=7777;
        InetAddress _adr= InetAddress.getByName("localhost");
        // création d'une socket, sans la lier à un port particulier
        this.socketUDP = new DatagramSocket();

    }
    private SingletonUDP() throws UnknownHostException, SocketException {
        _port=7777;
        InetAddress _adr= InetAddress.getByName("localhost");
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
        DatagramPacket recu=new DatagramPacket(new byte[_taille],_taille);;
        socketUDP.receive(recu);
        String data= new String(recu.getData());
        System.out.println("Data recieved : " + data);
        return data;
    }
    public void fermetureSocket()  {
        socketUDP.close();
    }

    /** Holder */
    private static class SingletonHolder
    {
        /** Instance unique non préinitialisée */
        private final static SingletonUDP instance;
        static {
            try {
                instance = new SingletonUDP();
            } catch (UnknownHostException | SocketException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonUDP getInstance()
    {
        return SingletonHolder.instance;
    }
}