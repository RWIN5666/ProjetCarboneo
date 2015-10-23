/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// modification fonction computeCO2
/**
 *
 * @author schafer
 */
public abstract class ModeDeTransport {
    
    public final static String DIESEL = "DIESEL";
    public final static String GPL = "GPL";
    public final static String SUPER = "SUPER";
        
//*****************************Variables******************************
//********************************************************************  
        
    private String nom;
    private String date; 
    private String energie;          
    private int id = 0;
    private int defaut1=0;
    
    public int indiceCalcul;
    public double consommation;
  //  public int nbPassagers;

//***************************Constructeurs****************************     
//********************************************************************
    public ModeDeTransport(String name, Energie energieChoisie, int conso, int indice){
        
        this.nom = name;
        //this.energie = energieChoisie;
        this.consommation = conso;
        this.indiceCalcul = indice;
    }
    
    public ModeDeTransport(String name, String energieChoisie, int conso){
        this.nom = name;
        this.energie = energieChoisie;
        this.consommation = conso;
    }
    
//*************************Accesseurs/Mutateurs***********************
//********************************************************************
    public String getNom() {return nom;}

    public String getDate() {return date;}

    public String getEnergie() {return energie;}

    public int getId() {return id;}

    public int getDefaut1() {return defaut1;}

    public abstract double computeCO2_km(double distanceCalcul);

    public void setNom(String nom) {this.nom = nom;}

    public void setEnergie(String energie) {this.energie = energie;}

    public void setId(int id) {this.id = id;}

    public void setDefaut1(int defaut1) {this.defaut1 = defaut1;}

    public void setConsommation(int consommation) {this.consommation = consommation;}
    
    public double getConsommation() {return this.consommation;}
    
    
    
//************************Méthodes d'acquisition**********************     
//********************************************************************

//************************Méthodes d'execution************************ 
//********************************************************************

    /**
     *
     * @param distance
     */
        public void computeCO2_km(){};
    
//************************Méthodes d'affichage************************      
//********************************************************************

    


   
}
