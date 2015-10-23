/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;


public class Velo extends ModeDeTransport{

    public Velo(String energieChoisie) {
        super("Velo", energieChoisie, 0);
    }
    
    public Velo(String energieChoisie, int ident) {
        super("Velo", energieChoisie, 0,ident);
    }

    @Override
    public double computeCO2_km(double distanceCalcul) {
        return 100; //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
