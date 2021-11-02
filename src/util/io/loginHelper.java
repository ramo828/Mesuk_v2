/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.io;

import database.Db;
import static database.IQueries.settingQuery;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ramo828
 */
public class loginHelper implements loginInterface, database.IQueries {

    private String login_f = "";
    private String pass = "";
    private static parentSelector ps = new parentSelector();
    private static Db d = new Db();
    private Stage stage;
    private boolean loginStatus = false;
    
    
    
    public void setLogin_f(String login_f) {
        this.login_f = login_f;
    }

    public void setPass_f(String pass) {
        this.pass = pass;
    }

    public static int upServer() throws SQLException {

        String upServer = d.getDataString(9, "Select * from settings");
        if (upServer == null) {
            ps.alert("Xəta");
        }
        return Integer.parseInt(upServer);
    }

    public void serverUpdate() throws SQLException, IOException {
        String Server = Db.getDataString(10, "Select * from settings");
        if (upServer() == 1) {
            String settingFile = "settings";
            file f = new file();
            f.setUrl(settingFile);
            File fd = f.fileInit();
            f.writeFile(fd, Server);
            System.out.println("VB'dan server melumatlari alindi");
        }
    }

    private static void update() throws URISyntaxException, IOException, SQLException {
        d.init();
        String updateUrl = d.getDataString(8, settingQuery);
        int update = JOptionPane.showConfirmDialog(null, "Programın yeni versiyasi mövcuddur. yenilənsin?",
                "Diqqət!", JOptionPane.YES_NO_OPTION);
        if (update == JOptionPane.YES_OPTION) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(updateUrl));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ps.alert(ex.getMessage());
            }
            System.exit(0);
        }
    }

    public boolean blockID(String login) throws SQLException {
        d.init();
        if (login.length() <= 3) {
            if (d.executeQuery("Select * from user_value where block_id=?", Integer.parseInt(login))) {
                return true;
            } else {
                return false;
            }
        } else {
            if (d.executeQuery("Select * from user_value where block_id=?", d.getID(login))) {
                return true;
            } else {
                return false;
            }

        }
    }

    public boolean warningID(String login) throws SQLException {
        d.init();
        if (login.length() >= 3) {
            if (d.executeQuery("Select * from user_value where war_id=?", d.getID(login))) {
                return true;
            } else {
                return false;
            }
        } else {
            if (d.executeQuery("Select * from user_value where war_id=?", Integer.parseInt(login))) {
                return true;
            } else {
                return false;
            }

        }
    }

    public void loginFirtStep() throws SQLException, URISyntaxException, IOException {
        String login_tf = login_f;
        if (blockID(login_tf)) {
            ps.setLabel("Bloklanıb");
            ps.alert("Hesabiniz bloklanib");

        } else if (warningID(login_tf)) {
                String warMsg = d.getDataString(4, "Select * from user_value where war_id=" + String.valueOf(d.getID(login_tf)));
                ps.setLabel("Xəbərdarlıq");
                ps.setButtonMode(2);
                ps.alert(warMsg);
                login();
            
        } else {

            login();
        }
    }

     public boolean isLoginStatus() {
        return loginStatus;
    }
    
    private void login() throws SQLException, URISyntaxException, IOException {
        serverUpdate();                                               // Database clsini calistir
        // access a = new access();                                         // Access clasini calisdir
        d.init();                                                        // VB aktiv et
        String login_tf = login_f;                                        // Login TF - ni Stringe cevir
        String password_tf = pass;                                 // Parol TF - ni Stringe cevir
        String acc = d.getDataString(4, settingQuery);                   //  VB-den melumatlari al
        String fixMsg = d.getDataString(5, settingQuery);                // VB-dan mesaji al
        String update = d.getDataString(7, settingQuery);                //  VB-den melumatlari al

        if(login_tf.isBlank() && password_tf.isBlank()){
            ps.alert(("Login ve ya Parol daxil edilməyib!"));
        }
        
        
        if (login_tf.length() <= 3) {                                    // login 3den kicik yada 3e bearberdise asagidaki emrleri icra et
            if (Integer.parseInt(acc) == 1) {                            // eger asagidaki deyerler 1 e beraberdise asagidaki emrleri icra et
                ps.alert(fixMsg);                                        // Mesaji goster
                System.exit(1);                                          // Sistemden cix
            }
            if (Integer.parseInt(update) == 1) {                         // eger acc deyeri 2 beraberdise asagidaki emrleri icra et
                update();
            }
        }                                                                // mesaji goster

        if (d.loginSystem(login_tf, password_tf, d.login) || d.loginSystem(login_tf, password_tf, d.ID)) { //ID ve ya Login uygun gelirse
                ps.setID(d.getID(login_tf));
                             loginStatus = true;
            if (login_tf.length() <= 3) {                                // Eger login 3e bearaber yadaki 3den kicikdirse
                String LOGIN = d.getLogin(Integer.parseInt(login_tf));   // ID olaraq qebul ele ve emrleri icra et
                int ID = d.getID(LOGIN);                                 // ID logine cevir ve sonra set etmek ucun aldigin ID-ni
                ps.setID(ID);
                ps.setLabel("Access");
                ps.runAccess();                                      // accese set et          }
            }

        } else {                                                         // eger sisteme girmeye icaze verilmese
            ps.setLabel("Parol səhvdir");
            ps.setButtonMode(ps.HIDE_ALERT);
            ps.alert(accessDeniedMsg);                                   // xeta mesajini goster
        }
        
    }

    
}
