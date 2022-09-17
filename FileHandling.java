package package4;

import java.io.*;
import java.util.ArrayList;
public class FileHandling {
    protected File userdatafile;    // FILE OBJECT TO OPEN THE USERS DATA //
    protected BufferedReader userdatafilereader;    // READER TO DOWNLOAD USER DATA //
    protected FileOutputStream userdatafileoutputwriter; // WRITER OBJECT TO REWRITE THE NEW USERS DATA //
    protected BufferedWriter userdatawriter;
    String Filepath;
    protected ArrayList<String> val_email; // USERS EMAIL LIST //
    protected ArrayList<String> val_password;  // USERS PASSWORD LIST //

    protected FileHandling(String Filepath) throws FileNotFoundException {
        this.Filepath = Filepath;
        userdatafile = new File(this.Filepath);
        userdatafilereader = new BufferedReader(new FileReader(this.Filepath));

        val_email = new ArrayList<String>();
        val_password = new ArrayList<String>();
    }

    public boolean CheckOpenFile() {
        try {
            return ( userdatafile.exists()  // CHECKING FOR THE FUNCTIONALLITY OF THIS FILE //
                    && userdatafile.canWrite()
                    && userdatafile.canRead() );

        } catch (Exception e) {
            System.out.println("Error in CHECKOPENFILE");
            return false;
        }

    }

    public boolean readUserDataFromFile() { // READ THE TEXT FILE LINE BY LINE TO GET EMAILS AND PASSWORDS //
        try {
                int i = 0;

                val_email.add(userdatafilereader.readLine());
                val_password.add(userdatafilereader.readLine());
                while ( val_password.get(i) != null ) {
                    val_email.add(userdatafilereader.readLine());
                    val_password.add(userdatafilereader.readLine());

                    i++;
                }

                val_email.remove(val_email.size() - 1);
                val_password.remove(val_password.size() - 1);

                return true;
        }

        catch (IOException e) {
            System.out.println("IO EXCEPTION IN READING FILE");
                return false;
        }

        catch (Exception e) {
            System.out.println("EXCEPTION IN READING FILE");
                return false;
        }
    }

    protected void reSaveUsersData() throws IOException { // RE-WRITING LINE BY LINE THE USERS DATA //
            userdatafileoutputwriter = new FileOutputStream(userdatafile , false);
            userdatawriter = new BufferedWriter(new OutputStreamWriter(userdatafileoutputwriter));

            for (int i = 0; ((i < val_email.size()) && (val_email.size() == val_password.size())); ++i) {
                userdatawriter.write(val_email.get(i));
                userdatawriter.newLine();
                userdatawriter.write(val_password.get(i));
                userdatawriter.newLine();
            }

            userdatawriter.close();
            userdatafileoutputwriter.close();
    }
}
