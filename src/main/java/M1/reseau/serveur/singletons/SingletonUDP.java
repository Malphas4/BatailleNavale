package M1.reseau.serveur.singletons;

public class SingletonUDP
{
    /** Constructeur privé
     un seul socket UDP est nécessaire pour le serveur
     */
    private SingletonUDP()
    {}

    /** Holder */
    private static class SingletonHolder
    {
        /** Instance unique non préinitialisée */
        private final static SingletonUDP instance = new SingletonUDP();
    }

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonUDP getInstance()
    {
        return SingletonHolder.instance;
    }
}