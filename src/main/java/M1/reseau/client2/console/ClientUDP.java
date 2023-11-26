package M1.reseau.client2.console;

import M1.reseau.client.controller.ControleurMenu;
import M1.reseau.serveur.singletons.SingletonUDP;
import M1.reseau.utilities.InformationsUtilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class ClientUDP extends Thread {
    int a;
    String s;
    Scanner scan = new Scanner(System.in);





    private static M1.reseau.serveur.singletons.SingletonUDP _singletonUDP;

    /**
     * Données de l'instance du socket UDP
     *
     */
    static DatagramSocket socketUDP=null;
    final int _taille = 1024;
    byte[] _buffer = new byte[_taille];
    int _port = 7777;
    InetAddress _adr;

    BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
    String str;

    {
        try {
            _adr = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructeur privé
     */
    private ClientUDP( String ad) throws UnknownHostException, SocketException {
        //_port = port;
        this._adr = InetAddress.getByName(ad);
        // création d'une socket, sans la lier à un port particulier
        socketUDP = new DatagramSocket();

    }
    /**
     * Constructeur privé par défaut
     * un seul socket UDP est nécessaire pour le serveur
     */
    public ClientUDP() throws UnknownHostException, SocketException {
        InetAddress _adr = InetAddress.getByName("localhost");
        socketUDP = new DatagramSocket();
    }
    /**
     * envoi d'un message par UDP
     * @param s message à envoyer de type String
     *  @throws IOException gestion IO exception
     * @throws UnknownHostException gestion host inconnu (ici localhost initialement
     * @throws SocketException
     * La demande de message est bloquante pour l'intant
     * Une option pour abandonner la'tente de réponse est à prévoir en 2 3 lignes de codes
     */
    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //creation du packet UDP
        DatagramPacket packet = new DatagramPacket(data, data.length, _adr, _port);
        // envoi du paquet via la socket PROBLEME ICI CAR SERVEUR KAPUT
        System.out.println("envois de : "+s);
        socketUDP.send(packet);
    }

    /**
     * reception d'un message avec UDP
     *  @return message recu de type String
     *  @throws IOException
     *  La lecture d'un socket vide est une erreur pour l'intant
     *  une option pour ignorer le message vide est à faire
     */
    public String reception() throws IOException {
        DatagramPacket recu = new DatagramPacket(_buffer, _taille);
        try {
            //socketUDP.setSoTimeout( 5000);
        }catch (Exception e){ e.printStackTrace();}

        socketUDP.receive(recu);
        _buffer=recu.getData();
        String data = new String(_buffer,0,recu.getLength());
        System.out.println("Data recu : " + data + "\n");
        _buffer= new byte[_taille];
        return data;
    }

    /**
     * fermeture du socket
     */
    public static void fermetureSocket(){
        try{
            if(socketUDP != null) socketUDP.close();
        }catch (Exception e){ e.printStackTrace();}

    }



    boolean connexionUDP(){

        boolean co=false;
        while(!co){
            System.out.println("votre pseudo ?");
            s = scan.nextLine();
            System.out.println("entrer votre mdp");
            str = scan.nextLine();
            if (str.trim().isEmpty()||str.trim().equals("pseudonyme")) {//alerte AF
                System.out.println("probleme mdp");
                String _connexion = "0C;".concat(s).concat(":").concat(str);
                //System.out.print(_connexion);
                //Mise a jour du pseudonyme dans l'instance
                InformationsUtilisateur.getInstance().set_pseudo(s);

                try {
                    message(_connexion);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String _messageServeur= null;
                try {
                    _messageServeur = reception();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // traitement de la chaine COR pour savoir quel message traiter
                String[] _msgT = _messageServeur.split(";");
                //message valide selon COR a la fin
                if (_msgT[0].equals("1C")) {
                    co= true;

                }
                if (_msgT[0].equals("1D")) {

                    System.out.println("Echec de la connexion; Procedure d'isncription");
                    inscritpion();

                }
            }


        }


    }


    @FXML
    void inscription() throws IOException {
        System.out.print("inscription\n");
        //recuperation du stage si changement de fenetre
        
        String _pseudo=_champPseudo.getText();
        String _mdpInput=_mdp.getText();
        String _inscription="";


        if (_pseudo.trim().isEmpty()||_pseudo.trim().equals("pseudonyme")) {
            //alerte AF
            _msgConsigne.setText("Pseudo manquant ! \nVeuillez entrer votre speudo et votre mot de passe");
            System.out.print("pseudo vide\n");
        } else if (_mdpInput.trim().isEmpty()){
            _msgConsigne.setText("Mot de passe manquant ! \nVeuillez entrer votre mot de passe");
            System.out.print("mdp vide\n");
        }else if(!( _pseudo.isEmpty() ||_mdpInput.isEmpty())) {
            _inscription = "12;".concat(_pseudo).concat(":").concat(_mdpInput);
            System.out.print(_inscription);
            //Mise a jour du pseudonyme dans l'instance
            SingletonUDP.getInstance().message(_inscription);
            _messageServeur = SingletonUDP.getInstance().reception();

            //injection du pseudo dans le controleur Lobby et Grille
            //FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            //ControleurGrille controleurGrille = grilleLoader.getController();
            //appel d'une fonction de Grille ou appel de setPseudo par exemple


            // traitement de la chaine COR pour savoir quel message traiter
            String[] _msgT = _messageServeur.split(";");
            //messahe valide selon COR a la fin
            if (_msgT[0].equals("12")) {
                stageActuel.setScene(sceneMenu);
                InformationsUtilisateur.getInstance().set_pseudo(_pseudo);


            }
            if (_msgT[0].equals("13")) {
                _msgConsigne.setText("Compte existant, essayez un autre pseudo");

            }
            if (_msgT[0].equals("20")) {
                _msgConsigne.setText("Inscription faite");

            }
            if (_msgT[0].equals("1E")) {
                _msgConsigne.setText("Connexion impossible");

            }
        }
        stageActuel.show();


    }
}