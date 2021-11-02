/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author ramo828
 */
public interface IQueries {

    public static String allUser = "SELECT * FROM users"; //istifadeci melumatlari
    String prefMsgQueryPart1 = "UPDATE users SET ";
    String prefMsgQueryPart2 = " = ? WHERE id = ?;";
    String pref[] = {"prefix055", "prefix099"};
    String coinSql = "UPDATE users SET coin =? WHERE id=?;";
    String maxSendMsg = "UPDATE users SET maxSendMsg =? WHERE id=?;";
    String queryCount = "select count(*) from users;";
    String idQuery = "SELECT * FROM `users` WHERE `login` = ";
    public String settingQuery = "SELECT * FROM `settings`";
    String loginSql = "Select * from users where login=?";
    String emailSql = "Select * from users where email=?";
    String idSql = "Select * from user_value where block_id=?";

}
