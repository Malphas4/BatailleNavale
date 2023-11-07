package M1.reseau;


import java.io.IOException;

import M1.reseau.controleur.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Maj+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Maj+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+8.
            System.out.println("i = " + i);
        }
       // client  Testclient = new client();
       // serveur Testserveur = new serveur();

        try {
            // Testclient.unClient();
            //   Testserveur.unServeur();
            launch();

        } catch (Exception e) {
           // e.printStackTrace();

        }

    }



    @Override
    public void start(Stage stage){
        // Leaving this line for reference
        //FrontController frontController = FrontController.getInstance();



        try {
            // loader pour les FXML et liens avec les controleurs
            // possibilité de factorisation mais pas fait


            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent nodeMenu=menuLoader.load();
            ControleurMenu ControleurMenu = menuLoader.getController();


            FXMLLoader pseudoLoader = new FXMLLoader(getClass().getResource("pseudo.fxml"));
            Parent nodePseudo=pseudoLoader.load();
            ControleurPseudo ControleurPseudo = pseudoLoader.getController();

            FXMLLoader attenteLoader = new FXMLLoader(getClass().getResource("attente.fxml"));
            Parent nodeAttente=attenteLoader.load();
            ControleurAttente ControleurAttente = attenteLoader.getController();

            FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("lobby.fxml"));
            Parent nodeLobby=lobbyLoader.load();
            ControleurLobby ControleurLobby = menuLoader.getController();

            FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("grille.fxml"));
            Parent nodeGrille=grilleLoader.load();
            ControleurGrille ControleurGrille = grilleLoader.getController();

            //initialisation des modeles
            //            DataModel model = new DataModel();
            //            ControleurMenu.initModel(model);
            //            ControleurPseudo.initModel(model);
            //            menuController.initModel(model);


            //generation de la fenetre
            //            Scene scene = new Scene(root, 800, 600);
                        stage.setScene(new Scene(nodeMenu));
                        stage.show();




            // ici Borderpan et pan classiques mis dedans
            // BorderPane root = new BorderPane();
           /* code de l'exemple A SUPPR
           FXMLLoader listLoader = new FXMLLoader(getClass().getResource("list.fxml"));
            root.setCenter(listLoader.load());
            ListController listController = listLoader.getController();

            //init controleurs & model
            DataModel model = new DataModel();
            listController.initModel(model);
            editorController.initModel(model);
            menuController.initModel(model);

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            */


            ///


            //root.setCenter(listLoader.load());
            //ListController listController = listLoader.getController();



            // load can throw an IOException if their is an issue with the FXML file

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}