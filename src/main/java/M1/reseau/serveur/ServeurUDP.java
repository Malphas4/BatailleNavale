package M1.reseau.serveur;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class ServeurUDP {

    public final static int port = 8532;
    final static int taille = 1024;
    final static byte[] buffer = new byte[taille];

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
    public DatagramSocket initialiseUDP (int port) throws Exception{

        try {
            DatagramSocket socket = new DatagramSocket(port);
            byte[] buffer = new byte[taille];
            return socket;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void attenteConnexion( DatagramSocket socket ) throws Exception{
        while(true)
        {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(data.getAddress());
            socket.send(data);
        }
    }
    public void finConnexion(DatagramSocket socket ) throws Exception{
        while(true)
        {
            DatagramPacket data = new DatagramPacket(buffer,buffer.length);
            socket.receive(data);
            System.out.println(data.getAddress());
            socket.send(data);
        }
    }
    public void attributionID(int buffer, DatagramSocket socket ) throws Exception{

    }


}
