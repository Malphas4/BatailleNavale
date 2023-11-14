package M1.reseau.client;

import M1.reseau.serveur.serveur.ServeurUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class ClientUDP {
    final static int taille = 1024;
    final static byte buffer[] = new byte[taille];

    public static void main(String argv[]) throws Exception
    {
        InetAddress serveur = InetAddress.getByName("localhost");
        byte[] data = (new String("youpi")).getBytes();

        DatagramPacket dataSent = new DatagramPacket(data,data.length,serveur,7777 );
        DatagramSocket socket = new DatagramSocket();
        socket.send(dataSent);
        DatagramPacket dataRecieved = new DatagramPacket(new byte[taille],taille);
        socket.receive(dataRecieved);
        System.out.println("Data recieved : " + new String(dataRecieved.getData()));
        System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
    }

}

