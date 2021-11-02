package util.io;

import controller.AccessController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.AlertController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.control.Button;

public class parentSelector {

    private Stage stage = new Stage();
    private String path = "../../fxml/";
    private String Label = "";
    public int EXIT_ALERT = 0;
    public int HIDE_ALERT = 1;
    private int BUTTON_MODE = 0;
    private static int ID = 0;

    public void setID(int İD) {
        this.ID = ID;
    }
            
    
    
    
    
    
    public Stage getStage() {
        return stage;
    }

    public void setButtonMode(int BUTTON_MODE) {
        this.BUTTON_MODE = BUTTON_MODE;
    }

    
    
    
    public void setLabel(String Label) {
        this.Label = Label;
    }

    public FXMLLoader XMLLoader(String source) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path + source));
        return loader;
    }

    public Parent loadRoot(FXMLLoader loader) throws IOException {
        Parent root = loader.load();
        return root;
    }

    public Object getController(FXMLLoader loader) {
        Object s = loader.getController();
        return s;
    }

    public void runApp(Parent root) {
        Scene scene = new Scene(root);
        if (Label.isBlank()) {
            Label = "Hello";
        }
        stage.setTitle(Label);
        stage.setScene(scene);
        stage.show();

    }

    public void alert(String msg) {
        AlertController ac = new AlertController();
        FXMLLoader loader = null;
        try {
            loader = XMLLoader("alert.fxml");
        } catch (IOException ex) {
            Logger.getLogger(parentSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent root = null;
        try {
            root = loadRoot(loader);
        } catch (IOException ex) {
            Logger.getLogger(parentSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        AlertController s = loader.getController();
        s.setControlID(BUTTON_MODE);
        s.setMsg(msg);
        runApp(root);
    }

    public void close(Button o) {
        Stage stage = getStage();
        stage = (Stage) o.getScene().getWindow();
        stage.close();
    }
    
     public void hide() {
        stage.hide();
    }
     
     public void hide(Stage s) {
        s.hide();
    }
    
    
     public void runAccess() throws IOException{
         AccessController ac = new AccessController();
         FXMLLoader loader = XMLLoader("access.fxml");
         Parent root = loadRoot(loader);
         AccessController ac1 = loader.getController();
         ac1.setID(ID);
         runApp(root);
     }
}
