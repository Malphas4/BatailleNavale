package M1.reseau.serveur.serveur.game;

import M1.reseau.model.exception.IPartieException;

import java.io.IOException;

public class ChronoThread extends Thread {

    private SalonThread _salon;

    private int _time;

    public ChronoThread() {

    }

    public ChronoThread(SalonThread _salon) {
        set_salon(_salon);
        set_time(30);
    }

    public SalonThread get_salon() {
        return _salon;
    }

    public void set_salon(SalonThread _salon) {
        this._salon = _salon;
    }

    public int get_time() {
        return _time;
    }

    public void set_time(int _time) {
        this._time = _time;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            String _message = null;
            String _pseudo = null;

            try {
                _pseudo = _salon.get_gameService().get_partie().getJoueurCourant().get_pseudo();
            } catch (IPartieException e) {
                throw new RuntimeException(e);
            }

            _message = "chrono;"
                    + _salon.get_id()
                    + ";" + _pseudo
                    + ";" + get_time();

            try {
                if (get_time() <= 0) {
                    set_time(30);
                    _salon.get_gameService().get_partie().tourSuivant();
                }
                _salon.get_j1().message(_message);
                _salon.get_j2().message(_message);
            } catch (IOException e) {
                System.err.println("ChronoThread : Fail to send message.");
            } catch (IPartieException e) {
                System.err.println("ChronoThread : Fail to switch game round.");
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("ChronoThread : Fail to sleep.");
            }

            set_time(get_time() - 1);
        }
    }
}
