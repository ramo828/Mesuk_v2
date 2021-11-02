package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ramo828
 */
public class AccessController implements Initializable {

    private int ID = 0;
    /**
     * Initializes the controller class.
     */
    
    public void setID(int ID){
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
