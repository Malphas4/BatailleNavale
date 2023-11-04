package M1.reseau.modele.exception;

public class ICaseException extends Exception {
    private String _err;

    public ICaseException(String err) {
        super(err);
        setErr(err);
    }

    public String getErr() {
        return _err;
    }

    public void setErr(String _err) {
        if (_err.trim().isEmpty()) throw new IllegalArgumentException("IGrilleException : le message d'erreur est vide.");
        this._err = _err;
    }
}
