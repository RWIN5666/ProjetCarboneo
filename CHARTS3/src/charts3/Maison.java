/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;


public class Maison {
//*****************************Variables******************************
//********************************************************************  
    //private String rue;
    //private String ville;
    private int surface;
    private int consoAnnuelle;
    private int anneeFabrication;
    private String typeEnergie;
    private String adresse;

//***************************Constructeurs****************************     
//********************************************************************
    public Maison (String adresseMaison,int surface, int conso, int annee, String energie){
        this.surface=surface;
        this.consoAnnuelle=conso;
        this.anneeFabrication=annee;
        this.typeEnergie=energie;
        this.adresse = adresseMaison;
    }
//*************************Accesseurs/Mutateurs***********************     
//********************************************************************
    public int getSurface(){ return this.surface;}
    public int getConsoAnnuelle(){ return this.consoAnnuelle;}
    public int getAnneeFabrication(){ return this.anneeFabrication;}
    public String getEnergie(){ return this.typeEnergie;}
    public String getAdresse(){ return this.adresse;}
//************************Méthodes d'acquisition**********************     
//********************************************************************

//************************Méthodes d'execution************************ 
//********************************************************************

//************************Méthodes d'affichage************************      
//********************************************************************
    
    
//FIN    
}
