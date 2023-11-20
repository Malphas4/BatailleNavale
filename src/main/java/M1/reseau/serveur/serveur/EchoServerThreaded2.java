package M1.reseau.serveur.serveur;
import M1.reseau.model.exception.IGrilleException;
import M1.reseau.model.exception.IJoueurException;
import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.game.local.PartieServeur;
import M1.reseau.model.player.classic.JoueurNormal;
import M1.reseau.model.player.visitor.VisitorEstTire;
import M1.reseau.model.player.visitor.VisitorTirer;
import M1.reseau.model.world.element.ICase;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EchoServerThreaded2 {



    /**
     * Threaded Echo Server, pre-allocation scheme.
     * Each Thread waits in its accept() call for a connection; this synchronizes
     * on the serversocket when calling its accept() method.
     * @author Ian F. Darwin.
     */


    public static final int ECHOPORT = 7;

    public static final int NUM_THREADS = 4;

    /** Main method, to start the servers. */
    public static void main(String[] av) {
        new EchoServerThreaded2(ECHOPORT, NUM_THREADS);
    }

    /** Constructor */
    public EchoServerThreaded2(int port, int numThreads) {

        ServerSocket servSock;

        try {
            servSock = new ServerSocket(port);

        } catch(IOException e) {
            /* Crash the server if IO fails. Something bad has happened */
            throw new RuntimeException("Could not create ServerSocket ", e);
        }

        // Create a series of threads and start them.
        for (int i=0; i<numThreads; i++) {
            new Handler(servSock, i).start();
        }
    }

    /** A Thread subclass to handle one client conversation. */
    class Handler extends Thread {
        ServerSocket servSock;
        int threadNumber;
        ArrayList<Handler> _handlerTCP = new ArrayList<>();

        /** Construct a Handler. */
        Handler(ServerSocket s, int i) {
            servSock = s;
            threadNumber = i;
            setName("Thread " + threadNumber);
        }

        public void run() {
            PartieServeur _partie = new PartieServeur(8, 8);

            /* Wait for a connection. Synchronized on the ServerSocket
             * while calling its accept() method.
             */
            while (true) {
                try {
                    System.out.println( getName() + " waiting");

                    Socket clientSocket;
                    // Wait here for the next connection.
                    synchronized(servSock) {
                        clientSocket = servSock.accept();
                    }
                    System.out.println(getName() + " starting, IP=" +
                                    clientSocket.getInetAddress());
                    BufferedReader is = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
                    PrintStream os = new PrintStream(
                            clientSocket.getOutputStream(), true);
                    String line;
                    while ((line = is.readLine()) != null) {
                        // messageAction(_partie, line + "\r\n");
                        os.print(line + "\r\n");
                        os.flush();
                    }
                    System.out.println(getName() + " ENDED ");
                    clientSocket.close();
                } catch (IOException ex) {
                    System.out.println(getName() + ": IO Error on socket " + ex);
                    return;
                }
            }
        }

        /*private PartieServeur messageAction(PartieServeur _partie, String _message) {
            String[] _sp = _message.split(";");
            VisitorTirer _tirer = new VisitorTirer();
            VisitorEstTire _estTirer = new VisitorEstTire();
            try {
                if (_message.contains("ajouter")) { // ajouter;[pseudo]
                    _partie.ajouterJoueur(_sp[1]);
                } else if (_message.contains("commencer")) { // commencer
                    _partie.commencer();
                } else if (_message.contains("tirer")) { // tirer;[x];[y]
                    JoueurNormal _joueur = (JoueurNormal) _partie.getJoueurCourant();
                    // On récupère le joueur adverse
                    JoueurNormal _joueurAdverse = (JoueurNormal) _partie.getJoueurAdverse();
                    // On tire sur la grille de touche du joueur 1 (courant)
                    _tirer.set_grille(_joueurAdverse.get_grilleJoueur());
                    // On récupère la case
                    ICase _case = _joueurAdverse
                            .get_grilleJoueur()
                            .get_caseParCoord(Integer.parseInt(_sp[1]),
                                    Integer.parseInt(_sp[2]));
                    _tirer.set_case(_case);
                    _joueur.accepte(_tirer);

                    // On tire sur la grille du joueur 2 (adverse) sur sa grille
                    _estTirer.set_grille(_joueurAdverse.get_grilleJoueur());
                    _estTirer.set_case(_case);
                    _joueurAdverse.accepte(_estTirer);

                    // TODO Envoyer message au joueur adverse pour dire que la case [x][y] a été touché
                    // Côté client, ça veut dire que c'est le tour du joueur

                    // Le joueur passe son tour
                    _partie.tourSuivant();
                } else if (_message.contains("fin")) { // fin
                    _partie.fin();
                } else if (_message.contains("chat")) { // chat;[pseudo];[message]

                } else if (_message.contains("broadcast")) { // broadcast;[pseudo];[message]

                }
            } catch (IPartieException | IGrilleException | IJoueurException e) {
                throw new RuntimeException(e);
            }

            return _partie;
        }*/
    }
}

