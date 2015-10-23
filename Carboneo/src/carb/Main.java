



package carb;


import carb.model.Dates;
import carb.model.Profil;
import carb.model.SQLiteJDBC;
import carb.view.ProfilEditDialogController;
import carb.view.ProfilOverviewController;
import carb.view.SelectorController;
import carb.view.TrajetOverviewController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
  
//*****************************Variables******************************
//********************************************************************  
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList <Profil> profilData = FXCollections.observableArrayList();
    
    public static SQLiteJDBC bdd = new SQLiteJDBC();
    public static Dates dateMaker = new Dates();
    
//***************************Constructeurs****************************     
//********************************************************************
    public Main(){
        profilData.add(new Profil("Hans", "Muster"));
        profilData.add(new Profil("Ruth", "Mueller"));
        profilData.add(new Profil("Heinz", "Kurz"));
        profilData.add(new Profil("Cornelia", "Meier"));
        profilData.add(new Profil("Werner", "Meyer"));
        profilData.add(new Profil("Lydia", "Kunz"));
        profilData.add(new Profil("Anna", "Best"));
        profilData.add(new Profil("Stefan", "Meier"));
        profilData.add(new Profil("Martin", "Mueller"));
        
        
        
        
    }
//*************************Accesseurs/Mutateurs***********************     
//********************************************************************
    public Stage getPrimaryStage() {return primaryStage;}
    
    public ObservableList<Profil> getProfilData() {return profilData;}
//************************Méthodes d'acquisition**********************     
//********************************************************************

//************************Méthodes d'execution************************ 
//********************************************************************
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        bdd.connection("Essai");
        bdd.deconnection();
        this.primaryStage.setMaximized(true);
        showRoot();
        showSelector();
        showGeneralOverview();
       
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
//************************Méthodes d'affichage************************      
//********************************************************************
    public void showRoot(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout =(BorderPane)loader.load();
            //Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {e.printStackTrace();}     
    }
       
    public void showProfilOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ProfilOverview.fxml"));
            AnchorPane profilPane = (AnchorPane) loader.load();
            // Set person overview into the center of root layout.
            
            this.primaryStage.setTitle("Carboneo - Profils");
            ProfilOverviewController controller = loader.getController();
            controller.setMain(this);
            rootLayout.setCenter(profilPane);
        } catch (IOException e) {e.printStackTrace();}
    }
     
    public void showSelector() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Selector.fxml"));
            VBox selectorPane = (VBox) loader.load();
            // Set person overview into the center of root layout.
            SelectorController controller = loader.getController();
            controller.setMain(this);
            rootLayout.setLeft(selectorPane);
        } catch (IOException e) {e.printStackTrace();}
    }
     
    public void showGeneralOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/GeneralOverview.fxml"));
            BorderPane generalPane = (BorderPane) loader.load();
            
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/NewsOverview.fxml"));
            AnchorPane newsPane = (AnchorPane) loader.load();
            
            generalPane.setLeft(newsPane);
            rootLayout.setCenter(generalPane);
            this.primaryStage.setTitle("Carboneo - General");
        } catch (IOException e) {e.printStackTrace();}
    }
    
    public boolean showProfilEditDialog(Profil tempProfil){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/ProfilEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editer Profil");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            ProfilEditDialogController controller = loader.getController();
            controller.setMain(this);
            controller.setDialogStage(dialogStage);
            controller.setProfil(tempProfil);
            dialogStage.setResizable(false);
            dialogStage.showAndWait();
            return controller.getValidee();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void showTrajet() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/TrajetOverview.fxml"));
            BorderPane trajetPane = (BorderPane) loader.load();
            this.primaryStage.setTitle("Carboneo - Trajet");
            TrajetOverviewController controller = loader.getController();
            controller.setMain(this);
            rootLayout.setCenter(trajetPane);
        } catch (IOException e) {e.printStackTrace();}
    }
        
    
//FIN        
}
