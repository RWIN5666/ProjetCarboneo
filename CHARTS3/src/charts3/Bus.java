
package charts3;


public class Bus extends ModeDeTransport {
    
    public Bus(String compagnie, String energieChoisie ){
        super(compagnie, energieChoisie,1);       
    }

    public Bus(String compagnie, String energieChoisie, int ident ){
        super(compagnie, energieChoisie,1, ident);       
    }


    @Override
    public double computeCO2_km(double distanceCalcul) {
        // calcul très simple
        // Emissions en g éq. C par personne
        return distanceCalcul*27.7; 
    }
}
