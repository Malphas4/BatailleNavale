package M1.reseau.serveur.serveur.game;

import M1.reseau.model.game.local.PartieServeur;

public class GameThread {

    private PartieServeur _partie;
    private Toucher _toucher;

    private boolean isInterrupted = false;

    public GameThread() {
        _partie = new PartieServeur(8, 8);
        _toucher = new Toucher();
    }

    public class GameThread1 extends Thread{
        @Override
        public void run() {
            synchronized (_toucher) {
                while (!isInterrupted) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // TODO
                _toucher.notifyAll();
            }
            // TODO
        }
    }

    public class GameThread2 extends Thread{
        @Override
        public void run() {
            synchronized (_toucher) {
                while (!isInterrupted) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // TODO
                _toucher.notifyAll();
            }
            // TODO
        }
    }

    public class GameThreadArbiter extends Thread{
        @Override
        public void run() {
            synchronized (_toucher) {
                while (!isInterrupted) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // TODO
                _toucher.notifyAll();
            }

            // TODO
        }
    }

}
