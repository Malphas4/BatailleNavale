package M1.reseau.serveur.singletons;

import java.io.Serializable;

public class SingletonTCP implements Serializable
{
    /** Constructeur privé */
    private SingletonTCP()
    {

    }

    /** Instance unique pré-initialisée */
    private static SingletonTCP INSTANCE = new SingletonTCP();

    /** Point d'accès pour l'instance unique du singleton */
    public static SingletonTCP getInstance()
    {   return INSTANCE;
    }

    /** Sécurité anti-désérialisation */
    private Object readResolve() {
        return INSTANCE;
    }
}