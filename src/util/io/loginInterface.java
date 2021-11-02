/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.io;

/**
 *
 * @author ramo828
 */
public interface loginInterface {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *

/**
 *
 * @author ramo828
*/
    public static String userTable = "users";
    public static String statusMsg = "Sizin statusunuz aktiv deyil";
    public static String statusLabel[] = {"Aktiv", "Passiv", "Error"};
    public static String ErrorMsgLabel = "Xəta";
    public static String author = "Bu program Memmedli Ramiz terefinden yaradilib";
    public static String accessDeniedMsg = "Login və ya Şifrə yalnışdır!";
    public static String connectStatusMsg[] = {"Connected", "Disconnected"};

    /*
        Qeydiyyat hissesi
     */
    public static String nullLoginMsg = "Login boş buraxıldı!";
    public static String nullPassMsg = "Parol boş buraxıldı!";
    public static String nullNameLogin = "Ad boş buraxıldı!";
    public static String nullSurnameMsg = "Soyad boş buraxıldı!";
    public static String nullEmailMsg = "Email boş buraxıldı!";
    public static String nullNumberMsg = "Nomrə boş buraxıldı!";
    public static String LongLoginMsg = "Login bu qədər böyük ola bilməz!";
    public static String LongPassMsg = "Parol bu qədər böyük ola bilməz!";
    public static String LongNameLogin = "Ad bu qədər böyük ola bilməz!";
    public static String LongSurnameMsg = "Soyad bu qədər böyük ola bilməz!";
    public static String LongEmailMsg = "Email bu qədər böyük ola bilməz!";
    public static String LongNumberMsg = "Nomrə bu qədər böyük ola bilməz!";
    public static String wrongEmail = "Email Xətalı daxil edildi!";
    public static String wrongNumber = "Nömrə Xətalı daxil edildi\n Ölkə kodunu doğru yazdığınızdan əmin olun!";
    public static String limitPass = "Parol 8 simvoldan kiçik ola bilməz!";
    public static String existLogin = "Bu login artıq istifadə edilir!";
    public static String existEmail = "Bu email artıq istifadə edilir!";
    public static String tryPassError = "Şifrələr uyğun deyil";
    public static String RefisterSuccessMsg = "Qeydiyyat olundu!";
    public static String RefisterUnsuccessMsg = "Qeydiyyat ugursuz oldu!";
    
    public static int loginVar = 0;
    public static int emailVar = 1;

}
                                           

