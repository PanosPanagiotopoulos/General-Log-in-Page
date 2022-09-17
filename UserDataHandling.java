package package4;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class UserDataHandling {
    protected FileHandling usersData;
    protected String Email = "" , PassWord = "" , RetypedPassword = "";
    private String FilePath = "";
    private final byte  MAX_PASSWORD_LENGTH = 15 , MINIMUM_PASSWORD = 5;
    private byte MINIMUM_ACCEPTABLE_MATCH = 5;
    protected ArrayList<String> val_email;
    protected ArrayList<String> val_password;
    private boolean flag_continue = true;

    protected UserDataHandling() throws FileNotFoundException {
        // LOADING USERS DATA LISTS //
        val_email = new ArrayList<String>();
        val_password = new ArrayList<String>();

        FilePath = ( "Log-In-Page\\usersData.txt" );
        // JUST FOR TESTING PURPOSES IT WILL CHANGE DEPENDING THE
        usersData = new FileHandling(FilePath);

         try {
             if ( !(usersData.CheckOpenFile()) ) {
                 System.out.println("Can't Process File");
                 flag_continue = false;
             }

             if (flag_continue) {
                 System.out.println("Readed: " + usersData.readUserDataFromFile());

                 val_email = usersData.val_email;
                 val_password = usersData.val_password;

                 System.out.println(val_email.size() + " " + val_password.size());

                 for (int i = 0; ((i < val_email.size()) && (val_email.size() == val_password.size())); ++i) {
                     System.out.println(val_email.get(i) + " --> " + val_password.get(i));
                 }
             }
         }

        catch (StackOverflowError e) {
            System.out.println("StackOverFlow");
        }

        catch (Exception e) {
            System.out.println("Exception In Constructor Of UserDataHandling");
        }
    }

    protected String IsDataValid() {

        for (int i = 0 ; ( (i < val_email.size()) && (val_email.size() == val_password.size()) ) ; i++) {
            if ( Email.equals(val_email.get(i)) ) {
                if ( PassWord.equals(val_password.get(i)) ) { return "TRUE-BOTH"; }

                else { return "FALSE-PASSWORD-CORRECT-EMAIL"; }
            }
        }

        return "FALSE-EMAIL";
    }

    protected boolean EmailExists() {
        for (int i = 0 ; (i < val_email.size()) ; ++i) {
            if (Email.equals(val_email.get(i))) { return true; }
        }

        return false;
    }

    protected boolean emailIsValid() {
        char[] email = Email.toCharArray();
        // flase if a domain wasn't typed // // false if it doesn't end with lowercase letters //
        byte domain_position = -1 , j = 0;

        if (email.length < 5) { return false; }

        if ( !(Character.isAlphabetic(email[0])) ) { return false; }

        for (int i = 1 ; (i < email.length) ; ++i) {
            if (email[i] == '@') { domain_position = (byte) i;
                break; }
        }

        if (domain_position == -1) { return false; }

        if ( !(Character.isLowerCase(email[domain_position + 1])) ) { return false; }

        for (j = (byte) (domain_position + 2) ; (j < email.length && email[j] != '.') ; ++j) {}

        if (j == email.length) { return false; }

        if ( !(Character.isLowerCase(email[j + 1])) ) { return false; }


        return true;
    }

    protected boolean PasswordCloseToValid() {
        char[] user_password = PassWord.toCharArray();   // Password variable //
        byte char_cnt = 0 , min_lengh = MINIMUM_PASSWORD;
        int password_place = 0;

        for (int i = 0 ; (i < val_email.size()) ; i++) {
            if ( Email.equals(val_email.get(i)) ) {
                password_place = i;
                break;
            }
        }

        char[] valid_passwords_temp = val_password.get(password_place).toCharArray();

        min_lengh = (valid_passwords_temp.length < user_password.length)
                ? (byte) valid_passwords_temp.length : (byte) user_password.length;

        for (int i = 0; (i < min_lengh); ++i) {
            if (valid_passwords_temp[i] == user_password[i])
                char_cnt++;

            else break;

        }

        MINIMUM_ACCEPTABLE_MATCH = (byte) (valid_passwords_temp.length - 2);

        if (char_cnt >= MINIMUM_ACCEPTABLE_MATCH
                && user_password.length < MAX_PASSWORD_LENGTH)
        { return true; }

        return false;
    }
    protected boolean NewPasswordIsValid() {
        char[] newpass = PassWord.toCharArray();
        byte i = 0 , cnt_dig = 0 , cnt_upper = 0 , cnt_other = 0;

        for (; (i < newpass.length) ; i++) {
            if (Character.isUpperCase(newpass[i])) { cnt_upper++; }
            else if (Character.isDigit(newpass[i])) { cnt_dig++; }
            else if (Character.isLowerCase(newpass[i])) {}
            else  { cnt_other++; }
        }

        return ( (newpass.length >= MINIMUM_PASSWORD) &&
                (cnt_dig >= 1) && (cnt_upper >= 1) && (cnt_other >= 1) );
    }

    protected boolean PasswordsMatch() {
        return ( PassWord.equals(RetypedPassword) );
    }

    protected boolean emailAlreadyExists() {
        for (int i = 0 ; ( i < val_email.size() ) ; ++i) {
            if ( val_email.get(i).equals(Email) ) {
                return true;
            }
        }

        return false;
    }
    protected void saveNewAccount() {
        val_email.add(Email);
        val_password.add(PassWord);
    }

    protected void ChangeCurrentEmailPass() {
       int CurrentPosition = 0;

        for (int i = 0 ; ( (i < val_email.size()) && (val_email.size() == val_password.size()) ) ; i++) {
            if (Email.equals(val_email.get(i))) {
                CurrentPosition = i;
                   break;
            }
        }

        val_password.remove(CurrentPosition);
        val_password.add(CurrentPosition , PassWord);

    }

}
