package M1.reseau.serveur.serveur.testServeurs;


import java.net.DatagramPacket;
import java.net.DatagramSocket;


        class ServeurEcho
{
    final static int port = 8532;
    final static int taille = 1024;
    final static byte buffer[] = new byte[taille];

    public static void main(String argv[]) throws Exception
    {
        DatagramSocket socket = new DatagramSocket(port);
        while(true)
        {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(data.getAddress());
            socket.send(data);
        }
    }
}