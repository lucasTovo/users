package orion.users.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ValidateEmail {

    public static boolean validateMail(String email) {
        ValidateEmail demo = new ValidateEmail();

        
        boolean isValid = demo.validateEmail(email);
       return isValid;

        
    }

    private boolean validateEmail(String email) {
        boolean isValid = false;
        try {
            // Create InternetAddress object and validated the supplied
            // address which is this case is an email address.
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return isValid;
    }


}