/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.view;


import carb.model.Dates;
import java.util.Iterator;
import java.util.List;
import org.jdom2.*;



/**
 *
 * @author erwinandre
 */
public class NewsParseur {
    
    
    // attributs nécessaires pour parser le fichier XML
    static org.jdom2.Element racineNews;
    static org.jdom2.Document documentNews; 
    static String dateString =  Dates.date(); // valeur de test comme Thomas n'a pas mis à jour le XML de news
    
    
    static String getTexte() throws DataConversionException
{ 

    Element elementNews = racineNews.getChild("allnews");
                
    Element newsTrouvee = null;
    Element courant = null;


    // dateString = dateDuJour.date(); // quand il sera toujours à jour              
    List listNews = elementNews.getChildren("news");
    Iterator i = listNews.iterator();

    int dateTrouvee = 0;

    while(dateTrouvee !=1 & i.hasNext()) {// tant que la date n'est pas trouvé et qu'il reste des news
    // à analyser
        courant = (Element)i.next();

        String dateActuelle = courant.getChild("date").getText();     // petit test de date              
        if(dateActuelle.equals(dateString)){      // si on trouve une news aujourd'hui         
           dateTrouvee = 1;
           newsTrouvee = courant.clone();

        }    
    }         


        if(dateTrouvee == 1){
            return newsTrouvee.getChild("text").getText();                    
         }                 
        else{                    
            System.out.println("Il y a eu une couille");     
            return null;
        }

}      
    
    
    static String getTitre() throws DataConversionException { 

        Element elementNews = racineNews.getChild("allnews");
        Element newsTrouvee = null;
        Element courant = null;
        // dateString = dateDuJour.date(); // quand il sera toujours à jour              
        List listNews = elementNews.getChildren("news");
        Iterator i = listNews.iterator();

        int dateTrouvee = 0;

        while(dateTrouvee !=1 & i.hasNext()) {// tant que la date n'est pas trouvé et qu'il reste des news
            // à analyser
            courant = (Element)i.next();
            String dateActuelle = courant.getChild("date").getText();     // petit test de date              
            if(dateActuelle.equals(dateString)){      // si on trouve une news aujourd'hui         
                dateTrouvee = 1;
                newsTrouvee = courant.clone();
            }    
        }         
                
                
        if(dateTrouvee == 1){
            return newsTrouvee.getChild("title").getText();                    
        }                 
        else{                    
            System.out.println("Il y a eu une couille");     
            return null;
        }
    } 
   
}
