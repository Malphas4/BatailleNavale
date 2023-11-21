package M1.reseau.serveur.serveur.Threads;

import M1.reseau.serveur.serveur.ServeurGlobale;

import java.io.IOException;
import java.net.*;


public class ThreadUDP extends Thread{

    //variables
    final static int _taille = 1024;
     byte[] _buffer = new byte[_taille];
    int _port = 7777;
    String _msg;
    DatagramSocket _socketUDP;
    InetAddress _adr;
    {
        try {
            _adr = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    Thread _t; // contiendra le thread
    ServeurGlobale _sv;

    public ThreadUDP(ServeurGlobale sv)  {

        _sv=sv;
        // création d'une socket, sans la lier à un port particulier
        try {
            setName("ServeurUDP");
            _socketUDP = new DatagramSocket(7777);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        System.out.print("UDP en arriere plan\n");
        while (! isInterrupted()) {
            try {
                _adr=InetAddress.getByName("localhost") ;
                String reponse = "";
                System.out.print("Serveur en ecoute");
                //Creaton du packet a recuperer
                final  int _taille = 1024;
                byte[] _buffer = new byte[_taille];
                DatagramPacket recu = new DatagramPacket(_buffer, _taille);
                //recuperation du packet
                //recieve est bloquant
                _socketUDP.receive(recu);
                //recuperation des données
                String _msg = new String(recu.getData(), 0, recu.getLength());
                System.out.println("Data par serveur recu : " + _msg + "\n");
                //Separation du message recu
                String[] _msgT = _msg.split(";");
                //traitement du message
                if (_msgT[0].equals("0C")) {
                    String[] _msgID = _msgT[1].split(":");
                    if (_msgID[0].equals("test") && _msgID[1].equals("0000")) {
                        reponse = "1C";
                    } else if (_msgID[0].equals("test")) {
                        reponse = "1E";
                    } else
                        reponse = "1D";
                } else if (_msg.equals("NBsalons?")) {
                    int _nbSalons = _sv.getNbSalons();
                    reponse = String.valueOf(_nbSalons);
                } else reponse = "err";
                _buffer = new byte[_taille];
                _buffer = reponse.getBytes();
                DatagramPacket response = new DatagramPacket(_buffer, _buffer.length, recu.getAddress(), recu.getPort());
                System.out.println("Serveur envoie "+reponse);
                _socketUDP.send(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
