/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testbdd;


public abstract class Energie {
    
//*****************************Variables******************************
//********************************************************************  
    private String nomEnergie;
    private double facteurC02;
    
//*************************Accesseurs/Mutateurs***********************
//********************************************************************
    public double getFacteurCO2(){return this.facteurC02;}
    
    public String getNomEnergie(){return this.nomEnergie;}
    
    public void setFacteurC02(int newFacteur) {this.facteurC02 = newFacteur;}
   
    public void setnomEnergie(String newNom) {this.nomEnergie = newNom;}
    
//***************************Constructeurs****************************     
//********************************************************************
    public Energie(String nom, double facteur){
        this.nomEnergie = nom;
        this.facteurC02 = facteur;       
    }
       

    
}
