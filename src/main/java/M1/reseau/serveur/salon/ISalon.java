package M1.reseau.serveur.salon;

import M1.reseau.model.game.Partie;

public  interface ISalon {

    Partie _partie = null;

    public String getNom();
    public String getDescription();
    public int getNbMax();
    public int getNbConnecte();
    public void setNbConnecte(int nbConnecte);
    public void incrementNbConnecte();
    public void decrementNbConnecte();

    String infos();
}