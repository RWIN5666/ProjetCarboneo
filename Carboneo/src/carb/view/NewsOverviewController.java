/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carb.view;


import static carb.view.NewsParseur.documentNews;
import static carb.view.NewsParseur.racineNews;

import carb.model.Dates;
import java.io.IOException;
import java.util.ResourceBundle;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author erwinandre
 */
public class NewsOverviewController implements Initializable {
    
    public Dates dateTest = new Dates();
    
    @FXML
    private Label labelDate;
    
    @FXML
    private Label labelTitre;
    
    @FXML
    private Label labelTexte;
   
    @FXML
    private Button bouton1;   
    public SAXBuilder sxb = new SAXBuilder(); 
    public NewsParseur superParseur = new NewsParseur();
    
    public String essaiTitre;
    public String essaiTexte;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws MalformedURLException, JDOMException, IOException {
        System.out.println("You clicked me!");
        System.out.println(dateTest.date());
        labelDate.setText(dateTest.date());
        
       String urlParcours = "http://bl4ckbird.co.nf/news/myNews.xml";
       URL Url = new URL(urlParcours); 
       documentNews = sxb.build(Url.openStream());
       
       
       
      //On initialise un nouvel élément racine avec l'élément racine du document.
        racineNews =documentNews.getRootElement();
         
        essaiTitre = superParseur.getTitre();
        essaiTexte = superParseur.getTexte();
        
         labelTitre.setText(""+essaiTitre);
         labelTexte.setText(essaiTexte);
        
        
        System.out.println("Fini" + essaiTitre);            
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(dateTest.date());     
    }    
    
}
