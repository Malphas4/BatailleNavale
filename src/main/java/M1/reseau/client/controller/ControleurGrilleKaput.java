package M1.reseau.client.controller;

import M1.reseau.model.exception.IPartieException;
import M1.reseau.model.game.distance.PartieClient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class ControleurGrilleKaput {



    @FXML
    private Button _btnAbandon;
    @FXML
    private Button _btnChatPartie;
    @FXML
    private Button _btnQuitter;
    @FXML
    private TextArea _chatPartie;
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

    @FXML
    private Button _btnContreTorpille;

    @FXML
    private Button _btnCroiseur;

    @FXML
    private Button _btnPorteAv;
    @FXML
    private Scene _sceneGrille;


    @FXML
    private Button _btnRotation;

    @FXML
    private Button _btnTorpilleur;

    @FXML
    private Button _btnValidationPause;


    //variables du controleur a enlever car remplacer par PartieClient
    Boolean _placementbateaux;
    Boolean _porteAvions, _croiseur, _contreTorpilleurs, _torpilleur = false;
    int _typeBateau = 0;
    int _nbBateau = 0;
    Boolean _monTour = false;
    PartieClient _maPartie;



    int _longueur, _largeur = 6;//AF apres recuperation coté serveur
    //= 0;
    //definition des couleurs
    Color _couleurRocher=Color.DARKGREY;
    Color _couleurPasDeCible=Color.DARKSLATEBLUE;
    Color _couleurBateau=Color.FORESTGREEN;
    Color _couleurEpave=Color.DARKRED;


    /*********************************
     * Déclaration des constructeurs
     *********************************/

    public ControleurGrilleKaput() {

    }

    @FXML
    public void initialize() throws IPartieException {
        _placementbateaux = true;

        //initialisation du modele
        //_maPartie = new PartieClient(_longueur, _largeur);

        //affichage de da Grille initiale


        HBox Grilles = new HBox(50);
        System.out.println("initialisation des grilles\n");

        boolean enemy = false;
        int nbBateaux = 4;
        int compteurBateaux = 0;
        int tailleViseur = 1;


        VBox _maGrille = AffichageGrille(6, 6, true);
        VBox _GrilleDeSauron = AffichageGrille(6, 6, false);

        Grilles.getChildren().add(_maGrille);
        Grilles.getChildren().add(_GrilleDeSauron);

        _panGrilles.getChildren().add(Grilles);

    }

    @FXML
    void Abandonner(ActionEvent event) {
        //on envoie le message d'abandon
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo()));
        //on retourne au Lobby
        FXMLLoader grilleLoader = new FXMLLoader(getClass().getResource("/grilleV2.fxml"));
        try {
            Scene sceneGrille = grilleLoader.load();
            Stage stageActuel = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageActuel.setScene(sceneGrille);
            stageActuel.show();
        } catch (IOException e) {
            System.out.print("erreur lors du retour au lobby, fermeture de l'application");
            Quitter(event);
            throw new RuntimeException(e);
        }

    }

    @FXML
    void EnvoyerMessage(ActionEvent event) {
        String _msgTransit = _textChatpartie.getText();
        //on envoie au serveur le message avec le code pour le chat local
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo().concat(";").concat(_msgTransit)));

    }

    @FXML
    void Quitter(ActionEvent event) {

        //on envoie au serveur le ragequit
        // SingletonTCP.getInstance().message("code:".concat(InformationsUtilisateurs.getInstance.get_pseudo()));
        System.out.print("au revoir\n");
        Platform.exit();
        //fermeture serveur UDP et TCP AF
        //SingletonUDP.fermetureSocket();
        //SingletonTCP.fermetureSocket();
        System.exit(0);

    }


    @FXML
    void RotationBateau(ActionEvent event) {

    }

    @FXML
    void ValiderPoser(ActionEvent event) {

    }

    @FXML
    void selectionContreTorpille(ActionEvent event) {
        _typeBateau = 3;
    }

    @FXML
    void selectionCroiseur(ActionEvent event) {
        _typeBateau = 4;
    }

    @FXML
    void selectionPorteAv(ActionEvent event) {
        _typeBateau = 5;
    }

    @FXML
    void selectionTorpilleur(ActionEvent event) {
        _typeBateau = 2;

    }



    void modifCase(int x, int y, Color c, boolean proprio) {
        String _id;
        if (proprio) {
            _id = "#".concat(String.valueOf(x).concat(String.valueOf(y)).concat("g1"));
        } else {
            _id = "#".concat(String.valueOf(x).concat(String.valueOf(y)).concat("g2"));
        }
        _sceneGrille.lookup(_id);
    }


    VBox AffichageGrille(int x, int y, boolean proprietaire) {
        //definition des couleurs et autres variables
        Color _couleurEau = null;
        Color _couleurBord = null;
        String _id;
   /* try {
        _maPartie.ajouterJoueur(InformationsUtilisateur.getInstance().get_pseudo());
    } catch (IPartieException e) {
        throw new RuntimeException(e);
    }*/
        // selon le propriétaire de la grille
        if (proprietaire) {
            //attribution de l'id afin de retrouver la case
            _id = String.valueOf(x).concat(String.valueOf(y)).concat("g1");
            _couleurEau = Color.DARKTURQUOISE;
            _couleurBord = Color.BLACK;
            //=String.valueOf(x).concat(String.valueOf(y)).concat("g2")
        } else {
            //attribution de l'id afin de retrouver la case
            _id = String.valueOf(x).concat(String.valueOf(y)).concat("g2");

            _couleurEau = Color.DARKBLUE;
            _couleurBord = Color.LIGHTGOLDENRODYELLOW;
        }
        VBox _maGrille = new VBox();
        VBox _GrilleDeSauron = new VBox();
        for (int yb = 0; y < 6; y++) {
            HBox colonne = new HBox();
            for (int xb = 0; x < 6; x++) {
           /* if (_maPartie.get){

            }*/
                Rectangle _uneCase = new Rectangle(30, 30, _couleurEau);
                _uneCase.setStroke(_couleurBord);
                _uneCase.setId(_id);
                _uneCase.setOnMouseClicked(event -> {
                    int _x,_y;
                    _x = (int) _uneCase.getLayoutX() / 31;
                    _y = (int) _uneCase.getParent().getLayoutY() / 31;
                    if (proprietaire) {
                        System.out.print("case pointée ennemie x:" + _x + " y:" + _y + "\n");
                    } else {
                        System.out.print("case pointée x:" + _x + " y:" + _y + "\n");
                    }
                    if (_placementbateaux) {
                        posebateaux( _x, _y,6,6);
                    } else if (_monTour) {
                        System.out.print("Feu à volonté\n");
                        //on envoie la case du tir
                        // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                        //on attend le message du serveur

                    } else System.out.print("bateaux deja placées ou pas mon tour\n");


                });

                colonne.getChildren().add(_uneCase);
            }
            _maGrille.getChildren().add(colonne);

        }
        return _maGrille;
    }

    void posebateaux(int _x,int _y,int x,int y){
        System.out.print("placement d'un bateau \n");
        switch (_typeBateau) {

            case 0:
                System.out.println("pas de bateau choisi");
                break;

            case 2:
                System.out.println("torpi");
                if ((_x + _typeBateau) < _largeur && (_y + _typeBateau) < _longueur) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    _sceneGrille.lookup("#_btnTorpilleur").setVisible(false);
                    _sceneGrille.lookup("#_btnTorpilleur").setDisable(true);

                    //envoi des cases au serveur
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));

                }
                break;

            case 3:
                System.out.println("contre torpi");
                if ((_x + _typeBateau) < _largeur && (_y + _typeBateau) < _longueur) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    modifCase(_x + 2, _y, _couleurBateau, true);
                    _sceneGrille.lookup("#_btnContreTorpille").setVisible(false);
                    _sceneGrille.lookup("#_btnContreTorpille").setDisable(true);
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+2)).concat(";y").concat(String.valueOf(_y)));


                }
                break;
            case 4:
                System.out.println("croiseur");
                if ((_x + _typeBateau) < _largeur && (_y + _typeBateau) < _longueur) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    modifCase(_x + 2, _y, _couleurBateau, true);
                    modifCase(_x + 3, _y, _couleurBateau, true);
                    _sceneGrille.lookup("#_btnCroiseur").setVisible(false);
                    _sceneGrille.lookup("#_btnCroiseur").setDisable(true);
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+2)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+3)).concat(";y").concat(String.valueOf(_y)));

                }
                break;
            case 5:
                System.out.println("porte-avions");
                if ((_x + _typeBateau) < _largeur && (_y + _typeBateau) < _longueur) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    modifCase(_x + 2, _y, _couleurBateau, true);
                    modifCase(_x + 3, _y, _couleurBateau, true);
                    modifCase(_x + 4, _y, _couleurBateau, true);
                    _sceneGrille.lookup("#_btnPorteAv").setVisible(false);
                    _sceneGrille.lookup("#_btnPorteAv").setDisable(true);
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+2)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+3)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+4)).concat(";y").concat(String.valueOf(_y)));

                }
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }
        //on envoie le debut du bateau a placer
        // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));

    }



