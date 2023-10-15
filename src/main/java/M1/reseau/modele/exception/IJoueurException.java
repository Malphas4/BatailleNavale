package M1.reseau.modele.exception;

public class IJoueurException extends Exception {

    private String err;

    /**
     * Constructeur pour les exceptions sur les joueurs
     * @param err
     */
    public IJoueurException(String err) {
        super(err);
        setErr(err);
    }

    /**
     * Getter pour le message d'erreur
     * @return
     */
    public String getErr() {
        return err;
    }

    /**
     * Setter pour le message d'erreur
     * @param err
     */
    public void setErr(String err) {
        if (err.trim().isEmpty()) throw new IllegalArgumentException("IJoueurException : ");
        this.err = err;
    }
}
