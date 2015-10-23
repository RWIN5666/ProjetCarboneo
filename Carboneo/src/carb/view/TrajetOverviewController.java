/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.view;

import carb.Main;
import static carb.Main.bdd;
import static carb.Main.dateMaker;
import carb.model.ModeDeTransport;
import carb.model.Trajet;
import static carb.view.ParsingEverything.document;
import static carb.view.ParsingEverything.racine;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Erwin
 */
public class TrajetOverviewController implements Initializable {
    private Main main;
  
    // bizarre
    private Timeline locationUpdateTimeline;
    @FXML
    public WebView webView =  new WebView();
    @FXML
    public Label label; 
    @FXML
    public Label label3; 
    @FXML
    public Label truc;
    
    @FXML
    public Label consoAffiche;
    
   
    //essai image
    private Image img = new Image("file:Logo_resized.png");
    @FXML
    public ImageView imageLogo =new ImageView(img);
    
    @FXML
    public ToggleButton bouton_route = new ToggleButton("Route");
    @FXML
    public ToggleButton bouton_satellite =new ToggleButton(" Satellite ") ;
    @FXML
    public ToggleButton bouton_hybride= new ToggleButton("Hybride");
    @FXML
    public TextField searchDepart = new TextField("Brest");
    @FXML
    public TextField searchArrivee = new TextField("Guidel");
    @FXML
    public Button boutonValiderDepart = new Button("Valider"); 
    @FXML
    public Button boutonExit = new Button("Quitte le Programme Motherfucker");
    
    @FXML
    public BorderPane border = new BorderPane();
    
    // IMPORTANT
    public WebEngine webEngine ; 
    public SAXBuilder sxb = new SAXBuilder(); 
    public ParsingEverything superParseur = new ParsingEverything();
    
//*************************Accesseurs/Mutateurs***********************
//********************************************************************
    public void setMain(Main main) {this.main = main;}
        
    
    
    // Methodes
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        bdd.connection("Essai");
        
    }
    
    @FXML
    private void quitterProgramme(ActionEvent event) {
        
        System.exit(0);
        
    }
    
    @FXML
    public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle toggle1) {
                if (bouton_route.isSelected()) {
                    webEngine.executeScript("document.setMapTypeRoad()");
                } else if (bouton_satellite.isSelected()) {
                    webEngine.executeScript("document.setMapTypeSatellite()");
                } else if (bouton_hybride.isSelected()) {
                    webEngine.executeScript("document.setMapTypeHybrid()");
                } 
            }
    
    @FXML
    public void exitProg(ActionEvent event){
        
        System.exit(0);
    }
    
    @FXML
    public void changeToRoute(ActionEvent event) {
                if (bouton_route.isSelected()) {
                    webEngine.executeScript("document.setMapTypeRoad()");
                } 
            }
    
    @FXML
    public void changeToSatellite(ActionEvent event) {
                if (bouton_satellite.isSelected()) {
                   webEngine.executeScript("document.setMapTypeSatellite()");
                } 
            }
    
    @FXML
    public void changeToHybrid(ActionEvent event) {
                if (bouton_route.isSelected()) {
                    webEngine.executeScript("document.setMapTypeHybrid()");
                } 
            }
    
    
    
    @FXML
    public void validerDepart(ActionEvent event) throws MalformedURLException, JDOMException, IOException{
       
        System.out.println("You clicked me!");
        
        // on met le nom des points de départ et d'arrivée dans les labels
        String origine = searchDepart.getText();
        String arrivee = searchArrivee.getText();
        label3.setText("Votre lieu de départ est "+ origine +" et l'arrivée est "+arrivee );
        // Calcul de distance avec API Google MAPS
        String urlParcours = "http://maps.googleapis.com/maps/api/distancematrix/xml?origins="+ origine + "&destinations=" + arrivee +"&mode=driving&language=fr-FR&sensor=false";
        URL Url = new URL(urlParcours); 
        document = sxb.build(Url.openStream());
        racine = document.getRootElement();   
        double resultat = superParseur.afficheALL();
        label.setText("Distance trouvee :  " + resultat + " KM ");
        // Script de marqueur qui bueuge encore
         //webEngine.executeScript("document.goToLocation(\""+searchDepart.getText()+"\")");
        
        
        // On va créer le trajet et le mettre dans la BDD
        bdd.connection("Essai");
        // test de choix de mode il reste à le choisir sur l'IHM
        List<ModeDeTransport> listeMod = bdd.getModeDeTransport("Voiture"); 
        ModeDeTransport modechoix = listeMod.get(0);
        // on créé le trajet avec les points de départ d'arrivée et le mode choisi
        Trajet trajetTemp = new Trajet(origine,arrivee,modechoix);
        trajetTemp.setDistance(resultat);
        trajetTemp.computeC02();
        trajetTemp.setDate(dateMaker.date());
        bdd.ajouterTrajet(trajetTemp);
        bdd.deconnection();     
        consoAffiche.setText(" Votre consommation pour ce trajet en grammes de Co2 expulsé : "+ trajetTemp.getEmissionTotale());
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // PARTIE CARTE 
        webEngine = webView.getEngine();
      //  webEngine.load(getClass().getResource("googlemap.html").toString());
        final URL urlGoogleMaps = getClass().getResource("GoogleMapsV3.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        webEngine.setJavaScriptEnabled(true); 
        border.setCenter(webView);
        border.getStyleClass().add("map");
       // groupe de boutons
        final ToggleGroup mapTypeGroup = new ToggleGroup();
        bouton_route.setSelected(true);       
        bouton_route.setToggleGroup(mapTypeGroup);
        bouton_satellite.setToggleGroup(mapTypeGroup);
        bouton_hybride.setToggleGroup(mapTypeGroup);
        if (bouton_route.isSelected()) {
        //webEngine.executeScript("document.setMapTypeRoad()");
        }
        // Pour que ça marche
        ToggleGroup mapSourceGroup = new ToggleGroup();
        final ToggleButton google = new ToggleButton("Google");
        google.setSelected(true);
        google.setToggleGroup(mapSourceGroup);
        searchDepart.setPrefColumnCount(12);
        searchDepart.setPromptText("Entrez votre lieu de départ");
        searchDepart.setText("Brest");
        
        
        // Initilisation du logo
        
        
  
   
    }  

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
  
        
    }    
    

