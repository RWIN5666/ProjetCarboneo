/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;

import java.util.ArrayList;


public class Bilan {
    
//*****************************Variables******************************
//********************************************************************          
    private int consoTotale;
    public int derniereConso;
    private ArrayList <Trajet> historiqueTrajet = new ArrayList();
    
//***************************Constructeurs****************************     
//********************************************************************
    public Bilan(){} 
  
//*************************Accesseurs/Mutateurs***********************
//********************************************************************
    public int getLastConso(){return this.derniereConso;}
    
    public Trajet getLastTrajet(){
        int taille = this.historiqueTrajet.size();
        return this.historiqueTrajet.get(taille);        
    }
       
    public void setLastConso(int newConso){this.derniereConso = newConso;}
    
//************************Méthodes d'execution************************ 
//********************************************************************
    public void update(){ }
 
    public void ajouterTrajetBilan(String depart, String arrivee, ModeDeTransport mode){
    
            Trajet newTrajet = new Trajet(depart, arrivee, mode);
            newTrajet.computeC02();
            historiqueTrajet.add(newTrajet);
    }

    
}
