/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PFE.Utils;

import java.util.regex.Pattern;

/**
 *
 * @author Tryvl
 */
public class Validator {

    public Validator() {
    }

    public boolean validEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public String cryptPassword(String password) 
    {
        String newPassword ="";
        for (int i = 0; i < password.length(); i++) 
        {
            newPassword += new String(new char[]{(char) (password.charAt(i) + 60)});
        }
        System.out.println("new password = " + newPassword);
        System.out.println("final password = " +"$2yspfe$"+ newPassword);
        return "$2yspfe$" + newPassword;
        
    }

}
