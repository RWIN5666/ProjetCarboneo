/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author erwinandre
 */
public class SQLiteJDBC {
    
    
    Connection c = null;
    Statement stmt = null;
    
     // Table Names
    private static final String TABLE_ROUTE = "table_route";
    private static final String TABLE_CAR = "table_car";
    private static final String TABLE_ROUTE_FAVORITE = "table_route_favorite";
    private static final String TABLE_HOUSE = "table_house";

    // Common column name
    private static final String KEY_CREATED_AT = "created_at";


    // TABLE_ROUTE - column names
    private static final String COL_DEPARTURE = "DEPARTURE";
    private static final String COL_ARRIVAL = "ARRIVAL";
    private static final String COL_KM = "KM";
    private static final String COL_CO = "CO";
    private static final String COL_CAR_ID= "CAR_ID";


    // TABLE_CAR - column names
    private static final String COL_NAME = "NAME";
    private static final String COL_CLASS = "CLASS_NAME";
    private static final String COL_ENERGIE = "ENERGIE";
    private static final String COL_CONSUMPTION = "CONSUMPTION";
    private static final String COL_CAR_PIC = "PICTURE";
    private static final String COL_DEFAULT= "def_ault";
    
    // TABLE_HOUSE - column names
    private static final String COL_SURFACE = "SURFACE";
    private static final String COL_ANNUAL = "ANNUAL_CONSUMPTION";
    private static final String COL_FABRICATION = "FABRICATION_DATE";
    private static final String COL_ADRESS = "ADRESS";
    private static final String COL_EXISTING= "EXISTING";

    // Primary Keys name
    private static final String KEY_route_ID = "ROUTE_ID";
    private static final String KEY_car_ID = "CAR_ID";
    private static final String KEY_favourite_ID = "FAVORITE_ID";
    private static final String KEY_house_ID = "HOUSE_ID";


    // Table Create Statements

    // route table create statement
    private static final String CREATE_TABLE_ROUTE = "CREATE TABLE IF NOT EXISTS " 
                    + TABLE_ROUTE + " ("
                    + KEY_route_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + COL_DEPARTURE + " TEXT NOT NULL,"
                    + COL_ARRIVAL + " TEXT NOT NULL, " 
                    + COL_KM + " REAL, " 
                    + COL_CO + " REAL, "
                    + COL_CAR_ID + " INTEGER, " 
                    + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_DATE" 
                    + ");"; 

    // car table create statement
    private static final String CREATE_TABLE_CAR = "CREATE TABLE IF NOT EXISTS " 
                    + TABLE_CAR + " ("
                    + KEY_car_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + COL_CLASS + " TEXT NOT NULL,"
                    + COL_NAME + " TEXT NOT NULL,"
                    + COL_ENERGIE + " TEXT NOT NULL,"
                    + COL_CONSUMPTION + " INTEGER, "
                    + COL_CAR_PIC + " INTEGER, "
                    + COL_DEFAULT + " INTEGER, "
                    + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_DATE" 
                    + ");";



