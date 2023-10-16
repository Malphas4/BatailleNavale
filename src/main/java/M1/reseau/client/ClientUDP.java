package M1.reseau.client;
import M1.reseau.serveur.ServeurUDP;

import java.io.*;
import java.net.*;
public class ClientUDP {
    final static int taille = 1024;


    final static byte buffer[] = new byte[taille];

    public static void main(String argv[]) throws Exception
    {
        InetAddress serveur = InetAddress.getByName(argv[0]);
        int length = argv[1].length();
        byte buffer[] = argv[1].getBytes();
        DatagramPacket dataSent = new DatagramPacket(buffer,length,serveur, ServeurUDP.port);
        DatagramSocket socket = new DatagramSocket();

        InetAddress adr;
        DatagramPacket packet;




// adr contient l'@IP de la partie serveur
        adr = InetAddress.getByName("localhost");
// données à envoyer : chaîne de caractères
        byte[] data = (new String("youpi")).getBytes();
// création du paquet avec les données et en précisant l'adresse du serveur
// (@IP et port sur lequel il écoute : 7777)
        packet = new DatagramPacket(data, data.length, adr, 7777);
// création d'une socket, sans la lier à un port particulier
        // création d'une socket, sans la lier à un port particulier
        socket = new DatagramSocket();
    // envoi du paquet via la socket
        socket.send(packet);



        socket.send(dataSent);

        DatagramPacket dataRecieved = new DatagramPacket(new byte[length],length);
        socket.receive(dataRecieved);
        System.out.println("Data recieved : " + new String(dataRecieved.getData()));
        System.out.println("From : " + dataRecieved.getAddress() + ":" + dataRecieved.getPort());
    }
}

