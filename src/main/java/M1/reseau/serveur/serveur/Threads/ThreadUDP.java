package M1.reseau.serveur.serveur.Threads;

import M1.reseau.serveur.serveur.ServeurGlobale;

import java.io.IOException;
import java.net.*;


public class ThreadUDP extends Thread{

    //variables
    final static int taille = 1024;
    static byte[] buffer = new byte[taille];
    int _port = 7777;
    String _msg;
    DatagramSocket socketUDP;
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
            this.socketUDP = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
       System.out.print("UDP en arriere plan\n");
        try {
            String reponse="";
            DatagramPacket recu = new DatagramPacket(buffer, taille);
            socketUDP.receive(recu);
            _msg=recu.getData().toString();
            System.out.printf(_msg);
            String[] _msgT=_msg.split(":");
            if(_msgT[0].equals("0C")) {
                String[] _msgID = _msgT[1].split(";");
                if (_msgID[0].equals("test") && _msgID[1].equals("000")) {
                    reponse="1C";
                }else if (_msgID[0].equals("test") && !(_msgID[1].equals("000"))){
                    reponse="1E";
                }else
                    reponse="1D";
            } else if (_msg.equals("NBsalons?")) {
                int _nbSalons=_serveurmain.getNbSalons();
                reponse=String.valueOf(_nbSalons);
            }
            buffer= reponse.getBytes();
            DatagramPacket response = new DatagramPacket(buffer, taille,
                    recu.getAddress(), recu.getPort());
            socketUDP.send(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
