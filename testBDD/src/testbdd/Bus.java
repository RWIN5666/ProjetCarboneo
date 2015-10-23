
package testbdd;


public class Bus extends ModeDeTransport {
    
    public Bus(String compagnie, String energieChoisie ){
        super(compagnie, energieChoisie,1);       
    }

    

    @Override
    public double computeCO2_km(double distanceCalcul) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
