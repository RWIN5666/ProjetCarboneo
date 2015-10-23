

package carb.model;

    public class Trajet {
//*****************************Variables******************************
//********************************************************************  
    private double distance =0;
    public double emissionTotale = 0;
    private String typeRoute;
    private ModeDeTransport modeChoisi;
    private String depart;
    private String arrivee;
    private int id = 0;
    private String date;

//***************************Constructeurs****************************     
//********************************************************************
    public Trajet(String lieuDepart,String lieuArrivee, ModeDeTransport mode) {
        this.depart = lieuDepart;
        this.arrivee = lieuArrivee;
        this.modeChoisi = mode;
    }
    
    public Trajet(String lieuDepart,String lieuArrivee) {
        this.depart = lieuDepart;
        this.arrivee = lieuArrivee;
    }
//*************************Accesseurs/Mutateurs***********************     
//********************************************************************
    public ModeDeTransport getModeChoisi() {return modeChoisi;}

    public String getDepart() {return depart;}
    
    public String getTypeRoute() {return this.typeRoute;}
   
    public String getArrivee() {return arrivee;}
    
    public double getEmissionTotale() {
        this.emissionTotale =  modeChoisi.computeCO2_km(this.distance);
        return this.emissionTotale;
    }
    
    public double getDistance(){return this.distance;}

    public int getId() {return id;}
    
    public String getDate() {return date;}
    
    public void setDepart(String depart) {this.depart = depart;}

    public void setArrivee(String arrivee) {this.arrivee = arrivee;}

    public void setDistance(double distance) {this.distance = distance;}  

    public void setId(int id) {this.id = id;} 

    public void setEmissionTotale(double EmissionTotale) {this.emissionTotale = EmissionTotale;}
   
    public void setModeChoisi(ModeDeTransport modeChoisi) {this.modeChoisi = modeChoisi;}

    public void setDate(String date) {this.date = date;}

//************************Méthodes d'acquisition**********************     
//********************************************************************

//************************Méthodes d'execution************************ 
//********************************************************************

//************************Méthodes d'affichage************************      
//********************************************************************

    public void computeC02(){

        this.emissionTotale =  modeChoisi.computeCO2_km(this.distance);
    }
  
}
