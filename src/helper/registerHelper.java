/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import database.Db;
import java.sql.SQLException;
import util.io.parentSelector;

/**
 *
 * @author ramo828
 */
public class registerHelper implements database.IDatabase, database.IQueries {

    private String login;
    private String pass;
    private String number;
    private String email;
    private String name;
    private String surname;
    private String sex;

    parentSelector ps = new parentSelector();

    public registerHelper(String login, String pass, String number, String email, String name, String surname, String sex) {
        this.login = login;
        this.pass = pass;
        this.number = number;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
    }

    public boolean run() throws SQLException {
        Db d = new Db();
        d.init();
        int regBonus = Integer.parseInt(d.getDataString(1, settingQuery));

        if (d.newUser(
                login.toLowerCase(),
                pass,
                number,
                email,
                name,
                surname,
                regBonus,
                0,
                "",
                "",
                0,
                0,
                sex)) {
            return true;
        } else {
            return false;
        }
    }

}
