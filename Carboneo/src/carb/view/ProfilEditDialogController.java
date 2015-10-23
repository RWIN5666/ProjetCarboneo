/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.view;

import carb.Main;
import carb.model.Profil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;






public class ProfilEditDialogController {
//**************************** Variables **************************** 
//********************************************************************     
    @FXML
    private TextField fieldNom;
    
    @FXML
    private TextField fieldPrenom;
    
    @FXML
    private TextField fieldRue;
    
    @FXML
    private TextField fieldVille;
    
    private Main main;
    private Stage dialogStage;
    private Profil profil;
    private boolean validee;

//***************************Constructeurs**************************** 
//********************************************************************     
    public ProfilEditDialogController() {this.validee=false;}
        
//*************************Accesseurs/Mutateurs***********************     
//********************************************************************
    public boolean getValidee() {return this.validee;}
    
    public void setDialogStage(Stage dialogStage) {this.dialogStage = dialogStage;}
    
    public void setMain(Main main) {this.main=main;}
    
    public void setProfil(Profil profil) {
        this.profil=profil;
        fieldNom.setText(profil.getNom());
        fieldPrenom.setText(profil.getPrenom());    
        fieldRue.setText(profil.getRue());
        fieldVille.setText(profil.getVille());
    }
    
//*******************Méthodes d'execution graphique******************* 
//********************************************************************    
    @FXML
    private void handleValider(){
        if(isInputValid()){
            profil.setNom(fieldNom.getText());
            profil.setPrenom(fieldPrenom.getText());  
            profil.setRue(fieldRue.getText());
            profil.setVille(fieldVille.getText());
            validee=true;
            dialogStage.close();
        }   
    }
    
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    
//***********************Méthodes d'execution *********************** 
//********************************************************************  
    private boolean isInputValid() {
        String errorMessage ="";
        if(fieldPrenom.getText() == null || fieldPrenom.getText().length()==0) {
            errorMessage +="- Prenom invalide ";           
        }
        
        if(fieldNom.getText() == null || fieldNom.getText().length()==0) {
            errorMessage +="- Nom invalide ";            
        }
        
        if(fieldRue.getText() == null || fieldRue.getText().length()==0) {
            errorMessage +="- Rue invalide ";            
        }
        
        if(fieldVille.getText() == null || fieldVille.getText().length()==0) {
            errorMessage +="- Ville invalide ";            
        }
        
        if (errorMessage.length() == 0){
            return true;
        }
        else {
            Dialogs.create()
            .title("Champs invalides")
            .masthead("Veuillez corriger les champs concernés")
            .message(errorMessage)
            .showError();
            return false;
        } 
    }    
}