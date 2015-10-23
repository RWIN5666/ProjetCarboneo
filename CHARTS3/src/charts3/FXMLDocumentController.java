/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;

import static charts3.CHARTS3.bdd;
import static charts3.CHARTS3.dateMaker;
import static java.lang.Double.parseDouble;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author erwinandre
 */
public class FXMLDocumentController implements Initializable {
    
    
        private ObservableList<String> monthNames = FXCollections.observableArrayList();  
    private dataChart listeData = new dataChart();   
    private ArrayList<recordData> listeEssai = new ArrayList();      
    
    
    
    
    public  XYChart.Series series = new XYChart.Series<>();
    
    
     @FXML
    public LineChart<String, Integer> lineChart;
    
    @FXML
    public Label caption ;
     
       @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private Label label;
    
    
    @FXML
    private PieChart chart;
    
    @FXML
    private TextField model;
    
    @FXML
    private TextField conso;
    
    @FXML 
    private Button valider;
    
    
    @FXML
    private NumberAxis yAxis = new NumberAxis();
    
  
    
    
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        lineChart.getData().add(series);
    }
    
    
     @FXML
    private void handleButtonAction2(ActionEvent event) {

      //  String newEntryMod = model.getText();
      //  String newEntryConso = conso.getText();       
      //  double addConso = parseDouble(newEntryConso);      
        // on fait le set
       chart.setData(listeData.pieChartModeleConso);
        
        
        
    }
    
    
     public void addDataParMode(){            
            // on va ajouter les différentes consos relatives aux différents types de véhicules pour les PIE CHARTS
            // On commence par les voitures             
            for(int i =0; i<listeEssai.size() ;i++){
                listeEssai.remove(i);              
            }                     
            // on prend les voitures
            String mode = "Voiture";
            double emission = 0;
            List<Trajet> listeTrajet = bdd.getAllTrajet("Voiture");      
            
            // on ajoute les données
            for(int j =0; j<listeTrajet.size() ;j++){
                emission +=listeTrajet.get(j).getEmissionTotale();
                System.out.println("Emission nom1 : "+ listeTrajet.get(j).getDepart() + " emission " +emission);               
            }
            listeEssai.add(new recordData("Voiture",emission));            
            // On s'occupe ensuite des bus
            String mode2 = "Bus";
            double emission2 = 1;
             List<Trajet> listeTrajet2 = bdd.getAllTrajet("Bus");
            for(int k =0; k<listeTrajet2.size() ;k++){
                emission2 += listeTrajet2.get(k).getEmissionTotale();                   
                System.out.println("Emission nom2 : "+ listeTrajet2.get(k).getDepart() + " emission " +emission2);
            }
          listeEssai.add(new recordData("Bus",emission2));     
        }
    
     
     
     
     
     
     public void addDataParDate(){
         
         // Test Moi de mai
         List<Trajet> listeTrajet = bdd.getTrajetFromTo("Voiture","2014-05-01","2014-05-31");
        double emissionMai = 0;
          for(int k =0; k<listeTrajet.size() ;k++){
                emissionMai += listeTrajet.get(k).getEmissionTotale();                   
                System.out.println("Emission mai: "+ listeTrajet.get(k).getDepart() + " emission " +emissionMai);
            }
         
          series.getData().add(new XYChart.Data<String,Double>("May", emissionMai));
         
         
         
     }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        caption  = new Label("");
        // on se connecte à la BDD
        bdd.connection("Essai");
        bdd.deconnection();
        bdd.connection("Essai");
        
      // PARTIE AJOUT DE TRAJET POUR PIE CHART
          
        // test de choix de mode il reste à le choisir sur l'IHM
        List<ModeDeTransport> listeMod = bdd.getModeDeTransport("Voiture"); 
        ModeDeTransport modechoix = listeMod.get(0);
         System.out.println("Nom véhicule 1: " +modechoix.getNom() );
        // on créé le trajet avec les points de départ d'arrivée et le mode choisi
        Trajet trajetTemp1 = new Trajet("Brest","Marseille",modechoix);
        trajetTemp1.setDistance(60);
        trajetTemp1.computeC02();
        trajetTemp1.setDate(dateMaker.date());
        bdd.ajouterTrajet(trajetTemp1);
       System.out.println("Emission totale : "+ trajetTemp1.getEmissionTotale()+ " nom : " + modechoix.getNom() );
         
       
       
       // deuxieme trajet
        ModeDeTransport modechoix2 = listeMod.get(1);
         System.out.println("Nom véhicule 2  : " +modechoix2.getNom() );
        Trajet trajetTemp2 = new Trajet("Guidel","Paris",modechoix2);
        trajetTemp2.setDistance(500);
        trajetTemp2.computeC02();     
        System.out.println("Emission totale : "+ trajetTemp2.getEmissionTotale()+ " nom : " + modechoix2.getNom() );
        trajetTemp2.setDate(dateMaker.date());       
        bdd.ajouterTrajet(trajetTemp2);
           // on ajoute tout les trajets à partir de la BDD pour les préparer au pie chart
        
        
        
        
        
        
        
        List<ModeDeTransport> listeMod2 = bdd.getModeDeTransport("Bus"); 
        ModeDeTransport modechoix3 = listeMod2.get(0);
         System.out.println("Nom véhicule 3: " +modechoix3.getNom() );
        // on créé le trajet avec les points de départ d'arrivée et le mode choisi
        Trajet trajetTemp3 = new Trajet("Lorient","Marseille",modechoix3);
        trajetTemp3.setDistance(300);
        trajetTemp3.computeC02();
        trajetTemp3.setDate(dateMaker.date());
        bdd.ajouterTrajet(trajetTemp3);
       System.out.println("Emission totale : "+ trajetTemp3.getEmissionTotale()+ " nom : " + modechoix3.getNom() );

        addDataParMode(); 
         
      //  listeEssai.add(new recordData("Voiture",101));
      //  listeEssai.add(new recordData("Bus",240));        */
        listeData.addData(listeEssai);       
        chart.setTitle("Consommation en fonction du modele");
        chart.setData(listeData.pieChartModeleConso);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // LINE CHART
        
        
        
      lineChart.getXAxis().setAutoRanging(true);
        lineChart.getYAxis().setAutoRanging(true);
//        progressBar.setId("progressBar");
        series.setName("Consommation Voiture");
       lineChart.setTitle("Consommation Totale de Co2 en 2014");
        lineChart.getXAxis().setLabel("Mois");
        series.getData().add(new XYChart.Data<String,Integer>("Jan", 23000));
        series.getData().add(new XYChart.Data<String,Integer>("Feb", 14000));
        series.getData().add(new XYChart.Data<String,Integer>("Mar", 15000));
        series.getData().add(new XYChart.Data<String,Integer>("Apr", 24000));
        addDataParDate();
        series.getData().add(new XYChart.Data<String,Integer>("Jun", 36000));
        series.getData().add(new XYChart.Data<String,Integer>("Jul", 22000));
        series.getData().add(new XYChart.Data<String,Integer>("Aug", 45000));
        series.getData().add(new XYChart.Data<String,Integer>("Sep", 43000));
        series.getData().add(new XYChart.Data<String,Integer>("Oct", 17000));
        series.getData().add(new XYChart.Data<String,Integer>("Nov", 29000));
        series.getData().add(new XYChart.Data<String,Integer>("Dec", 25000)); 
    
        
        
        
    // TODO
        
       bdd.deconnection();
       

    }    
    
}
