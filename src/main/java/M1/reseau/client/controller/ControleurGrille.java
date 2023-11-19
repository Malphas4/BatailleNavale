package M1.reseau.client.controller;


import M1.reseau.model.world.grid.Grille;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.scene.control.PasswordField;

import javax.swing.text.Position;

public class ControleurGrille {

    @FXML
    private Button _btnAbandon;
    @FXML
    private Button _btnChatPartie;
    @FXML
    private Button _btnQuitter;
    @FXML
    private TextFlow _chatPartie;
    @FXML
    private TextArea _infoParties;
    @FXML
    private AnchorPane _panPartie;
    @FXML
    private TextField _textChatpartie;
    @FXML
    private PasswordField _mdp;
    @FXML
    private Pane _panGrilles;


     //variables du constructeur
     Boolean _placementbateaux;
     Boolean _PorteAvions,_Croiseur,_ContreTorpilleurs,_Torpilleur=false;
    int _hauteur,largeur=6;//AF apres recuperation coté serveur
    //= 0;



    /*********************************
     * Déclaration des constructeurs
     *********************************/

    public ControleurGrille() {

    }
    @FXML
    public void initialize() {
        _placementbateaux=false;
        Node _joueur ,_adversaire= null;
        HBox Grilles =new HBox(50);
        System.out.println("initialisation des grilles");

        boolean enemy = false;
        int ships = 5;
        VBox _maGrille = new VBox();
        VBox _GrilleDeSauron = new VBox();
        //boolean _proprietaire = proprietaire;
        for (int y = 0; y < 6; y++) {
            HBox colonne = new HBox();
            for (int x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(30, 30, Color.DARKTURQUOISE);
                _uneCase.setStroke(Color.BLACK);
                _uneCase.setOnMouseClicked(event ->  {
                    int _x,_y;
                    _x=(int)_uneCase.getLayoutX()/31;
                    _y=(int)_uneCase.getParent().getLayoutY()/31;
                    System.out.print("case pointée x:"+_x+" y:"+_y+"\n");
                    if(!_placementbateaux){

                    }


                    });

                colonne.getChildren().add(_uneCase);
            }
            _maGrille.getChildren().add(colonne);

        }
        Grilles.getChildren().add(_maGrille);

        for (int y = 0; y < 6; y++) {
            HBox colonnade = new HBox();
            for (int x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(30, 30, Color.DARKBLUE);
                _uneCase.setStroke(Color.LIGHTGOLDENRODYELLOW);
                _uneCase.setOnMouseClicked(event ->  {
                    int _x,_y;
                    _x=(int)_uneCase.getLayoutX()/31;
                    _y=(int)_uneCase.getParent().getLayoutY()/31;
                    System.out.print("case pointée ennemie x:"+_x+" y:"+_y+"\n");


                });
                // _uneCase.setOnMouseClicked(handler);
               /* _uneCase.setOnMouseClicked(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        Node source = (Node) event.getSource();
                        //= source.();
                        System.out.println("test case ");
                        Position pos = (Position) source.getUserData();

                    }
                });*/

                colonnade.getChildren().add(_uneCase);
            }
            _GrilleDeSauron.getChildren().add(colonnade);

        }
        Grilles.getChildren().add(_GrilleDeSauron);

        _panGrilles.getChildren().add(Grilles);

    }


    @FXML
    void Tirer(MouseEvent event) {

    }

    @FXML
    void Abandonner(ActionEvent event) {

    }

    @FXML
    void EnvoyerMessage(ActionEvent event) {

    }

    @FXML
    void Quitter(ActionEvent event) {

    }


    /*public UneGrille(boolean proprietaire, EventHandler<? super MouseEvent> handler, int x, int y) {
        VBox rows = new VBox();
        boolean _proprietaire = proprietaire;
        for ( y = 0; y < 6; y++) {
            HBox row = new HBox();
            for ( x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(x, y, Color.DARKTURQUOISE);
                _uneCase.setOnMouseClicked(handler);
                row.getChildren().add(_uneCase);
            }

            rows.getChildren().add(row);
        }

    //getChildren().add(rows);
    }*/
    /* public GrilleClassique(boolean proprietaire, EventHandler<? super MouseEvent> handler) {
        VBox rows = new VBox();
        boolean _proprietaire = proprietaire;
        for (int y = 0; y < 6; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(x, y, Color.DARKTURQUOISE);
                _uneCase.setOnMouseClicked(handler);
                row.getChildren().add(_uneCase);
            }

            rows.getChildren().add(row);
        }*/

       // getChildren().add(rows);
    //}


}
/* ancient ititilize
     public void initialize() {
        Node _joueur ,_adversaire= null;
        HBox Grilles =null;
        System.out.println("initialisation des grilles");

        boolean enemy = false;
        int ships = 5;
        VBox rows = new VBox();
        //boolean _proprietaire = proprietaire;
        for (int y = 0; y < 6; y++) {
            HBox row = new HBox();
            for (int x = 0; x < 6; x++) {
                //Cell c = new Cell(x, y, this);
                Rectangle _uneCase = new Rectangle(x, y, Color.DARKTURQUOISE);
               // _uneCase.setOnMouseClicked(handler);
               /* _uneCase.setOnMouseClicked(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        Node source = (Node) event.getSource();
                        //= source.();
                        System.out.println("test case ");
                        Position pos = (Position) source.getUserData();

                    }
                });

                row.getChildren().add(_uneCase);
                        }
                        rows.getChildren().add(row);
                        _joueur=rows;

                        System.out.println("initialisation des du vbox contenant les grilles");
                        Grilles = new HBox(50, _joueur,_adversaire);
                        //VBox Grilles = new VBox(50, playerBoard,enemyBoard);
                        Grilles.setAlignment(Pos.CENTER);


                        }
                        _panGrilles.getChildren().add(Grilles);


        for (int i = 0; i < 6; i++) {
            Button b=new Button("Salon ".concat(String.valueOf(i)));
            b.setId(String.valueOf(i));
            System.out.println(i);
            b.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    Node source = (Node) event.getSource();
                    _salonChoisi= source.getId();
                    System.out.println("test Salon "+_salonChoisi);
                }
            });
            _listeSalon.getChildren().add(b);
        }


                        }
 */
