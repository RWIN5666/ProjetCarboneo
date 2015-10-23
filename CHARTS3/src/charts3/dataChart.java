/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package charts3;

import static charts3.CHARTS3.bdd;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author erwinandre
 */
public class dataChart {
         
       // ObservableList<recordData> dataList;
        ObservableList<PieChart.Data> pieChartModeleConso;
       // ObservableList<XYChart.Data> xyListDateConso;
         
        dataChart(){
        //    dataList = FXCollections.observableArrayList();
            pieChartModeleConso = FXCollections.observableArrayList();
          //  xyListDateConso = FXCollections.observableArrayList();
        }
         
        public void add(recordData r){
          //  dataList.add(r);
            pieChartModeleConso.add(new PieChart.Data(r.getFieldModele(), r.getFieldConso()));
         //   xyListDateConso.add(new XYChart.Data(r.getFieldDay(), r.getFieldConso()));
    
        }
        
        
        
        public void addData(ArrayList <recordData> liste){
        
            int taille = liste.size();
            for(int i = 0; i<taille; i++){
                
                this.add(liste.get(i));
                
            }
            
            
            
            
        }
        
        
         
        public void update1(int pos, Double val){
            pieChartModeleConso.set(pos, new PieChart.Data(pieChartModeleConso.get(pos).getName(), val));
        }
        
      /*  public void update2(int pos, Double val){
            xyListDateConso.set(pos, new XYChart.Data(xyListDateConso.get(pos).getXValue(), val));
        }*/
        
        
        
        
    }