package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import util.io.parentSelector;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import helper.registerHelper;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author ramo828
 */
public class RegisterController implements Initializable {

    @FXML
    private AnchorPane regPanel;
    @FXML
    private TextField login;
    @FXML
    private TextField pass;
    @FXML
    private TextField try_pass;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private ChoiceBox<String> sex;
    @FXML
    private Button reg_button;
    @FXML
    private Label statusbar;
    private parentSelector ps = new parentSelector();
    
    /**
     * Initializes the controller class.
     */
    private String login_tf;
    private String pass_tf;
    private String pass1_tf;
    private String name_tf;
    private String surname_tf;
    private String email_tf;
    private String number_tf;

    @FXML
    private TextField email;
    @FXML
    private TextField number;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sex.setValue("Kişi");
        sex.getItems().add("Kişi");
        sex.getItems().add("Qadın");
    }

    private void filter() throws SQLException {
        login_tf = login.getText();
        pass_tf = pass.getText();
        pass1_tf = try_pass.getText();
        name_tf = name.getText();
        surname_tf = surname.getText();
        email_tf = email.getText();
        number_tf = number.getText();

        if (login_tf.isEmpty()) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Login boş buraxıldı!");
        } else if (login_tf.length() < 3) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Login çox qısadır!");
        } else if (pass_tf.isEmpty()) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Parol boş buraxıldı!");
        } else if (!pass_tf.equals(pass1_tf)) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Parol uyğun deyil!");
        } else if (pass_tf.length() < 8 || pass1_tf.length() < 8) {
            statusbar.setStyle("-fx-font-size: 20px");
            statusbar.setText("Parol ən az 8\nsimvoldan ibarət olmalıdır!");
        } else if (name_tf.isBlank()) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Ad boş buraxıldı!");
        } else if (name_tf.length() < 3) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Ad çox qısadır!");
        } else if (surname_tf.isBlank()) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Soyad boş buraxıldı!");
        } else if (surname_tf.length() < 3) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Soyad çox qısadır!");
        } else if (email_tf.isBlank()) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Email boş buraxıldı!");
        } else if (email_tf.length() < 3) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Email çox qısadır!");
        } else if (email_tf.indexOf("@") == -1) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Email xətalı daxil edilib!");
        } else if (number_tf.isBlank()) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Nömrə boş buraxıldı!");
        } else if (number_tf.length() < 3) {
            statusbar.setStyle("-fx-font-size: 30px");

            statusbar.setText("Nömrə çox qısadır!");
        } else if (number_tf.indexOf("+") == -1) {
            statusbar.setStyle("-fx-font-size: 20px");
            statusbar.setText("Nömrəni beynəlxalq qaydalara\n uyğun qeyd edin!");
        } else {
            try {
                runApp();
            } catch (InterruptedException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void runApp() throws InterruptedException, SQLException {
        statusbar.setStyle("-fx-font-size: 35px");
        statusbar.setStyle("-fx-text-fill: lightgreen");
        statusbar.setText("Qeydiyyat ugurludur");
        registerHelper rh = new registerHelper(login_tf,pass_tf,number_tf,email_tf,name_tf,surname_tf,sex.getValue());
        if(rh.run()){
             ps.setLabel("Qeydiyyat tamamlandi");
            ps.setButtonMode(1);
            ps.close(reg_button);
            ps.alert("Qeydiyyat ugurlu oldu");
        } else {
             ps.setLabel("Qeydiyyat ugursuzdur");
            ps.setButtonMode(0);
            ps.alert("Qeydiyyat ugursuz oldu");
        }
    }

    @FXML
    private void login_m(ActionEvent event) throws SQLException {
        filter();

    }

    @FXML
    private void pass_m(ActionEvent event) throws SQLException {
        filter();

    }

    @FXML
    private void try_pass_m(ActionEvent event) throws SQLException {
        filter();

    }

    @FXML
    private void name_m(ActionEvent event) throws SQLException {
        filter();

    }

    @FXML
    private void surname_m(ActionEvent event) throws SQLException {
        filter();

    }

    @FXML
    private void sex_m(MouseEvent event) {
    }

    @FXML
    private void reg_button_m(ActionEvent event) throws SQLException {
        filter();
    }

    @FXML
    private void email_m(ActionEvent event) throws SQLException {
        filter();

    }

    @FXML
    private void number_m(ActionEvent event) throws SQLException {
        filter();

    }

    

    
    
}
