/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.io.parentSelector;

/**
 * FXML Controller class
 *
 * @author ramo828
 */
public class AlertController implements Initializable {

    @FXML
    private Label mesaj;
    @FXML
    private Button alertButton;
    private int controlID = 0;
    private parentSelector p = new parentSelector();

    public void setControlID(int controlID) {
        this.controlID = controlID;
    }

    public void setMsg(String msg) {
        if (msg.length() > 15) {
            mesaj.setStyle("-fx-font-size: 18px");
        } else if (msg.length() > 25) {
            mesaj.setStyle("-fx-font-size: 12px");
        } else if (msg.length() > 35) {
            mesaj.setStyle("-fx-font-size: 5px");
        }
        mesaj.setText(msg);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void alertControl(ActionEvent event) {
        if (controlID == 0) {
            System.exit(0);
        } else if (controlID == 1) {
            p.close(alertButton);
        } else if (controlID == 2){
            try {
                p.runAccess();
                            p.close(alertButton);

            } catch (IOException ex) {
                Logger.getLogger(AlertController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
