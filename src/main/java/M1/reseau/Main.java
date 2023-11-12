package M1.reseau;


import java.io.IOException;

import M1.reseau.client.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    public void start(Stage stage) throws Exception{
        // Leaving this line for reference
        //FrontController frontController = FrontController.getInstance();



       //try {
            // loader pour les FXML et liens avec les controleurs
            // possibilité de factorisation mais pas fait


            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/menuv2.fxml"));
            Scene sceneMenu = (Scene) menuLoader.load();

             //Parent nodeMenu=(Parent)menuLoader.load();
            ControleurMenu ControleurMenu = menuLoader.getController();

            FXMLLoader pseudoLoader = new FXMLLoader(getClass().getResource("/pseudo.fxml"));
            Scene scenePseudo=(Scene) pseudoLoader.load();
            ControleurPseudo ControleurPseudo = pseudoLoader.getController();

            FXMLLoader attenteLoader = new FXMLLoader(getClass().getResource("/attenteV2.fxml"));
            Scene sceneAttente=(Scene) attenteLoader.load();
            ControleurAttente ControleurAttente = attenteLoader.getController();

   /*
            FXMLLoader lobbyLoader = new FXMLLoader(getClass().getResource("/lobby.fxml"));
            Scene sceneLobby=(Scene) lobbyLoader.load();
            ControleurLobby ControleurLobby = menuLoader.getController();
   */
            FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
            Scene sceneGrille=(Scene) grilleLoader.load();
            ControleurGrille ControleurGrille = grilleLoader.getController();

            //initialisation des modeles
            //            DataModel model = new DataModel();
            //            ControleurMenu.initModel(model);
            //            ControleurPseudo.initModel(model);
            //            menuController.initModel(model);

            /*generation de la fenetre
            Pane paneTest = new Pane();
            Scene sceneTest = new Scene(paneTest, 800, 600);*/

            stage.setScene(sceneMenu);

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

       /* } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


}