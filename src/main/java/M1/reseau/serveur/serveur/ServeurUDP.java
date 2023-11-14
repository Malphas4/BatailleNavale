package M1.reseau.serveur.serveur;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServeurUDP {

    public final static int port = 7777;//8532;
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];

    public static void main(String argv[]) throws Exception
    {
        DatagramSocket socket = new DatagramSocket(port);
        while(true) {
            System.out.print("boucle");
            DatagramPacket data = new DatagramPacket(buffer, buffer.length);
            socket.receive(data);
            System.out.println(data.getAddress());
            socket.send(data);
        }

    }


}
