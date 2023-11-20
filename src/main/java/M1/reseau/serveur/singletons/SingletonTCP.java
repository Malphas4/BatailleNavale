package M1.reseau.serveur.singletons;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SingletonTCP implements Serializable
{
    InetAddress host = InetAddress.getLocalHost();
   //pour socket client AF
   Socket socket = null;
    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;
    private static ServerSocket serveur;
    int port = 18000;
    /** Constructeur privé */
    private SingletonTCP() throws IOException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;

        serveur = new ServerSocket(port);
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        System.out.println("CLient TCp lancé au port 18 000");

    }

    /** Instance unique pré-initialisée */
    private static SingletonTCP INSTANCE;

    static {
        try {
            INSTANCE = new SingletonTCP();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonTCP getInstance()
    {
        return INSTANCE;
    }


    public static void fermetureSocket()  {
        try{
            if(serveur != null) {

                oos.close();
                ois.close();
                serveur.close();
            }
        }catch (IOException e){ e.printStackTrace();}

    }

    /*envoi d'un message TCP
    *
    *
    * */
    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //recuperation de la socket output
        OutputStream output = socket.getOutputStream();
        output.write(data);
        PrintWriter writer = new PrintWriter(output, true);
        output.write(data);


    }

    /**
     * reception d'un message avec TCP
     *  @return message recu de type String
     *  @throws IOException
     *  La lecture d'un socket vide est une erreur pour l'intant
     *  une option pour ignorer le message vide est à faire
     */
    public String reception() throws IOException {

        InputStream in = socket.getInputStream();
        //You can wrap the InputStream object in an InputStreamReader or BufferedReader to read data at higher level (character and String). For example, using InputStreamReader:
        InputStreamReader reader = new InputStreamReader(in);
        String s = reader.toString();
        System.out.printf("messag du serveur : "+s+"\n");
        return s;
    }
        
}
/*

//close res

*/
