
package carb.view;


import carb.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class SelectorController implements Initializable {
    
    private Main main;
    
    
//*********************** Accesseur / Mutateur ***********************      
//********************************************************************
    public void setMain(Main main) {this.main=main;}

//*********************** Méthodes d'affichage ***********************      
//********************************************************************    
    @FXML
    public void handleGeneral() {main.showGeneralOverview();}
    
    @FXML
    public void handleTrajet() {main.showTrajet();}
    
    @FXML
    public void handleBilan() {}
    
    @FXML
    public void handleProfil() {main.showProfilOverview();}   
    
//*********************** Méthodes d'execution ***********************      
//********************************************************************       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    
}
