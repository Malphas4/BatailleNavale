package M1.reseau.serveur;

public  interface ISalon {

    public String getNom();
    public String getDescription();
    public int getNbMax();
    public int getNbConnecte();
    public void setNbConnecte(int nbConnecte);
    public void incrementNbConnecte();
    public void decrementNbConnecte();
}