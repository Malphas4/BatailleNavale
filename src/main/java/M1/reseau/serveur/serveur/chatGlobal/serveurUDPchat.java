package M1.reseau.serveur.serveur.chatGlobal;

import M1.reseau.serveur.serveur.ServeurMain;

import java.io.IOException;
import java.net.*;

public class serveurUDPchat  extends Thread{

    final static int taille = 1024;
    static byte[] buffer = new byte[taille];
    String _msg;
    DatagramSocket socketUDP;
    Thread _t; // contiendra le thread
    ServeurChatTCP _serveurChatTCP;



    //constructeur
    public serveurUDPchat(ServeurChatTCP Serveur) throws SocketException, UnknownHostException {
        _serveurChatTCP=Serveur; // passage de local en global

        int _port = 7777;
        InetAddress _adr= InetAddress.getByName("localhost");
        // création d'une socket, sans la lier à un port particulier
        this.socketUDP = new DatagramSocket(_port);

    }


    @Override
    public void run() {

        while (this.isInterrupted()) {
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
                    int _nbSalons=_serveurChatTCP.getNbSalons();
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
}
