/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testbdd;

import java.util.List;

/**
 *
 * @author Erwin
 */
public class TestBDD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SQLiteJDBC base = new SQLiteJDBC();
        base.connection("Essai");       
        List<ModeDeTransport> listeMod = base.getModeDeTransport("Voiture"); 
        ModeDeTransport modechoix = listeMod.get(0);
        
        String origine = "Brest";
        String arrivee = "Guidel";
        
        
        Trajet trajetTemp = new Trajet(origine,arrivee,modechoix);
        
        trajetTemp.setDistance(30);
        trajetTemp.computeC02();
        trajetTemp.setDate("2014-05-28");
        
        System.out.println(" Test " + modechoix.getNom() + modechoix.getId());
        
         base.ajouterTrajet(trajetTemp);
      
        base.deconnection();
        
     
    }
    
}
