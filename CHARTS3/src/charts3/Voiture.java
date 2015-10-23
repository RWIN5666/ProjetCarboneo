/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;



// erwin
// modification fonction computeCO2

/**
/**
 *
 * @author schafer
 */
public class Voiture extends ModeDeTransport {
//*****************************Variables******************************
//********************************************************************    
    public int nbPassagers;// test quand même 
    
//***************************Constructeurs****************************     
//********************************************************************   
    public Voiture(String nomModele, Energie energie, int passagers,int conso, int indice){
        
        super(nomModele, energie, conso, indice);
        this.nbPassagers = passagers;  
    }
  
    public Voiture(String nomModele, String energie,int conso,int passagers) {
    	super(nomModele, energie, conso);
        this.nbPassagers = passagers;
    }
    
    public Voiture(String nomModele, String energie,int conso,int passagers, int ident) {
    	super(nomModele, energie, conso, ident);
        this.nbPassagers = passagers;
    }
    
    
//************************Méthodes d'execution************************ 
//********************************************************************
     

    @Override
    public double computeCO2_km(double distanceCalcul) {
         //To change body of generated methods, choose Tools | Templates.

         double em2 = 0;                          
	if (this.getEnergie().equals("DIESEL")){				
            //em2 = Emissions en g √©q. C/km du v√©h.                                    
            em2 = ((804) * (this.consommation)/100);                                         
	}
	if (this.getEnergie().equals("SUPER")){				
            //em2 = Emissions en g √©q. C/km du v√©h.
            em2 = ((774) * (this.consommation)/100);                                                                                                                      
	}				
	// em3 = Total emissions du v√©hicule sur trajet en g √©q. C
	double em3 = em2 * distanceCalcul;           
	// em5 = Emissions en g √©q. C par personne
	double emissionTotale = em3/(1);          // changer 1 par le nombre de passagers
        return emissionTotale;
    }   
}
