/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Profil {
//*****************************Variables******************************
//********************************************************************  
    private StringProperty nom;
    private StringProperty prenom;
    private String rue;
    private String ville;
    public ArrayList <ModeDeTransport> transports = new ArrayList();
    public Bilan bilanPerso;
    public ArrayList <Trajet> trajetFavoris;
    public Maison domicile;
    

//***************************Constructeurs****************************     
//********************************************************************
    public Profil(String nom, String prenom){
        this.nom= new SimpleStringProperty(nom);
        this.prenom =new SimpleStringProperty(prenom);
        this.rue= "random street";
        this.ville="random city";
    }
    
    public Profil() {
        this.nom= new SimpleStringProperty("");
        this.prenom =new SimpleStringProperty("");
        this.rue= "";
        this.ville="";
    
    }
   
//*************************Accesseurs/Mutateurs***********************
//********************************************************************
    public String getNom() {return nom.get();}
    public StringProperty getNomProperty() {return nom;}

    public String getPrenom() {return prenom.get();}
    public StringProperty getPrenomProperty() {return prenom;}

    public String getRue() {return rue;}

    public String getVille() {return ville;}
    
    public Bilan getBilanPerso() {return bilanPerso;}
        
    public void setNom(String nom) {this.nom.set(nom);}

    public void setPrenom(String prenom) {this.prenom.set(prenom);}

    public void setRue(String rue) {this.rue = rue;}

    public void setVille(String ville) {this.ville = ville;}
    
//************************Méthodes d'acquisition**********************     
//********************************************************************
    public void ajouterModeTransport(ModeDeTransport mode){
        this.transports.add(mode);
    }
    
    public void ajouterMaison(Maison maison){
        this.domicile=maison;
    }
    
    public void ajouterTrajetFavoris(Trajet trajet){
        this.trajetFavoris.add(trajet);
    }
    
    /*public void supprimerMaison(){}
    
    public void supprimerMode(){}
    
    public void supprimerFavoris(){}*/
//************************Méthodes d'execution************************ 
//********************************************************************

//************************Méthodes d'affichage************************      
//********************************************************************     
    
    
//FIN    
}
