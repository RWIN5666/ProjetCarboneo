

package carb.view;

import carb.Main;
import carb.model.Profil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;

public class ProfilOverviewController implements Initializable {
//*****************************Variables******************************
//********************************************************************   
    private Main main;
    
    @FXML
    private TableView<Profil> profilTable; 
    
    @FXML
    private TableColumn <Profil,String> prenomColumn;
    
    @FXML
    private TableColumn <Profil,String> nomColumn;
    
    @FXML
    private Label labelPrenom;
   
    @FXML
    private Label labelNom;
    
    @FXML
    private Label labelRue;
    
    @FXML
    private Label labelVille;
    
//*************************Accesseurs/Mutateurs***********************     
//********************************************************************
    public void setMain(Main main){
        this.main=main;
        profilTable.setItems(main.getProfilData());
    }
    
      
//******************** Constructeur / Init ***************************     
//********************************************************************     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prenomColumn.setCellValueFactory(cellData->cellData.getValue().getPrenomProperty());
        nomColumn.setCellValueFactory(cellData->cellData.getValue().getNomProperty());
        showProfilDetails(null);
        profilTable.getSelectionModel().selectedItemProperty().addListener((Observable,oldValue, newValue)->showProfilDetails(newValue));
    }
    
    public ProfilOverviewController() {}
    
//******************Méthodes d'execution graphique********************     
//********************************************************************  
    
    @FXML
    private void handleNewProfil() {
        System.out.println("Nouveau profil");
        Profil tempProfil = new Profil();
        boolean okClicked;
        okClicked =main.showProfilEditDialog(tempProfil);
        if (okClicked) {
            main.getProfilData().add(tempProfil);
        }
    }
    
    @FXML
    private void handleEditProfil() {
        Profil selectedPerson = profilTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = main.showProfilEditDialog(selectedPerson);
            if (okClicked) {
                showProfilDetails(selectedPerson);
            }
        } 
        else {
            // Nothing selected.
            Dialogs.create()
            .title("Sélection Vide")
            .masthead("Aucun profil sélectionné")
            .message("Merci d'en choisir un dans la table")
            .showWarning();
        }
    }
    
    @FXML
    private void handleDeleteProfil() {
        int selectedIndex = profilTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            profilTable.getItems().remove(selectedIndex);
        } 
        else {
            // Nothing selected.
            Dialogs.create()
            .title("Sélection Vide")
            .masthead("Aucun profil sélectionné")
            .message("Merci d'en choisir un dans la table")
            .showWarning();
        }
    }
    
    
//*************************Méthodes d'affichage***********************     
//********************************************************************    
   private void showProfilDetails(Profil profil) {
       if (profil !=null) {
           labelNom.setText(profil.getNom());
           labelPrenom.setText(profil.getPrenom());
           labelRue.setText(profil.getRue());
           labelVille.setText(profil.getVille());
       }
       else {
           labelNom.setText("");
           labelPrenom.setText("");
           labelRue.setText("");
           labelVille.setText("");
       }
   }
           
           
}
