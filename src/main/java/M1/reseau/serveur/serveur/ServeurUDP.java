package M1.reseau.serveur.serveur;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServeurUDP {

    public final static int port = 7777;//8532;
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];


    public static void main(String argv[]) throws Exception
    {
        final InetAddress _adr= java.net.InetAddress.getByName("localhost");
        DatagramSocket socket = new DatagramSocket(port);
        while(true) {
            System.out.print("boucle");
            DatagramPacket data = new DatagramPacket(buffer, buffer.length,_adr,port);
            socket.receive(data);
            System.out.println(data.getAddress());
            socket.send(data);
        }

    }


}
