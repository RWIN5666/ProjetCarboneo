/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;

import static charts3.CHARTS3.bdd;
import java.util.List;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author erwinandre
 */
public class recordData{
       // private SimpleStringProperty fieldDay; // Mois
        private SimpleStringProperty fieldModele; // mode de transport
        private SimpleDoubleProperty fieldConso; // consommation
         
        recordData(String fValue1, double fValue2){
      //      this.fieldDay = new SimpleStringProperty(fDay);
            this.fieldModele = new SimpleStringProperty(fValue1);
            this.fieldConso = new SimpleDoubleProperty(fValue2);
        }
         
      /*  public String getFieldDay() {
            return fieldDay.get();
        }*/
         
        public String getFieldModele() {
            return fieldModele.get();
        }
         
        public double getFieldConso() {
            return fieldConso.get();
        }
         
     /*   public void setFieldDay(String fDay) {
            fieldDay.set(fDay);
        }*/
      
        public void setFieldModele(String fValue1) {
            fieldModele.set(fValue1);
        }
         
        public void setFieldConso(Double fValue2) {
            fieldConso.set(fValue2);
        }
        
       
        
        
        
        
    }