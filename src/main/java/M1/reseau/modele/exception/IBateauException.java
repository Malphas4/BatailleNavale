package M1.reseau.modele.exception;

public class IBateauException extends Exception {
    private String _err;

    public IBateauException(String _err) {
        this._err = _err;
    }

    public String get_err() {
        return _err;
    }

    public void set_err(String _err) {
        this._err = _err;
    }
}
