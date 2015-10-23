/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.model;


public class Velo extends ModeDeTransport{

    public Velo(String energieChoisie) {
        super("Velo", energieChoisie, 0);
    }
    
    

    @Override
    public double computeCO2_km(double distanceCalcul) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void computeCO2_km() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
