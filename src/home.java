/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import util.io.parentSelector;
/**
 *
 * @author ramo828
 */
public class home extends Application {

    @Override
    public void start(Stage stage) throws Exception {
          parentSelector p = new parentSelector();
          Parent root = p.loadRoot(p.XMLLoader("home.fxml"));
          p.runApp(root);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