    // favorite route create statement
    private static final String CREATE_TABLE_ROUTE_FAVORITE = "CREATE TABLE IF NOT EXISTS " 
                    + TABLE_ROUTE_FAVORITE + " ("
                    + KEY_favourite_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + COL_NAME +  " TEXT NOT NULL,"
                    + COL_DEPARTURE + " TEXT NOT NULL,"
                    + COL_ARRIVAL + " TEXT NOT NULL, " 
                    + COL_KM + " INTEGER, " 
                    + COL_CO + " INTEGER, "
                    + COL_CAR_ID + " INTEGER, " 
                    + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_DATE" 
                    + ");";
    
    
    private static final String CREATE_TABLE_HOUSE = "CREATE TABLE IF NOT EXISTS " 
                    + TABLE_HOUSE + " ("
                    + KEY_house_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
                    + COL_SURFACE + " INTEGER NOT NULL,"
                    + COL_ANNUAL + " INTEGER,"
                    + COL_FABRICATION + " INTEGER, "
                    + COL_ENERGIE + " TEXT NOT NULL,"
                    + COL_ADRESS + " TEXT NOT NULL, "
                    + COL_EXISTING + " INTEGER, "
                    + KEY_CREATED_AT + " DATETIME DEFAULT CURRENT_DATE" 
                    + ");"; 
 
    
// Connexion et deconnexion de la base de données
    public void connection(String nom)
    {
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:"+nom+".db");
          //en cas d'absence de la base de données nom, elle sera créée (option
          //du driver sqlite)
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT COUNT(name) "
                  + "FROM sqlite_master WHERE type='table';" );
          int compteTable=rs.getInt(1);
          if(compteTable==0){
              initDataBase();
          }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Connecté avec succès");
      }

    public void deconnection(){
        try {
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Deconnecté avec succès");

      }

// Initialisation de la base de données en cas de création
    public void initDataBase(){
        try {
          stmt = c.createStatement();
          //on crée les tables (avec une condition IF NOT EXISTS pour ne pas 
          //écraser des tables déjà existante
          stmt.executeUpdate(CREATE_TABLE_ROUTE);
          stmt.executeUpdate(CREATE_TABLE_CAR);
          stmt.executeUpdate(CREATE_TABLE_ROUTE_FAVORITE);
          stmt.executeUpdate(CREATE_TABLE_HOUSE);
          Voiture v1 = new Voiture("AUDI A3","DIESEL",10,1);
          ajouterModeTransport(v1);
          setModeTransportDefault(v1);
          Velo velo = new Velo("Muscles");
          ajouterModeTransport(velo);
     
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    
// Méthodes de gestion des trajets :ajout et récupération
    public void ajouterTrajet(Trajet trajet){
        try {
            stmt = c.createStatement();
            String sql ="INSERT INTO "+TABLE_ROUTE+" "
                    + "VALUES(NULL , "
                    + "'"+trajet.getDepart()+"',"
                    + "'"+trajet.getArrivee()+"',"
                    + trajet.getDistance()+","
                    + trajet.getEmissionTotale()
                    +",(SELECT CAR_ID FROM TABLE_CAR WHERE car_id="+trajet.getModeChoisi().getId()
                    +"),'"+ trajet.getDate()+"') ";           
            stmt.executeUpdate(sql);
            stmt.close();
          } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
          }
          System.out.println("Opération effectuée avec succès");
      }

    public List<Trajet> getAllTrajet(String mode){
          
          List<Trajet> trajet= new ArrayList<>();
          try {
            stmt = c.createStatement();
            ResultSet rs= null;

            rs = stmt.executeQuery("SELECT * FROM "+TABLE_ROUTE+";" );
            
            while ( rs.next() ) {
                String  depart = rs.getString(COL_DEPARTURE);
                String  arrive = rs.getString(COL_ARRIVAL);
                int distance = rs.getInt(COL_KM);
                int emission = rs.getInt(COL_CO);
                int car_id = rs.getInt(COL_CAR_ID);
                String date = rs.getString(KEY_CREATED_AT);
                ModeDeTransport transport = null;
                ResultSet result= null;
                if (mode == null){
                    result = stmt.executeQuery("SELECT * FROM"+TABLE_CAR+" "
                                           + "WHERE car_id="+car_id+";");
                }
                else{
                    result = stmt.executeQuery("SELECT * FROM"+TABLE_CAR+" "
                                           + "WHERE car_id="+car_id+" "
                                           + "AND "+COL_CLASS+"='"+mode+"';");
                }
               while(result.next()){
                   String name = rs.getString(COL_NAME);
                   String typeVehicule= rs.getString(COL_CLASS);
                   String energie = rs.getString(COL_ENERGIE);
                   int conso = rs.getInt(COL_CONSUMPTION);
                   if("Voiture".equals(typeVehicule)){
                   transport=new Voiture(name,energie,conso,1);
                   }
                   if("Bus".equals(typeVehicule)){
                   transport=new Bus("Bibus",energie);
                   }
                   else{
                   transport= new Velo(energie);
                   }
               }
               result.close();
               Trajet t=new Trajet(depart, arrive,transport);
               t.setEmissionTotale((double)emission);
               t.setDistance(distance);
               t.setDate(date);
               trajet.add(t);
            }
          rs.close();
          stmt.close();
          } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
        return trajet;
    }
    
    public List<Trajet> getTrajetFrom(String mode,String dateDebut){

        List<Trajet> trajet= new ArrayList<>();
        try {
          stmt = c.createStatement();
          ResultSet rs= null;
          rs = stmt.executeQuery("SELECT * FROM "+TABLE_ROUTE+" "
                                + "WHERE "+KEY_CREATED_AT+">="+dateDebut+";" );

          
          while ( rs.next() ) {
            String  depart = rs.getString(COL_DEPARTURE);
            String  arrive = rs.getString(COL_ARRIVAL);
            int distance = rs.getInt(COL_KM);
            int emission = rs.getInt(COL_CO);
            int car_id = rs.getInt(COL_CAR_ID);
            String date = rs.getString(KEY_CREATED_AT);
            ModeDeTransport transport = null;
            ResultSet result= null;
            if (mode == null){
                result = stmt.executeQuery("SELECT * FROM"+TABLE_CAR+" "
                                       + "WHERE car_id="+car_id+";");
            }
            else{
                result = stmt.executeQuery("SELECT * FROM"+TABLE_CAR+" "
                                       + "WHERE car_id="+car_id+" "
                                       + "AND "+COL_CLASS+"='"+mode+"';");
            }
             while(result.next()){
                 String name = rs.getString(COL_NAME);
                 String typeVehicule= rs.getString(COL_CLASS);
                 String energie = rs.getString(COL_ENERGIE);
                 int conso = rs.getInt(COL_CONSUMPTION);
                 if("Voiture".equals(typeVehicule)){
                   transport=new Voiture(name,energie,conso,1);
                   }
                   if("Bus".equals(typeVehicule)){
                   transport=new Bus("Bibus",energie);
                   }
                   else{
                   transport= new Velo(energie);
                   }
             }
             result.close();
             Trajet t=new Trajet(depart, arrive,transport);
             t.setEmissionTotale((double)emission);
             t.setDistance(distance);
             t.setDate(date);
             trajet.add(t);
          }
        rs.close();
        stmt.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
      System.out.println("Opération effectuée avec succès");
      return trajet;
    }
    
    public List<Trajet> getTrajetFromTo(String mode,String dateDebut, String dateFin){

        List<Trajet> trajet= new ArrayList<>();
        try {
          stmt = c.createStatement();
          ResultSet rs= null;
            
          rs = stmt.executeQuery("SELECT * FROM "+TABLE_ROUTE+" "
                            + "WHERE "+KEY_CREATED_AT+">="+dateDebut+""
                            + "AND "+KEY_CREATED_AT+"<="+dateFin+";" );
            
          
          while ( rs.next() ) {
            String  depart = rs.getString(COL_DEPARTURE);
            String  arrive = rs.getString(COL_ARRIVAL);
            int distance = rs.getInt(COL_KM);
            int emission = rs.getInt(COL_CO);
            int car_id = rs.getInt(COL_CAR_ID);
            String date = rs.getString(KEY_CREATED_AT);
            ModeDeTransport transport = null;
            ResultSet result= null;
            if (mode == null){
                result = stmt.executeQuery("SELECT * FROM"+TABLE_CAR+" "
                                       + "WHERE car_id="+car_id+";");
            }
            else{
                result = stmt.executeQuery("SELECT * FROM"+TABLE_CAR+" "
                                       + "WHERE car_id="+car_id+" "
                                       + "AND "+COL_CLASS+"='"+mode+"';");
            }
            while(result.next()){
                String name = rs.getString(COL_NAME);
                String typeVehicule= rs.getString(COL_CLASS);
                String energie = rs.getString(COL_ENERGIE);
                int conso = rs.getInt(COL_CONSUMPTION);
                if("Voiture".equals(typeVehicule)){
                  transport=new Voiture(name,energie,conso,1);
                  }
                  if("Bus".equals(typeVehicule)){
                  transport=new Bus("Bibus",energie);
                  }
                  else{
                  transport= new Velo(energie);
                  }
            }
            result.close();
            Trajet t=new Trajet(depart, arrive,transport);
            t.setEmissionTotale((double)emission);
            t.setDistance(distance);
            t.setDate(date);
            trajet.add(t);
          }
        rs.close();
        stmt.close();
        } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
    System.out.println("Opération effectuée avec succès");
    return trajet;
    }
    
    
//Méthodes de gestion des maisons :ajout, suppression et récupération(non-implémentée)   
    public void ajouterMaison(Maison maison){
        try {
          stmt = c.createStatement();
          
            String sql = "INSERT INTO "+TABLE_HOUSE+" "
                    + "VALUES (NULL,"+maison.getSurface()+","
                    +maison.getConsoAnnuelle()+","
                    +maison.getAnneeFabrication()+","
                    + "'"+maison.getEnergie()+"',"
                    + "'"+maison.getAdresse()+"'"
                    + "NULL);";
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
    }
  
    public void supprimerMaison(Maison maison){
        try {
          stmt = c.createStatement();
          
          String sql = "UPDATE Maison"
                    + "WHERE adresse='"+maison.getAdresse()+"';";
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
    }
    
    public List<Maison> getMaison(){
          
          List<Maison> maisons= new ArrayList<>();
          try {
            stmt = c.createStatement();
            ResultSet rs= stmt.executeQuery("SELECT * FROM"+TABLE_HOUSE+" "
                                    + "WHERE "+COL_EXISTING+"<> -1;");

            
            while ( rs.next() ) {
                   int surface = rs.getInt(COL_SURFACE);
                   int conso = rs.getInt(COL_ANNUAL);
                   int fabrication = rs.getInt(COL_FABRICATION);
                   String energie = rs.getString(COL_ENERGIE);
                   String adresse = rs.getString(COL_ADRESS);
                   maisons.add(new Maison(adresse,surface,conso,fabrication,energie));
            }
          rs.close();
          stmt.close();
          } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
        return maisons;
    }
       
//Méthodes de gestion des modes de transport :ajout, suppression, récupération,
//mise en favori d'un mode de transport
     public void ajouterModeTransport(ModeDeTransport transport){
        try {
          stmt = c.createStatement();
          String sql ="INSERT INTO "+TABLE_CAR+" "
                    + "VALUES (NULL,"
                    + "'"+transport.getClass().getSimpleName().toString()+"',"
                    + "'"+transport.getNom()+"',"
                    + "'"+transport.getEnergie()+"',"
                    + transport.getConsommation()+","
                    + "NULL, "
                    + "0, "
                    + "NULL)";
          
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
    }
    
     public void supprimerModeTransport(ModeDeTransport transport){
        try {
          stmt = c.createStatement();
          String sql ="UPDATE "+TABLE_CAR+""
                    + "SET "+COL_DEFAULT+"= -1"
                    + "WHERE "+COL_NAME+"='"+transport.getNom()+"';";
          
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
    }
     
     public void setModeTransportDefault(ModeDeTransport transport){
         try {
          stmt = c.createStatement();
          String sql ="UPDATE "+TABLE_CAR+" "
                    + "SET "+COL_DEFAULT+" = 0 "
                    + "WHERE "+COL_DEFAULT+" = 1 ;";
          stmt.executeUpdate(sql);
          
          sql ="UPDATE "+TABLE_CAR+" "
               + "SET "+COL_DEFAULT+"= 1 "
               + "WHERE "+COL_NAME+"='"+transport.getNom()+"';";
          stmt.executeUpdate(sql);
          stmt.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
    }
     
     public List<ModeDeTransport> getModeDeTransport(String mode){
          
         System.out.println(" On rentre 1");
          List<ModeDeTransport> transports= new ArrayList<>();
          try {
            stmt = c.createStatement();
            System.out.println(" On rentre 2");
            ResultSet rs= null;
            
            if (mode == null){
                    rs = stmt.executeQuery("SELECT * FROM "+TABLE_CAR+"  "
                                    + "WHERE "+COL_DEFAULT+"<> -1;");
                    System.out.println(" On rentre 6");
                }
                else{
                    rs = stmt.executeQuery("SELECT * FROM "+TABLE_CAR+" "
                                           + "WHERE "+COL_CLASS+"='"+mode+"';");
                    System.out.println(" On rentre 4");
                }
            
            while ( rs.next() ) {
                    System.out.println(" On rentre 3");
                   String name = rs.getString(COL_NAME);
                   String typeVehicule= rs.getString(COL_CLASS);
                   String energie = rs.getString(COL_ENERGIE);
                   int conso = rs.getInt(COL_CONSUMPTION);
                   if("Voiture".equals(typeVehicule)){
                    transports.add(new Voiture(name,energie,conso,1));
                   }
                   if("Bus".equals(typeVehicule)){
                    transports.add(new Bus("Bibus",energie));
                   }
                   else{
                    transports.add(new Velo(energie));
               }
            }
            System.out.println(" On rentre 4");
          rs.close();
          stmt.close();
          } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Opération effectuée avec succès");
        return transports;
    }
    
    
    
}
