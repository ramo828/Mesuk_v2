/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ramo828
 */
public class file {
    
    static boolean ex = false;
    private static  String URL = "";

    public static void setUrl(String sURL){
        URL = sURL;
    }
    
    
    public static File fileInit(){
                File f = new File(URL);
                return f;
    }
    
    
    public static void createFile(File f) {
// yani burada benim bilgisayara göre ; C:\Users\Buyukaptan\workspace\DosyaIslemleri konumuna göre işlem yapar.(Yol belirtmezsek)
        try {
            if (!f.exists()) { // eğer dosya yoksa
                f.createNewFile(); // dosyamızı oluşturur.
                System.out.println(f.getName() + " adda fayl yaradildi..");
            } else {
                ex = true;
                System.out.println("file artiq var");
            }
        } catch (IOException e) { // hata yakalama
// TODO: handle exception
            e.printStackTrace();
        }
    }

    public static boolean existFile(File f){
        return f.exists();
    }
    
    
    public static void removeFile(File f) {
        if (!f.exists()) { // eğer dosya yoksa
            System.out.println("Fayl olmadigi ucun silinmedi");
        } else {
            f.delete(); // eğer dosyamız varsa.. // silme işlemi gerçekleştirir.
            System.out.println(f.getName() + " adlı fayl silindi");
        }
    }

   

    public static void writeFile(File file, String value) throws FileNotFoundException, IOException {
// Dosyaya yazmak için nesnemizi oluşturuyoruz.
        FileOutputStream fos = new FileOutputStream(file);
// Ekleyeceğimiz yazıyı yazıyoruz.
//Yazma işlemini gerçekleştiriyoruz.
        fos.write(value.getBytes());
        fos.flush();
// İşlemimiz bitince kaynaklarımızı kapatıyoruz. Boşuna sistemde hazır beklemesinler.
        fos.close();

    }

    public static String readFileLine(File file) {
        try {
            Scanner scanner = new Scanner(file);
// while(scanner.hasNext()){ // kelime kelime okur. boşluklar silinir.
// System.out.print(scanner.next());
// }

            while (scanner.hasNextLine()) {
                return scanner.nextLine(); 
            }
            scanner.close();
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Fayl tapilmadi..");
        }
        return null;
    }

    public static String getDir() {
        String currentDir = System.getProperty("user.dir");
        return currentDir;
    }

    public static String getDirV2() throws IOException {
        String currentPath = new java.io.File(".").getCanonicalPath();
        return currentPath;
    }
}
