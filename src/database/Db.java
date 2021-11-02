/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import org.apache.commons.codec.binary.Base64;
import util.io.file;
import util.io.parentSelector;

/**
 *
 * @author ramo828
 */
public class Db implements IDatabase, IQueries {

    private static parentSelector ps = new parentSelector();

    public static String read(int index, String value) {

        file f = new file();
        f.setUrl(value);
        File fd = f.fileInit();
        String s = file.readFileLine(fd);
        String decoded = new String(Base64.decodeBase64(s.getBytes()));
        String ss[] = decoded.split(",");

        return ss[index];

    }

    file f = new file();
    private String settingFile = f.getDir() + "/settings";

    private String servUrl = read(0, settingFile);
    private String port = read(1, settingFile);
    private String t_name = read(4, settingFile);
    //--------Istifadeki melumatlar
    private String user = read(2, settingFile);
    private String pass = read(3, settingFile);

    private String url = "jdbc:mysql://" + servUrl + ":" + String.valueOf(port) + "/" + t_name;

    static Connection con = null;

    private static Statement stmt;
    private static boolean status_db = false;
    private static String logins[] = {""};
    public static int login = 0;
    public static int ID = 1;

    public void init() {
        try {
            try {
                Class.forName(driver);

            } catch (ClassNotFoundException ex) {
                ps.alert(ex.getMessage());

                Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);

            }

            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                status_db = true;
                //JOptionPane.showMessageDialog(null,"Veritabanına bağlanıldı.");
            } else {
                status_db = false;
            }
        } catch (SQLException ex) {
            ps.alert(ex.getMessage());

            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public static boolean isStatus_db() {
        return status_db;
    }

    public static int getLastID() throws SQLException {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(allUser);
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }

    public boolean loginSystem(String username, String password, int ch) {
        String changeLogin[] = {"login", "id"};
        String sql = "Select * from users where " + changeLogin[ch] + "=? and pass=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {
                return false;
            }

        } catch (Exception e) {

            ps.alert(e.getMessage());

        }
        return false;

    }

    public static String sqlRead(String dat, String sorgu) {
        String data = "";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sorgu);
            while (rs.next()) {
                data += "\n" + rs.getString(dat);
            }
        } catch (Exception e) {
            ps.alert(e.getMessage());

            return null;
        }
        return data;

    }

    public static boolean alreadyInput(String value, int index) {
        String dataSql = "";
        switch (index) {
            case 0:
                dataSql += loginSql;
                break;
            case 1:
                dataSql += emailSql;
                break;

            default:
                break;
        }
        try {

            PreparedStatement pst = con.prepareStatement(dataSql);
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {
                return false;
            }

        } catch (Exception e) {

            ps.alert(e.getMessage());

        }
        return false;

    }

    public static boolean newUser(
            String login,
            String pass,
            String number,
            String email,
            String ad,
            String soyad,
            int coin,
            int status,
            String pref055,
            String pref099,
            int vezife,
            int maxSendMsg,
            String  sex) {

        try {
            Statement stmt = con.createStatement();
            String bg = "('";
            String sp = "','";
            String ed = "')";
            String sorgu
                    = "insert into users values"
                    + bg
                    + String.valueOf(getLastID() + 1) + sp
                    + login + sp
                    + pass + sp
                    + number + sp
                    + email + sp
                    + ad + sp
                    + soyad + sp
                    + String.valueOf(coin) + sp
                    + String.valueOf(status) + sp
                    + pref055 + sp
                    + pref099 + sp
                    + String.valueOf(vezife) + sp
                    + String.valueOf(maxSendMsg)+sp
                    + sex
                    + ed;
            System.out.println(sorgu);
            int ekleme = stmt.executeUpdate(sorgu);
            return true;
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }

    }

    public static boolean warUser(int ID, String msg) {

        try {
            Statement stmt = con.createStatement();
            String bg = "('";
            String sp = "','";
            String ed = "')";
            String sorgu
                    = "insert into user_value values"
                    + bg
                    + "-1" + sp
                    + "" + sp
                    + String.valueOf(ID) + sp
                    + msg
                    + ed;
            System.out.println(sorgu);
            int ekleme = stmt.executeUpdate(sorgu);
            return true;
        } catch (Exception e) {
            ps.alert(e.getMessage());
        }
        return false;
    }

    public static boolean norQuery(String sql) {
        // Use TRUNCATE
        Statement stmt;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            ps.alert(ex.getMessage());

            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ps.alert(ex.getMessage());
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public static boolean blockUser(int ID) {

        try {
            Statement stmt = con.createStatement();
            String bg = "('";
            String sp = "','";
            String ed = "')";
            String sorgu
                    = "insert into user_value values"
                    + bg
                    + String.valueOf(ID) + sp
                    + "" + sp
                    + "-1" + sp
                    + ""
                    + ed;
            System.out.println(sorgu);
            int ekleme = stmt.executeUpdate(sorgu);
            return true;
        } catch (Exception e) {
            ps.setLabel("Xəta");
            ps.alert(e.getMessage());
        }
        return false;
    }

    public static boolean unWarUser(int ID) {
        String sql = "delete FROM user_value WHERE `war_id` = ?;";
        if (query(sql, ID)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean unblockUser(int ID) {
        String sql = "delete FROM user_value WHERE `block_id` = ?;";
        if (query(sql, ID)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean setPrefMsg(int ID, String value, int selectPref) {

        try {
            PreparedStatement ps = con.prepareStatement(prefMsgQueryPart1 + pref[selectPref] + prefMsgQueryPart2);
            ps.setString(1, value);
            ps.setInt(2, ID);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }
    }

    public static boolean querySettings(int ID, int value) {
        String data[] = {"regBonus", "BID", "updateStatus", "upServerStatus"};
        try {
            String q = "UPDATE settings SET " + data[ID] + "=?;";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1, value);
            pst.executeUpdate();
        } catch (Exception e) {
            ps.alert(e.getMessage());

            return false;
        }
        return true;
    }

    public static boolean querySettings(int ID, String value) {
        String data[] = {"pref055Def", "pref099Def", "bMsg", "msgFile", "updateUrl", "upServerValue"};
        try {
            String q = "UPDATE settings SET " + data[ID] + "=?;";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, value);
            pst.executeUpdate();
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean query(String q, int value) {
        try {
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1, value);
            pst.executeUpdate();
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean query(String q, String value) {
        try {
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, value);
            pst.executeUpdate();
        } catch (Exception e) {
            ps.alert(e.getMessage());

            return false;
        }
        return true;
    }

    public static boolean executeQuery(String q, int value) {
        try {
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1, value);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {
                return false;
            }

        } catch (Exception e) {

            ps.alert(e.getMessage());

        }

        return true;
    }

    public static boolean executeQuery(String q, String value) {
        try {
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return true;

            } else {
                return false;
            }

        } catch (Exception e) {

            ps.alert(e.getMessage());

        }
        return false;
    }

    public static boolean setCoin(int ID, int coin) {
        try {
            PreparedStatement pst = con.prepareStatement(coinSql);
            pst.setInt(1, coin);
            pst.setInt(2, ID);
            pst.executeUpdate();
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean setMaxMsg(int ID, int maxMsg) {
        try {
            PreparedStatement pst = con.prepareStatement(maxSendMsg);
            pst.setInt(1, maxMsg);
            pst.setInt(2, ID);
            pst.executeUpdate();
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }
        return true;
    }

    public int getMaxMsg(int ID) throws SQLException {
        String s = getDataString(13, "SELECT * FROM users WHERE id= " + String.valueOf(ID));
        return Integer.parseInt(s);
    }

    public boolean remove(String table, String column) {
        try {
            Statement stmt = con.createStatement();
            String sorgu = "ALTER TABLE " + table + "\n"
                    + "DROP COLUMN " + column + ";";
            System.out.println(sorgu);
            int ekleme = stmt.executeUpdate(sorgu);
            return true;
        } catch (Exception e) {
            ps.alert(e.getMessage());
            return false;
        }
    }

    public static String getDataString(int index, int id, String table) throws SQLException {
        String data = "";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + table + " WHERE `id` = " + String.valueOf(id));
        while (resultSet.next()) {
            data = resultSet.getString(index);
        }
        return data;
    }

    public int getDataInt(int index, int id, String table) throws SQLException {
        int data = 0;
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + table + " WHERE `id` = " + String.valueOf(id));
        while (resultSet.next()) {
            data = resultSet.getInt(index);
        }
        return data;
    }

    public static String getDataString(int index, String query1) throws SQLException {
        String data = "";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(query1);
        while (resultSet.next()) {
            data = resultSet.getString(index);
        }
        return data;
    }

    public static int getUserCount() throws SQLException {
        String data = getDataString(1, queryCount);
        return Integer.parseInt(data);
    }

    public int getID(String login) throws SQLException {
        int data = 0;
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(idQuery + "'" + login + "'");
        while (resultSet.next()) {
            data = resultSet.getInt(1);
        }
        return data;
    }

    public String getLogin(int id) throws SQLException {
        String idQuery = "SELECT * FROM users WHERE `id` = " + String.valueOf(id);
        String data = "";
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery(idQuery);
        while (resultSet.next()) {
            data = resultSet.getString(2);
        }
        return data;
    }

    public int getCoin(int ID) throws SQLException {
        int coins = getDataInt(8, ID, "users");
        return coins;
    }

    public boolean inHesabat(int senderID, int receiverID, int howmuch) {

        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf
                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);

        try {
            Statement stmt = con.createStatement();
            String bg = "('";
            String sp = "','";
            String ed = "')";
            String sorgu
                    = "insert into hesabat values"
                    + bg
                    + getLogin(senderID) + sp
                    + getLogin(receiverID) + sp
                    + String.valueOf(howmuch) + sp
                    + currentTime
                    + ed;
            System.out.println(sorgu);
            int ekleme = stmt.executeUpdate(sorgu);
            return true;
        } catch (Exception e) {
            //      access.alert(e.getMessage(), 1);
            ps.alert(e.getMessage());
        }
        return false;
    }

}
