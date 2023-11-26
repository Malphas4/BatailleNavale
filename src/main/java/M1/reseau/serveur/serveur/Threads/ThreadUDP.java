package M1.reseau.serveur.serveur.Threads;

import M1.reseau.model.player.Joueur;
import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.JoueurHandler;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ThreadUDP extends Thread{

    //variables
    final static int _taille = 1024;
     byte[] _buffer = new byte[_taille];
    int _port = 7777;
    String _msg;
    DatagramSocket _socketUDP;
    InetAddress _adr;
    //ConcurrentHashMap
    Map<String,String>  _listeJoueur= new ConcurrentHashMap<String,String>();


    {
        try {
            _adr = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    Thread _t; // contiendra le thread
    ServeurGlobale _sv;

    public ThreadUDP(ServeurGlobale sv)  {

        _sv=sv;
        // création d'une socket, sans la lier à un port particulier
        try {
            setName("ServeurUDP");
            _socketUDP = new DatagramSocket(7777);
        _listeJoueur.put("test", "0000");
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {

        System.out.print("UDP en arriere plan\n");
        while (! isInterrupted()) {
            try {
                _adr=InetAddress.getByName("localhost") ;
                String reponse = "";
//                System.out.print("Serveur UDP en ecoute");
                //Creaton du packet a recuperer
                final  int _taille = 1024;
                byte[] _buffer = new byte[_taille];
                DatagramPacket recu = new DatagramPacket(_buffer, _taille);
                //recuperation du packet
                //recieve est bloquant
                _socketUDP.receive(recu);
                //recuperation des données
                String _msg = new String(recu.getData(), 0, recu.getLength());
              //  System.out.println("Data par serveur avec UDP recu : " + _msg + "\n");
                //Separation du message recu
                String[] _msgT = _msg.split(";");
                //traitement du message
                if (_msgT[0].equals("0C")) {
                    String[] _msgID = _msgT[1].split(":");
                    if (_listeJoueur.containsKey(_msgID[0]) &&_listeJoueur.get(_msgID[0]).equals(_msgID[1])){
                        reponse = "1C";
                    } else if (_listeJoueur.containsKey(_msgID[0])) {
                        reponse = "1E";
                    } else
                        reponse = "1D";
                } else if (_msg.equals("NBsalons?")) {
                    int _nbSalons = _sv.getNbSalons();
                    reponse = "NBsalons;".concat(String.valueOf(_nbSalons));
                    for (int i = 0; i < _nbSalons; i++) {
                        SalonThread unSalon=_sv.get_salon(i);
                        reponse.concat(";");
                        reponse.concat(String.valueOf(unSalon));
                    }
                }else if(_msgT[0].equals("12")){
                    System.out.println("inscription en cours");
                    boolean dejaIsncrit=true;
                    String[] _msgID = _msgT[1].split(":");
                    String _speudo= _msgID[0];
                    String _mdp= _msgID[1];
                    //si un joueur avec le meme pseudo ne c'est aps deja incrit, on creer le joueur handler
                    //Iterator<String> it = _listeJoueur.keySet().iterator();
                    //while(it.hasNext()){
                       // String key = it.next();
                       // if(key.equals("3")) _listeJoueur.put(key+"new", "new3");
                    //}
                    if (_listeJoueur.containsKey(_speudo)){
                        System.out.println("inscription refusée, pseudo deja utilisé");
                        reponse = "13";
                    }else{
                        _listeJoueur.put(_speudo,_mdp);
                        System.out.println("inscription acceptée");
                        reponse = "20";
                    }


                    //while(it1.hasNext()){
                     //   String key = it1.next();
                      //  if(key.equals("3")) myMap.put(key+"new", "new3");
                   // }


                }else reponse = "err";
                _buffer = new byte[_taille];
                _buffer = reponse.getBytes();
                DatagramPacket response = new DatagramPacket(_buffer, _buffer.length, recu.getAddress(), recu.getPort());
              //  System.out.println("Serveur  envoie avec UDP "+reponse);
                _socketUDP.send(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
