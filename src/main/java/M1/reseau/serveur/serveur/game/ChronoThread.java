package M1.reseau.serveur.serveur.game;

import java.io.IOException;
import java.time.LocalDateTime;

public class ChronoThread extends Thread {

    private JoueurHandler _j1;
    private JoueurHandler _j2;

    private LocalDateTime _dateTime = LocalDateTime.now();

    public ChronoThread(JoueurHandler _j1, JoueurHandler _j2) {
        set_j1(_j1);
        set_j2(_j2);
    }

    public JoueurHandler get_j1() {
        return _j1;
    }

    public void set_j1(JoueurHandler _j1) {
        this._j1 = _j1;
    }

    public JoueurHandler get_j2() {
        return _j2;
    }

    public void set_j2(JoueurHandler _j2) {
        this._j2 = _j2;
    }

    public LocalDateTime get_dateTime() {
        return _dateTime;
    }

    public void set_dateTime(LocalDateTime _dateTime) {
        this._dateTime = _dateTime;
    }

    /**
     * If this thread was constructed using a separate
     * {@code Runnable} run object, then that
     * {@code Runnable} object's {@code run} method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of {@code Thread} should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        synchronized (get_j1()) {
            synchronized (get_j2()) {
                while (!isInterrupted()) {
                    try {
                        get_j1().wait();
                        get_j2().wait();
                    } catch (InterruptedException e) {
                        System.err.println("ChronoThread : Fail to wait.");
                    }
                }
                String _message = "chrono;"
                        + get_dateTime()
                        + ";" + LocalDateTime.now();
                try {
                    get_j1().message(_message);
                    get_j2().message(_message);
                } catch (IOException e) {
                    System.err.println("ChronoThread : Fail to send message.");
                }

                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    System.err.println("ChronoThread : Fail to sleep.");
                }

            }
        }
    }
}
