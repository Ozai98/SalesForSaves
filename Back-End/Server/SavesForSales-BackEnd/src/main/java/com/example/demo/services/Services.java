
package services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author German le yo
 */
public class Services {

    private static MessageDigest md;
    private static Pattern emailPattern;

    public static void startServices(){
        try{
            md = MessageDigest.getInstance("SHA-256");
        } catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        
    }

    public static String cryptPassword(String password){
        byte[] crypt = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.encodeBase64(crypt));
    }

    public static void handleError(Exception ex){
        ex.printStackTrace();
    }

    public static boolean validateEmail(String email){
        return emailPattern.matcher(email).matches();
    }

} 