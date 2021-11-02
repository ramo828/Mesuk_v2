package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.Db;
import static database.Db.read;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.io.file;
import util.io.parentSelector;

/**
 * FXML Controller class
 *
 * @author ramo828
 */
public class ConfigController implements Initializable {
    
    @FXML
    private TextField server_tf;
    @FXML
    private TextField port;
    @FXML
    private TextField db_name;
    @FXML
    private TextField table_name;
    @FXML
    private TextField db_pass;
    @FXML
    private Button config_button;
    private static parentSelector ps = new parentSelector();

    /**
     * Initializes the controller class.
     */
    
    private static String Base64encode(String val) {
        return Base64.getEncoder().encodeToString(val.getBytes());
    }
    
    private static String Base64decode(String val) {
        return new String(org.apache.commons.codec.binary.Base64.decodeBase64(val.getBytes()));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Db d = new Db();
        file f = new file();
        String settingFile = f.getDir() + "/settings";
        f.setUrl(settingFile);
        File fd = f.fileInit();
        if (f.existFile(fd)) {
            f.createFile(fd);
        }
        String sul = d.read(0, settingFile);
        String p = d.read(1, settingFile);
        String t_n = read(4, settingFile);
        //--------Istifadeki melumatlar
        String us = read(2, settingFile);
        String ps = read(3, settingFile);
        server_tf.setText(sul);
        port.setText(p);
        db_name.setText(us);
        db_pass.setText(Base64encode(ps));
        table_name.setText(t_n);
        
    }    
    
    @FXML
    private void conf_button(ActionEvent event) {
        file f = new file();
        Db d = new Db();
        String settingFile = f.getDir() + "/settings";
        f.setUrl(settingFile);
        File fd = f.fileInit();
        if (f.existFile(fd)) {
            ps.setButtonMode(1);
            ps.alert("Ayar fayli var");
        } else {
            ps.setButtonMode(1);
            ps.alert("Ayar fayli yazildı");
            f.createFile(fd);
        }
        String serv = server_tf.getText();
        String port_f = port.getText();
        String db_n = db_name.getText();
        String db_p = Base64decode(db_pass.getText());
        String table_n = table_name.getText();
        String all = serv + "," + port_f + "," + db_n + "," + db_p + "," + table_n;
        try {
            f.writeFile(fd, Base64encode(all));
        } catch (IOException ex) {
            ps.alert(ex.getMessage());
        }
        
    }
    
}
