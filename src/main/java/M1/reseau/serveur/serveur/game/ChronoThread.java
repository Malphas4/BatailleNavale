package M1.reseau.serveur.serveur.game;

import java.io.IOException;

public class ChronoThread extends Thread {

    private SalonThread _salon;

    private int _playerTime1;
    private int _playerTime2;

    public ChronoThread() {

    }

    public ChronoThread(SalonThread _salon) {
        set_salon(_salon);
    }

    public SalonThread get_salon() {
        return _salon;
    }

    public void set_salon(SalonThread _salon) {
        this._salon = _salon;
    }

    public int get_playerTime1() {
        return _playerTime1;
    }

    public void set_playerTime1(int _playerTime1) {
        this._playerTime1 = _playerTime1;
    }

    public int get_playerTime2() {
        return _playerTime2;
    }

    public void set_playerTime2(int _playerTime2) {
        this._playerTime2 = _playerTime2;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {

            String _message = "chrono;"
                    + _salon.get_id()
                    + ";" + _salon.get_j1().get_pseudo()
                    + ";" + get_playerTime1()
                    + ";" + _salon.get_j2().get_pseudo()
                    + ";" + get_playerTime2();
            try {
                _salon.get_j1().message(_message);
                _salon.get_j2().message(_message);
            } catch (IOException e) {
                System.err.println("ChronoThread : Fail to send message.");
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("ChronoThread : Fail to sleep.");
            }

            set_playerTime1(get_playerTime1() - 1);
            set_playerTime2(get_playerTime2() - 1);
        }
    }
}
