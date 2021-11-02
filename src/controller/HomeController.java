package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.io.parentSelector;
import database.Db;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.io.loginHelper;

/**
 * FXML Controller class
 *
 * @author ramo828
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private CheckBox pass_show;
    @FXML
    private TextField login_f;
    @FXML
    private PasswordField password_f;
    @FXML
    private Button login;
    @FXML
    private Button register_button;
    @FXML
    private TextField pass_text;
    @FXML
    private Label statusbar;
    @FXML
    private Label netStatus;
    @FXML
    private Label author;
    private parentSelector p = new parentSelector();
    private loginHelper lh = new loginHelper();
    private database.Db db = new Db();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db.init();
        statusbar.setText("Mesuk V2");
        pass_text.setVisible(false);
    }

    @FXML
    private void pass_show_action(ActionEvent event) {
        String data = password_f.getText();
        if (pass_show.isSelected()) {
            password_f.setVisible(false);
            pass_text.setText(data);
            pass_text.setVisible(true);
        } else {
            password_f.setVisible(true);
            password_f.setText(data);
            pass_text.setVisible(false);
        }
    }

    public void runLogin() {
        String login_tf = login_f.getText();
        String pass_tf = password_f.getText();
        String pass_tf2 = pass_text.getText();
        String cusPass = "";
        loginHelper lh = new loginHelper();
        if (pass_show.isSelected()) {
             lh.setLogin_f(login_tf);
             lh.setPass_f(pass_tf2);
        } else {
            lh.setLogin_f(login_tf);
            lh.setPass_f(pass_tf);
        }
        try {
            lh.loginFirtStep();
        } catch (SQLException ex) {
            p.alert(ex.getMessage());
        } catch (URISyntaxException ex) {
            p.alert(ex.getMessage());

        } catch (IOException ex) {
            p.alert(ex.getMessage());

        }
        if (lh.isLoginStatus()) {
            p.close(login);
        }
    }

    @FXML
    private void login_f_action(ActionEvent event) {
        runLogin();
    }

    @FXML
    private void password_f_action(ActionEvent event) {
        runLogin();
    }

    @FXML
    private void loginButton(ActionEvent event) throws IOException {
        runLogin();
    }

    @FXML
    private void register(ActionEvent event) throws IOException {
        Parent root = p.loadRoot(p.XMLLoader("register.fxml"));
        p.runApp(root);
    }

    @FXML
    private void secretMenu(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.F3) {
            Parent root = p.loadRoot(p.XMLLoader("config.fxml"));
            p.runApp(root);
        }
    }

}
