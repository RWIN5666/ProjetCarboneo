

package carb.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class RootLayoutController implements Initializable {

    
    @FXML
    public void handleExit(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    
}
