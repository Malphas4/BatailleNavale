package M1.reseau.client2.console;

import M1.reseau.client.controller.ControleurMenu;
import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.JoueurHandler;
import M1.reseau.serveur.singletons.SingletonTCP;
import M1.reseau.serveur.singletons.SingletonUDP;
import M1.reseau.utilities.InformationsUtilisateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
     */
    static DatagramSocket socketUDP = null;
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
    private ClientUDP(String ad) throws UnknownHostException, SocketException {
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
     *
     * @param s message à envoyer de type String
     * @throws IOException          gestion IO exception
     * @throws UnknownHostException gestion host inconnu (ici localhost initialement
     * @throws SocketException      La demande de message est bloquante pour l'intant
     *                              Une option pour abandonner la'tente de réponse est à prévoir en 2 3 lignes de codes
     */
    public void message(String s) throws IOException {
        //Message de type string deviens de type data pour l'envoi
        byte[] data = (s).getBytes();
        //creation du packet UDP
        DatagramPacket packet = new DatagramPacket(data, data.length, _adr, _port);
        // envoi du paquet via la socket PROBLEME ICI CAR SERVEUR KAPUT
        System.out.println("envois de : " + s);
        socketUDP.send(packet);
    }

    /**
     * reception d'un message avec UDP
     *
     * @return message recu de type String
     * @throws IOException La lecture d'un socket vide est une erreur pour l'intant
     *                     une option pour ignorer le message vide est à faire
     */
    public String reception() throws IOException {
        DatagramPacket recu = new DatagramPacket(_buffer, _taille);
        try {
            //socketUDP.setSoTimeout( 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        socketUDP.receive(recu);
        _buffer = recu.getData();
        String data = new String(_buffer, 0, recu.getLength());
        System.out.println("Data recu : " + data + "\n");
        _buffer = new byte[_taille];
        return data;
    }

    /**
     * fermeture du socket
     */
    public static void fermetureSocket() {
        try {
            if (socketUDP != null) socketUDP.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    void connexionUDP() {
        boolean co = false;
        while (!co) {
            System.out.println("votre pseudo ?");
            s = scan.next();
            System.out.println("entrer votre mdp");
            str = scan.next();
            if (str.trim().isEmpty() || str.trim().equals("pseudonyme")) {//alerte AF
                System.out.println("probleme mdp");
                String _connexion = "0C;".concat(s).concat(":").concat(str);

                InformationsUtilisateur.getInstance().set_pseudo(s);

                try {
                    message(_connexion);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String _messageServeur = null;
                try {
                    _messageServeur = reception();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // traitement de la chaine COR pour savoir quel message traiter
                String[] _msgT = _messageServeur.split(";");
                //message valide selon COR a la fin
                if (_msgT[0].equals("1C")) {
                    co = true;

                }
                if (_msgT[0].equals("1D")) {

                    System.out.println("Echec de la connexion; Procedure d'inscription");
                    try {
                        inscription();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }


        }
    }


    @FXML
    void inscription() throws IOException {
        Boolean inscr = true;
        while (inscr) {
            System.out.print("inscription\n");
            //recuperation du stage si changement de fenetre
            System.out.println("pseudo ?");
            String _pseudo = scan.next();
            System.out.println("mot de passe ?");
            String _mdpInput = scan.next();
            String _inscription = "";
            if (_pseudo.trim().isEmpty() || _pseudo.trim().equals("pseudonyme")) {
                System.out.println("Pseudo manquant !");
            } else if (_mdpInput.trim().isEmpty()) {
                System.out.println("Mot de passe manquant !");
            } else if (!(_pseudo.isEmpty() || _mdpInput.isEmpty())) {
                _inscription = "12;".concat(_pseudo).concat(":").concat(_mdpInput);
                message(_inscription);
                String _messageServeur = reception();

                // traitement de la chaine COR pour savoir quel message traiter
                String[] _msgT = _messageServeur.split(";");
                //messahe valide selon COR a la fin
                if (_msgT[0].equals("12")) {
                    inscr = false;
                    InformationsUtilisateur.getInstance().set_pseudo(_pseudo);
                }
                if (_msgT[0].equals("13")) {
                    System.out.println("Compte existant, essayez un autre pseudo");
                    //inscr=false;
                }
                if (_msgT[0].equals("20")) {
                    System.out.println("Inscription faite");
                    inscr = false;
                }
                if (_msgT[0].equals("1E")) {
                    System.out.println("Connexion impossible");


                }
            }
        }
    }

    void choisirSalon() {
        String[] _strSalons;
        int _salonChoisi;
        int nbSalons;
        String _msgSalons;
        String _demandeSalons="NBsalons?";
        Boolean typeP = true;
        while (typeP) {
            System.out.println(" partie solo ou partie 1v1 ?\n tapez bot pour 1vbot \net pvp pour 1v1");
            try {
                message(_demandeSalons);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String strP = scan.next();
            if (strP.equals("1v1")) {
                InformationsUtilisateur.getInstance().set_typePartie(1);
                System.out.println("partie multi");
                try {
                    ClientTCP.message("join;".concat(String.valueOf(_salonChoisi))
                            .concat(";")
                            .concat(InformationsUtilisateur.getInstance().get_pseudo()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("envoi du salon choisi TCP");
                //reception du pseudo adversaire
                String s = ClientTCP.reception();
                String[] sT = s.split(";");
                InformationsUtilisateur.getInstance().set_adversaire(sT[2]);

                InformationsUtilisateur.getInstance().set_salon(_salonChoisi);
                typeP = false;
            } else if (strP.equals("bot")) {
                System.out.println("partie solo");
                //demande de connexion
                try {
                    SingletonTCP.getInstance().message("joinbot;".
                            concat(String.valueOf(InformationsUtilisateur.getInstance().get_salon())).
                            concat(";").
                            concat(InformationsUtilisateur.getInstance().
                                    get_pseudo()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("envoi du salon choisi TCP");
                //reception du pseudo adversaire
                String s = ClientTCP.reception();
                String[] sT = s.split(";");
                InformationsUtilisateur.getInstance().set_adversaire(sT[2]);

                InformationsUtilisateur.getInstance().set_salon(_salonChoisi);
                typeP = false;
            }
            if (typeP) System.out.println("pas de salon choisi");
        }
    }

}
