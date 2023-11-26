package M1.reseau.client2;

import M1.reseau.client.console.ClientTCP;
import M1.reseau.model.game.distance.PartieClient;
import M1.reseau.model.world.element.classic.CaseBateau;

import java.util.Scanner;

public class GameClient {

    private PartieClient _partie;
    private ClientTCP _clientTCP;

    // Configuration côté serveur


    public GameClient(ClientTCP _clientTCP) {
        set_partie(new PartieClient(8, 8));
        set_clientTCP(_clientTCP);
    }

    public PartieClient get_partie() {
        return _partie;
    }

    public void set_partie(PartieClient _partie) {
        this._partie = _partie;
    }

    public ClientTCP get_clientTCP() {
        return _clientTCP;
    }

    public void set_clientTCP(ClientTCP _clientTCP) {
        this._clientTCP = _clientTCP;
    }

    public void requestPlayerSetup() {
        System.out.println("=============== Initialisation du placement des bateaux du joueurs ===============");
        int _x;
        int _y;
        do {
            System.out.println("Initialisation du nouveau bateau : ");
            System.err.println("Pour arrêter, mettre tout à 0.");
            Scanner _scan = new Scanner(System.in);

            // Input
            System.out.println("\tPosition x du bateau : ");
            _x = _scan.nextInt();
            System.out.println("\tPosition y du bateau : ");
            _y = _scan.nextInt();

            // Création de la case et remplacement dans la grille
            CaseBateau _bCase = new CaseBateau(_x, _y);
            _clientTCP.message("");

        } while (_x > 0 && _y > 0);
    }

    public void sendMessageTCP(String _message) {

    }
}
