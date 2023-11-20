package M1.reseau.serveur.salon;

import M1.reseau.serveur.serveur.chatGlobal.ClientHandler;

import java.util.ArrayList;

public class Salon implements ISalon {

    public String _nom;
    String _description;
    int _nbMaxJoueur=2;
    int _nbConnecte=0;
    //variable qui decide du temsp pour chaque tours
    int _temps=30;
    //variable gérant la différence de temps entre l'horloge serveur et la partie
    int debutPartie=0;

    /**
     * Les handlers contiennent les thread des 2 joueurs
     * afin de communiquer avec le chat local
     * et de s'envoyer les données
     * */
    ClientHandler _handlerJ1=null;
    ClientHandler _handlerJ2=null;
    // ArrayList vide qui contient les joueurs dont il faut transmettre les infos de la partie
    // pour une partie a n joueurs, il faudrait creer une autre array liste des joueurs et utiliser leurs pseudo pour les differencier
    ArrayList<ClientHandler> spectateurs=new ArrayList<>();



    //fonction pour récupérer l'horloge serveur
    //classe singleton possible AF.
    public int getServeurHorloge(
    ) {
        return debutPartie;
    }





    public  Salon(String nom, String description, int nbMax) {
        this._nom = nom;
        this._description = description;
        this._nbMaxJoueur = nbMax;
        this._nbConnecte = 0;
    }
    public  Salon() {
        this._nom = "NomDeBase";
        this._description = "partie classique";
    }

    public Salon(String difficulte, String _description) {
        this._nom = "NomDeBase";
        this._description = "partie classique";
    }

    public String getNom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public String getDescription() {
        return _description;
    }

    public int getNbMax() {
        return _nbMaxJoueur;
    }

    public int getNbConnecte() {
        return _nbConnecte;
    }

    public void setNbConnecte(int nbConnecte) {
        this._nbConnecte = nbConnecte;
    }

    public void incrementNbConnecte() {
        this._nbConnecte++;
    }

    public void decrementNbConnecte() {
        this._nbConnecte--;
    }

    @Override
    public String infos() {
        return "nom : "+_nom+"\n description : "+_description +"\n taille du salon : "+
                _nbMaxJoueur +" nombre de connection au salon :"+_nbConnecte;
    }
}
