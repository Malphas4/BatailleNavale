package M1.reseau.serveur.singletons;

import java.io.*;
import java.net.*;

public class SingletonTCP extends  Thread implements Serializable
{
    InetAddress host;

    {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    //pour socket client AF
   Socket socket = null;
   public  BufferedReader in = null;
    public  PrintWriter out = null;


    private static SingletonTCP _singletonTCP=null;

    int port = 9999;
    /** Constructeur privé */
    private SingletonTCP() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            socket  = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("CLient TCp lancé au port "+port);

    }

    /** Instance unique pré-initialisée */
    private static SingletonTCP INSTANCE;

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonTCP getInstance()  {
        if (_singletonTCP == null) {
            _singletonTCP = new SingletonTCP();
        }
        return SingletonTCP._singletonTCP;
    }


    public void fermetureSocket()  {
        try{
            if(socket != null) {

                in.close();
                out.close();
                socket.close();
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
        System.out.printf("envoi du message TCP : "+s+"\n");
        out.println(s);

    }
    /**
     * reception d'un message avec TCP
     *  @return message recu de type String
     *  @throws IOException
     *  La lecture d'un socket vide est une erreur pour l'intant
     *  une option pour ignorer le message vide est à faire
     */
    public String reception() throws IOException {

        //You can wrap the InputStream object in an InputStreamReader or BufferedReader to read data at higher level (character and String). For example, using InputStreamReader:
        String s= in.readLine();
        System.out.println("message recu TCP" +s);
        return s;
    }
        
}

