package M1.reseau.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
     * This class implements java socket M1.reseau.ClientTCP
     * @author Malphas
     *
     */
    public class ClientTCP {
        /**
         * A rajouter DP état pour la file d'attente
         * @param args
         * @throws UnknownHostException
         * @throws IOException
         * @throws ClassNotFoundException
         * @throws InterruptedException
         */

        static Scanner input=new Scanner(System.in);

        public static void main(String[] args)throws IOException, ClassNotFoundException, InterruptedException{
            //get the localhost IP address, if server is running on some other IP, you need to use that
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            boolean _boucle=true;

            String _message;
            while (_boucle){
                //establish socket connection to server
                socket = new Socket(host.getHostName(), 9876);
                //write to socket using ObjectOutputStream
                oos = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("votre message :");
                _message=input.nextLine();

                System.out.println("envoi du message :"+_message);
                oos.writeObject(_message);
                //read the server response message
                ois = new ObjectInputStream(socket.getInputStream());
                String messagereception = (String) ois.readObject();

                System.out.println("Message recu: " + messagereception);

                if(_message=="exit") {
                    _boucle=false;
                    //close resources
                    ois.close();
                    oos.close();
                    socket.close();
                }
                Thread.sleep(100);
            }
        }
    }

