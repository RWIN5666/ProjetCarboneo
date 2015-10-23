/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.view;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.*;

/**
 *
 * @author erwinandre
 */
public class ParsingEverything {

    /**
     * @param args the command line arguments
     */
    
     //Nous allons commencer notre arborescence en créant la racine XML
   //qui sera ici "personnes".
   static Element racine;

   //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
   static org.jdom2.Document document;

  /* public static void main(String[] args) throws JDOMException
   {
       /*
      //On crée un nouvel Element etudiant et on l'ajoute
      //en tant qu'Element de racine
      Element etudiant = new Element("etudiant");
      racine.addContent(etudiant);

      //On crée un nouvel Attribut classe et on l'ajoute à etudiant
     //grâce à la méthode setAttribute
      Attribute classe = new Attribute("classe","P2");
      etudiant.setAttribute(classe);

      //On crée un nouvel Element nom, on lui assigne du texte
      //et on l'ajoute en tant qu'Element de etudiant
      Element nom = new Element("nom");
      nom.setText("CynO");
      etudiant.addContent(nom);

      //Les deux méthodes qui suivent seront définies plus loin dans l'article
     // affiche();
      enregistre("Exercice1.xml");
      
      
      // Partie 2 
      
      //On crée une instance de SAXBuilder 
      System.out.println("Test");
      SAXBuilder sxb = new SAXBuilder(); 
      try {  //On crée un nouveau document JDOM avec en argument le fichier XML
         //Le parsing est terminé ;)
        document = sxb.build(new File("test2.xml")); 
      }
      catch(Exception e){}
      //On initialise un nouvel élément racine avec l'élément racine du document.
      racine = document.getRootElement();
      //Méthode définie dans la partie 3.2. de cet article
     afficheALL();
      
      
      
   }*/
   
   
   
   //Ajouter ces deux méthodes à notre classe JDOM1
static void affiche()
{
   try
   {
      //On utilise ici un affichage classique avec getPrettyFormat()
      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
      sortie.output(document, System.out);
   }
   catch (java.io.IOException e){}
}

static void enregistre(String fichier)
{
   try
   {
      //On utilise ici un affichage classique avec getPrettyFormat()
      XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
      //avec en argument le nom du fichier pour effectuer la sérialisation.
      sortie.output(document, new FileOutputStream(fichier));
   }
   catch (java.io.IOException e){}
}
   





static double afficheALL() throws DataConversionException
{ 

   /* // test premiere solution

//On crée une List contenant tous les noeuds "etudiant" de l'Element racine
        Element elementRoute = racine.getChild("route");
        System.out.println("Test /n");
        List legs = elementRoute.getChildren("leg");
        double distanceTotale = 0;
        
        for(int i = 0;i < legs.size(); i++ ){
            
            Element legActuel = (Element)legs.get(i);
            List listeStep = legActuel.getChildren("step");
            
            for (int j = 0; j< listeStep.size();j++){
                
                Element stepUnique = (Element)listeStep.get(j);
                
                
          
                String distanceTrouvee = stepUnique.getChild("distance").getChild("text").getText();
                 //System.out.println("Test /n" + distanceTrouvee);
             // essai   String tmpstr = "radius: -0.118211 zone";
              /* String asd[] = distanceTrouvee.split(" ");
                double dist = Double.parseDouble(asd[1]);
                System.out.println(dist);
                distanceTotale += dist;
                System.out.println("Test /n" + distanceTotale);*/
                 
              /*  String values[] = distanceTrouvee.split("\t");
                List<String> list = Arrays.asList(values);
                //Reverse the list so that longitude and latitude are the first two elements
                Collections.reverse(list);

                String distanceString = list.get(0);
                double distanceDouble = Double.parseDouble(distanceString);
                
                distanceTotale = distanceTotale + distanceDouble;
               
                
                System.out.println("Test" + j + " " + distanceTotale);
    
                }
        }
                */
    
    
    
                // deuxieme solution
                Element elementRow = racine.getChild("row");
                
                String distanceTrouveeString = elementRow.getChild("element").getChild("distance").getChild("value").getText();
                double distanceDouble = Double.parseDouble(distanceTrouveeString);
                distanceDouble = distanceDouble/1000;
                 System.out.println("Distance trouvee :  " + distanceDouble + " KM " );
                 return distanceDouble;
               
        
}  

        


//Ajouter cette méthodes à la classe JDOM2

/*static void afficheFiltre(){
    
    // on cree un nouveau filtre
    Filter filtre = new Filter()
    {
        
        // on definit les propriétés du filtre à l'aide de la méthode matches
        public boolean matches(Object ob)
        {
            // 1ere vérif on vérif que les objets filtrés sont des instances
            // de Element
            if(!(ob instanceof Element)){return false;}
            
            // on cree un element sur lequel on va faire les vérif
            Element element = (Element)ob;
            //On crée deux variables qui vont nous permettre de vérifier
            //les conditions de nom et de prenom
            int verifNom = 0;
            int verifPrenom = 0;
            
            //2 ème vérification: on vérifie que le nom est bien "CynO"
            if(element.getChild("nom").getTextTrim().equals("CynO")) 
            { 
                verifNom = 1;
            }
            //3 ème vérification: on vérifie que CynO possède un prenom "Laurent" //On commence par vérifier que la personne possède un prenom,
            //en effet notre fichier XML possède des étudiants sans prénom ! 
            Element prenoms = element.getChild("prenoms");
            if(prenoms == null){return false;}
            //On constitue une list avec tous les prenom
            List listprenom = prenoms.getChildren("prenom");
           //On effectue la vérification en parcourant notre liste de prenom //(voir: 3.1. Parcourir une arborescence)
           Iterator i = listprenom.iterator();
           
           while(i.hasNext())
           { 
               Element courant = (Element)i.next(); 
               if(courant.getText().equals("Laurent"))
               { 
                verifPrenom = 1; 
               }
           }
                    //Si nos conditions sont remplies on retourne true, false sinon
           if(verifNom == 1 && verifPrenom == 1)
           { 
               return true;
           }
           return false; 
        }

        @Override
        public boolean isLoggable(LogRecord record) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

       
    };
    
    
    List resultat;
    resultat = racine.getContent(filtre);
    Iterator i = resultat.iterator();
    while(i.hasNext()){
        
        Element courant = (Element)i.next();
        System.out.println(courant.getAttributeValue("classe"));
        
        
        
    }
    
}

*/

}
    
    
  


