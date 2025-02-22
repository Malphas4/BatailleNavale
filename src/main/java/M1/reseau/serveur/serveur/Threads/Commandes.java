package M1.reseau.serveur.serveur.Threads;

import M1.reseau.serveur.salon.Salon;
import M1.reseau.serveur.salon.SalonPrive;
import M1.reseau.serveur.serveur.ServeurGlobale;
import M1.reseau.serveur.serveur.game.SalonThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


//** Classe qui gère les commandes tapées dans la console **
// implémentation de l'interface Runnable (une des 2 méthodes pour créer un thread)
public class Commandes implements Runnable
{
    ServeurGlobale _serveurmain; // pour utilisation des méthodes de la classe principale

    BufferedReader _in; // pour gestion du flux d'entrée (celui de la console)
    String _strCommande=""; // contiendra la commande tapée
    Thread _t; // contiendra le thread

    //** Constructeur : initialise les variables nécessaires **
    public Commandes(ServeurGlobale serveur)
    {
        _serveurmain=serveur; // passage de local en global
        // le flux d'entrée de la console sera géré plus pratiquement dans un BufferedReader
        _in = new BufferedReader(new InputStreamReader(System.in));
        _t = new Thread(this); // instanciation du thread
        _t.start(); // démarrage du thread, la fonction run() est ici lancée
    }

    //** Methode : attend les commandes dans la console et exécute l'action demandée **
    public void run() // cette méthode doit obligatoirement être implémentée à cause de l'interface Runnable
    {
        try
        {
            // si aucune commande n'est tapée, on ne fait rien (bloquant sur _in.readLine())
            while ((_strCommande=_in.readLine())!=null) {
                if (_strCommande.equalsIgnoreCase("quit")) // commande "quit" detectée ...
                    System.exit(0); // ... on ferme alors le serveur
                else if (_strCommande.equalsIgnoreCase("total")) // commande "total" detectée ...
                {
                    // ... on affiche le nombre de clients actuellement connectés
                    System.out.println("Nombre de connectes : " + ServeurGlobale.sv.getNbXlients());
                    System.out.println("--------");
                }/*
                else
                {
                    // si la commande n'est ni "total", ni "quit", on informe l'utilisateur et on lui donne une aide
                    System.out.println("Cette commande n'est pas supportee");
                    System.out.println("Quitter : \"quit\"");
                    System.out.println("Nombre de connectes : \"total\"");
                    System.out.println("--------");
                }*/
                /*
                System.out.println("commandes !\n" +
                        "ls pour la liste des salons\n" +
                        "lj liste des joueurs\n" +
                        "ns [difficulte] [nom] [mot de passe] creation d'un nouveau salon avec mdp facultatif\n" +
                        "exit pour clore le serveur\n");
                String str1 = input.nextLine();
                */
                /*
                if ("exit".equals(str1)) {
                    System.out.println("Au revoir, extinction du serveur");
                    _statusConsole = false;
                    _statusServeur = false;
                    _statusServeurUDP = false;


                } */
                else if (_strCommande.equalsIgnoreCase("ls")) {
                    System.out.print("Informations des salons :\n");
                    //Vector _listeSalons=new Vector(Collection<Salon>);

                    for (SalonThread iterSalon : ServeurGlobale.sv.get_tabSalons()) {
                        if (ServeurGlobale.sv.get_tabSalons().isEmpty())
                            System.out.print("\tAucun salon n'est disponible\n");
                        else
                            System.out.println("\t"
                                    + iterSalon.get_id()
                                    + " - "
                                    + iterSalon.get_nom()
                            );
                    }

                /*} else if (_strCommande.equalsIgnoreCase("ns")) {
                    System.out.print("creation d'un salon :\n");
                    System.out.print("Difficulté du nouveau salon ? \n options possibles \n f pour facile \n d pour difficile \n m pour moyen" + "\n");
                    String difficulte = _in.readLine();

                    System.out.print("nb joueur de base 2 pas modifiable pour l'instant\n");
                    //int _nbJoueur=2;
                    System.out.print("mot de passe? laisser vide pour ne pas en attribuer\n");
                    String _mdp = _in.readLine();
                    if (_mdp.trim().isEmpty()) {
                        _listeSalons.add(new SalonThread(difficulte, "partie classique"));
                    } else {
                        _listeSalons.add(new SalonPrive(difficulte, "partie privée", _mdp));
                    }*/
                } else if (_strCommande.equalsIgnoreCase("create")) { /* create */
                    System.out.println("\tCreation du salon en cours ...");
                    SalonThread _salon = new SalonThread();
                    System.out.println("\tLe salon "
                            + _salon.get_id() + " - " + _salon.get_nom()
                            + " a ete cree !");

                    ServeurGlobale.sv.addSalon(_salon);
                    System.out.println("\tLe salon est en ligne.");
                } else {
                    System.err.println("La commande " + _strCommande + " n'a pas été reconnue.");
                }

                System.out.flush(); // on affiche tout ce qui est en attente dans le flux
            }
        }
        catch (IOException e) {}
    }
}