/*
 VBox AffichageGrille(Partie _maPartie, boolean proprietaire) {
    //definition des couleurs et autres variables
    Color _couleurRocher = Color.DARKGREY;
    Color _couleurPasDeCible = Color.DARKSLATEBLUE;
    Color _couleurBateau = Color.FORESTGREEN;
    Color _couleurEpave = Color.DARKRED;
    Color _couleurEau = null;
    Color _couleurBord = null;
    String _id;
    int x = _maPartie.get_fabriqueGrille().get_largeur();
    int y = _maPartie.get_fabriqueGrille().get_longueur();
    try {
        _maPartie.ajouterJoueur(InformationsUtilisateur.getInstance().get_pseudo());
    } catch (IPartieException e) {
        throw new RuntimeException(e);
    }
    // selon le propriétaire de la grille
    if (proprietaire) {
        //attribution de l'id afin de retrouver la case
        _id = String.valueOf(x).concat(String.valueOf(y)).concat("g1");
        _couleurEau = Color.DARKTURQUOISE;
        _couleurBord = Color.BLACK;
        //=String.valueOf(x).concat(String.valueOf(y)).concat("g2")
    } else {
        //attribution de l'id afin de retrouver la case
        _id = String.valueOf(x).concat(String.valueOf(y)).concat("g2");

        _couleurEau = Color.DARKBLUE;
        _couleurBord = Color.LIGHTGOLDENRODYELLOW;
    }
    VBox _maGrille = new VBox();
    VBox _GrilleDeSauron = new VBox();
    for (int yb = 0; y < 6; y++) {
        HBox colonne = new HBox();
        for (int xb = 0; x < 6; x++) {
           // if (_maPartie.get){


Rectangle _uneCase = new Rectangle(30, 30, _couleurEau);
            _uneCase.setStroke(_couleurBord);
            _uneCase.setId(_id);
            _uneCase.setOnMouseClicked(event -> {
    int _x, _y;
    _x = (int) _uneCase.getLayoutX() / 31;
    _y = (int) _uneCase.getParent().getLayoutY() / 31;
    if (proprietaire) {
        System.out.print("case pointée ennemie x:" + _x + " y:" + _y + "\n");
    } else {
        System.out.print("case pointée x:" + _x + " y:" + _y + "\n");
    }
    if (_placementbateaux) {
        //void posebateaux(_x,_y,x,y); probleme modele
        System.out.print("placement d'un bateau \n");
        switch (_typeBateau) {

            case 0:
                System.out.println("pas de bateau choisi");
                break;

            case 2:
                System.out.println("torpi");
                if ((_x + _typeBateau) < _maPartie.get_fabriqueGrille().get_largeur() && (_y + _typeBateau) < _maPartie.get_fabriqueGrille().get_longueur()) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    _sceneGrille.lookup("#_btnTorpilleur").setVisible(false);
                    _sceneGrille.lookup("#_btnTorpilleur").setDisable(true);
                    _nbBateau++;
                    _typeBateau=0;
                    //envoi des cases au serveur
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));

                }
                break;

            case 3:
                System.out.println("contre torpi");
                if ((_x + _typeBateau) < _maPartie.get_fabriqueGrille().get_largeur() && (_y + _typeBateau) < _maPartie.get_fabriqueGrille().get_longueur()) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    modifCase(_x + 2, _y, _couleurBateau, true);
                    _sceneGrille.lookup("#_btnContreTorpille").setVisible(false);
                    _sceneGrille.lookup("#_btnContreTorpille").setDisable(true);
                    _nbBateau++;
                    _typeBateau=0;
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+2)).concat(";y").concat(String.valueOf(_y)));


                }
                break;
            case 4:
                System.out.println("croiseur");
                if ((_x + _typeBateau) < _maPartie.get_fabriqueGrille().get_largeur() && (_y + _typeBateau) < _maPartie.get_fabriqueGrille().get_longueur()) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    modifCase(_x + 2, _y, _couleurBateau, true);
                    modifCase(_x + 3, _y, _couleurBateau, true);
                    _nbBateau++;
                    _typeBateau=0;
                    _sceneGrille.lookup("#_btnCroiseur").setVisible(false);
                    _sceneGrille.lookup("#_btnCroiseur").setDisable(true);
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+2)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+3)).concat(";y").concat(String.valueOf(_y)));

                }
                break;
            case 5:
                System.out.println("porte-avions");
                if ((_x + _typeBateau) < _maPartie.get_fabriqueGrille().get_largeur() && (_y + _typeBateau) < _maPartie.get_fabriqueGrille().get_longueur()) {
                    modifCase(_x, _y, _couleurBateau, true);
                    modifCase(_x + 1, _y, _couleurBateau, true);
                    modifCase(_x + 2, _y, _couleurBateau, true);
                    modifCase(_x + 3, _y, _couleurBateau, true);
                    modifCase(_x + 4, _y, _couleurBateau, true);
                    _nbBateau++;
                    _typeBateau=0;
                    _sceneGrille.lookup("#_btnPorteAv").setVisible(false);
                    _sceneGrille.lookup("#_btnPorteAv").setDisable(true);
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+1)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+2)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+3)).concat(";y").concat(String.valueOf(_y)));
                    // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x+4)).concat(";y").concat(String.valueOf(_y)));

                }
                break;
            default:
                System.out.println("Choix incorrect");
                break;
        }
        //on envoie le debut du bateau a placer
        // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));

    } else if (_monTour) {
        System.out.print("Feu à volonté\n");
        //on envoie la case du tir
        // SingletonTCP.getInstance().message("code".concat(":x").concat(String.valueOf(_x)).concat(";y").concat(String.valueOf(_y)));
        //on attend le message du serveur

    } else System.out.print("bateaux deja placées ou pas mon tour\n");


});

            colonne.getChildren().add(_uneCase);
}
        _maGrille.getChildren().add(colonne);

                }
                return _maGrille;
                }
*/
}





