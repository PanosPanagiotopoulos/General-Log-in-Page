package package4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LogInPage implements ActionListener {
    private ArrayList<JFrame> Pages;
    private JLabel HomeLabel1 , HomeLabel2 , HomeLabel3 , LoggedInLabel1 , WrongEmailLabel1 , WrongPasswordLabel1 , ForgetPassLabel1 ,
            ForgetPassLabel2 , ForgetPassLabel3 , ForgetPassLabel4 , ForgetPassLabel5 , ForgetPassLabel6 , CreatePassLabel13 , CreatePassLabel14 ,
         CreatePassLabel15 , CreatePassLabel16 , createAccNotValidDataLabel , accountCreatedLabel , create_acc_instruct_label , email_not_exist , image_label1 , image_label2 , image_label3 ,
                        image_label4 , image_label5 , image_label6;
    private ArrayList<JPanel> Panels;

    private JButton log_button , forgot_button , forgot_pass_email_button
            , WrongEmailBackButton , WrongPasswordBackButton , forgot_back_button , createAccSingUpButton ,
            createAccButton , createAccBackButton , PasswordCanNotBeChangedReturnButton , PasswordChangedReturnButton , AccountCreatedReturnButton;
    private TextField email , forgot_pass_email , password , new_pass_field , retype_new_pass_field ,
                        create_acc_email , create_acc_password , create_acc_retpassword;

    protected UserDataHandling userDataHandling;

    private byte PageCounter , forgotbuttonaction , try_cnt = 2;
    // PAGE COUNTER TO SWAP OPENING THE PAGES // VARIABLE TO DETERMINE THE ACTION OF THE BUTTON IN FORGET PASSWORD PAGE //
    // COUNTER FOR THE TRIES ALLOWED TO RETRIEVE PASSWORD //
    private final byte numPages = 6;    // NUMBER OF PAGES NEEDED //
    private boolean flag_add = false , dataFlagAdd = false;
        // FLAG TO ADD ONLY ONCE THE DATA IN THE PANELS // TO CREATE A USERDATA OBJECT ONCE //

    protected LogInPage() {
        CreatePageData();
        CreateHomeLogInPage();
        CreateLoggedInPage();
        CreateWrongEmailPage();
        CreateWrongPasswordPage();
        CreateForgetPassWordPage();
        CreateAccountPage();
        SumAndStart();
    }

    private void CreatePageData() {
        // MAIN PAGE FRAMES //
        Pages = new ArrayList<JFrame>();
        for (int i = 0 ; (i < numPages) ; i++)
            Pages.add(new JFrame());


        // PAGE LABELS //
        HomeLabel1 = new JLabel();
        HomeLabel2 = new JLabel();
        HomeLabel3 = new JLabel();
        LoggedInLabel1 = new JLabel();
        WrongEmailLabel1 = new JLabel();
        WrongPasswordLabel1 = new JLabel();
        ForgetPassLabel1 = new JLabel();
        ForgetPassLabel2 = new JLabel();
        ForgetPassLabel3 = new JLabel();
        ForgetPassLabel4 = new JLabel();
        ForgetPassLabel5 = new JLabel();
        ForgetPassLabel6 = new JLabel();
        CreatePassLabel13 = new JLabel();
        CreatePassLabel14 = new JLabel();
        CreatePassLabel15 = new JLabel();
        CreatePassLabel16 = new JLabel();
        createAccNotValidDataLabel = new JLabel();
        accountCreatedLabel = new JLabel();
        create_acc_instruct_label = new JLabel();
        email_not_exist = new JLabel();

        // IMAGE //
        image_label1 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png"));
        image_label2 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png"));
        image_label3 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png"));
        image_label4 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png"));
        image_label5 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png"));
        image_label6 = new JLabel(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png"));


        // PAGE PANELS //
        Panels = new ArrayList<JPanel>();
        for (int i = 0 ; (i < numPages) ; i++)
            Panels.add(new JPanel());

        // TEXTFIELDS //
        email = new TextField();
        forgot_pass_email = new TextField();
        password = new TextField();
        new_pass_field = new TextField();
        retype_new_pass_field = new TextField();
        create_acc_email = new TextField();
        create_acc_password = new TextField();
        create_acc_retpassword = new TextField();

        // BUTTONS //
        log_button = new JButton();
        forgot_button = new JButton();
        forgot_pass_email_button = new JButton();
        WrongEmailBackButton = new JButton();
        WrongPasswordBackButton = new JButton();
        PasswordCanNotBeChangedReturnButton = new JButton();
        PasswordChangedReturnButton = new JButton();
        AccountCreatedReturnButton = new JButton();
        forgot_back_button = new JButton(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\back_arrow.png"));
        createAccButton = new JButton();
        createAccSingUpButton = new JButton();
        createAccBackButton = new JButton(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\back_arrow.png"));
        forgotbuttonaction = 1;


        // PAGE COUNTER //
        PageCounter = 0;

        // OBJECT FOR USER DATA HANDLING METHODS , FILES AND VARIABLES //
        if ( !(dataFlagAdd) ) {
            dataFlagAdd = true;
            try {
                userDataHandling = new UserDataHandling();
            } catch (FileNotFoundException e) {
                dataFlagAdd = false;
                System.out.println("FileNotFoundException In Declaring UserDataHandling Object");
            }
        }


        for (int i = 0 ; (i < numPages) ; i++) {
            // FRAMES //
            Pages.get(i).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            Pages.get(i).getContentPane().setBackground(Color.DARK_GRAY);
            Pages.get(i).setSize(700,700);
            Pages.get(i).setLayout(null);
            Pages.get(i).setResizable(false);
            Pages.get(i).addWindowListener(new  WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {  // SAVING DATA AFTER CLOSING //
                    try {
                        userDataHandling.usersData.reSaveUsersData();
                    } catch (IOException o) {
                        System.out.println("Can't write data to .txt (IOException)");;
                    }
                    for (int i = 0 ; (i < Pages.size()) ; i++) {
                        Pages.get(i).dispose();
                    }
                     Pages.clear();
                    System.exit(0);
                    super.windowClosing(e);
                    //e.getWindow().dispose();
                }
            });

            // PANEL //
            Panels.get(i).setBounds(62,62,550,500);
            Panels.get(i).setBackground(Color.LIGHT_GRAY);
            Panels.get(i).setBorder(BorderFactory.createLineBorder(Color.black , 4));
            Panels.get(i).setLayout(null);
        }

        Pages.get(0).setTitle("Log-In");
        Pages.get(1).setTitle("Signing in..");
        Pages.get(2).setTitle("Wrong Email");
        Pages.get(3).setTitle("Wrong Password");
        Pages.get(4).setTitle("Forgot Password");
        Pages.get(5).setTitle("Create An Account");

        Pages.get(0).setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png").getImage());
        Pages.get(1).setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png").getImage());
        Pages.get(2).setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png").getImage());
        Pages.get(3).setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png").getImage());
        Pages.get(4).setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png").getImage());
        Pages.get(5).setIconImage(new ImageIcon("C:\\Prog_Java\\Testing\\Package4_Files\\loglogo.png").getImage());


    }

    private void CreateHomeLogInPage() {
        // IMAGE //
        image_label1.setBounds(215,20,110,97);

        // LABELS //
        HomeLabel1.setBackground(Color.LIGHT_GRAY);
        HomeLabel1.setBounds(170,140,80,30);
        HomeLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));
        HomeLabel1.setText("Email");

        HomeLabel2.setBackground(Color.LIGHT_GRAY);
        HomeLabel2.setBounds(170,230,100,15);
        HomeLabel2.setFont(new Font("Serif" , Font.PLAIN , 21));
        HomeLabel2.setText("Password");

        // TEXT-FIELDS //
        email.setBounds(170 , 172 ,240 , 22);
        email.setEditable(true);
        email.setFont(new Font("Serif" , Font.PLAIN , 15));
        email.setBackground(Color.LIGHT_GRAY);

        password.setBounds(170 , 255 ,240 , 22);
        password.setEditable(true);
        password.setFont(new Font("Serif" , Font.PLAIN , 15));
        password.setBackground(Color.LIGHT_GRAY);


        // BUTTONS //
        log_button.addActionListener(this);
        log_button.setFocusable(false);
        log_button.setBounds(191 , 312 , 150,26);
        log_button.setText("Log-In");
        log_button.setFont(new Font("Serif" , Font.PLAIN , 15));
        log_button.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        log_button.setBackground(Color.white);

        forgot_button.addActionListener(this);
        forgot_button.setFocusable(false);
        forgot_button.setBounds(200 , 357 , 130,26);
        forgot_button.setText("Forgot Password?");
        forgot_button.setFont(new Font("Serif" , Font.PLAIN , 15));
        forgot_button.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        forgot_button.setBackground(Color.white);

        createAccButton.addActionListener(this);
        createAccButton.setFocusable(false);
        createAccButton.setBounds(311 , 442 , 170,28);
        createAccButton.setText("Create Account");
        createAccButton.setFont(new Font("Serif" , Font.PLAIN , 15));
        createAccButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        createAccButton.setBackground(Color.white);
        createAccButton.setVisible(true);
        createAccButton.setEnabled(true);


        // SUM-UP //
            Panels.get(0).add(HomeLabel1);
            Panels.get(0).add(HomeLabel2);
            Panels.get(0).add(HomeLabel3);
            Panels.get(0).add(image_label1);
            Panels.get(0).add(email);
            Panels.get(0).add(password);
            Panels.get(0).add(log_button);
            Panels.get(0).add(forgot_button);
            Panels.get(0).add(createAccButton);
    }
    private void CreateLoggedInPage() {
        // IMAGE //
        image_label2.setBounds(215,20,110,97);

        // LABEL //
        LoggedInLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));
        LoggedInLabel1.setText("  We Are Now Signing You In ...  ");
        LoggedInLabel1.setBounds(125,190,300,52);
        LoggedInLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));

        // SUM UP //
        Panels.get(1).add(image_label2);
        Panels.get(1).add(LoggedInLabel1);

    }

    private void CreateWrongEmailPage() {
        // IMAGE //
        image_label3.setBounds(215,20,110,97);

        // LABEL //
        WrongEmailLabel1.setText("  The Email Is Incorrect  ");
        WrongEmailLabel1.setBounds(155,190,230,52);
        WrongEmailLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
        WrongEmailLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));

        // BACK BUTTON //
        WrongEmailBackButton.addActionListener(this);
        WrongEmailBackButton.setFocusable(false);
        WrongEmailBackButton.setBounds(192 , 310 , 150,35);
        WrongEmailBackButton.setText("Try Again");
        WrongEmailBackButton.setFont(new Font("Serif" , Font.PLAIN , 15));
        WrongEmailBackButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        WrongEmailBackButton.setBackground(Color.white);

        // SUM UP //
        Panels.get(2).add(image_label3);
        Panels.get(2).add(WrongEmailLabel1);
        Panels.get(2).add(WrongEmailBackButton);

    }

    private void CreateWrongPasswordPage() {
        // IMAGE //
        image_label4.setBounds(215,20,110,97);

        // LABEL //
        WrongPasswordLabel1.setText("  The Password Is Incorrect ");
        WrongPasswordLabel1.setBounds(145,190,250,52);
        WrongPasswordLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
        WrongPasswordLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));

        // BACK BUTTON //
        WrongPasswordBackButton.addActionListener(this);
        WrongPasswordBackButton.setFocusable(false);
        WrongPasswordBackButton.setBounds(192 , 310 , 150,35);
        WrongPasswordBackButton.setText("Try Again");
        WrongPasswordBackButton.setFont(new Font("Serif" , Font.PLAIN , 15));
        WrongPasswordBackButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        WrongPasswordBackButton.setBackground(Color.white);

        // SUM UP //
        Panels.get(3).add(WrongPasswordLabel1);
        Panels.get(3).add(WrongPasswordBackButton);
        Panels.get(3).add(image_label4);
    }

    private void CreateForgetPassWordPage() {
        // IMAGE //
        image_label5.setBounds(215,20,110,97);

        // LABEL //
        ForgetPassLabel1.setText("   Type Your Email  ");
        ForgetPassLabel1.setBounds(174,140,181,52);
        ForgetPassLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
        ForgetPassLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));

        ForgetPassLabel2.setBackground(Color.LIGHT_GRAY);
        ForgetPassLabel2.setBounds(150,225,80,30);
        ForgetPassLabel2.setFont(new Font("Serif" , Font.PLAIN , 21));
        ForgetPassLabel2.setText("Email");

        ForgetPassLabel3.setVisible(false);
        ForgetPassLabel4.setVisible(false);
        ForgetPassLabel5.setVisible(false);
        ForgetPassLabel6.setVisible(false);


        email_not_exist.setBackground(Color.LIGHT_GRAY);
        email_not_exist.setBounds(145 , 282,300,20);
        email_not_exist.setFont(new Font("Serif" , Font.PLAIN , 15));
        email_not_exist.setText(" Email doesn't exist. Please Try again. ");
        email_not_exist.setVisible(false);

        // TEXT-FIELDS //
        forgot_pass_email.setBounds(150 , 257 ,240 , 22);
        forgot_pass_email.setFont(new Font("Serif" , Font.PLAIN , 15));
        forgot_pass_email.setBackground(Color.LIGHT_GRAY);
        forgot_pass_email.setText(null);
        forgot_pass_email.setEditable(true);
        forgot_pass_email.setVisible(true);

        if (!flag_add) { // THESE WILL ONLY BE DONE ONCE EVERY FORGET BUTTON //
            new_pass_field.setVisible(false);
            new_pass_field.setEditable(false);
            retype_new_pass_field.setVisible(false);
            retype_new_pass_field.setEditable(false);

            // FORGOT PASSWORD EMAIL BUTTON //
            forgot_pass_email_button.addActionListener(this);
            forgot_pass_email_button.setFocusable(false);
            forgot_pass_email_button.setBounds(205, 315, 130, 35);
            forgot_pass_email_button.setText("Next");
            forgot_pass_email_button.setFont(new Font("Serif", Font.PLAIN, 15));
            forgot_pass_email_button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            forgot_pass_email_button.setBackground(Color.white);
            forgot_pass_email_button.setVisible(true);
            forgot_pass_email_button.setEnabled(true);

            PasswordCanNotBeChangedReturnButton.addActionListener(this);
            PasswordCanNotBeChangedReturnButton.setFocusable(false);
            PasswordCanNotBeChangedReturnButton.setBackground(Color.white);
            PasswordCanNotBeChangedReturnButton.setVisible(false);
            PasswordCanNotBeChangedReturnButton.setEnabled(false);

            PasswordChangedReturnButton.setVisible(false);
            PasswordChangedReturnButton.setEnabled(false);


            forgot_back_button.addActionListener(this);
            forgot_back_button.setFocusable(false);
            forgot_back_button.setBounds(20, 35, 40, 27);
            forgot_back_button.setFont(new Font("Serif Bold", Font.PLAIN, 19));
            forgot_back_button.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            forgot_back_button.setBackground(Color.white);
            forgot_back_button.setVisible(true);
            forgot_back_button.setEnabled(true);


            // SUM UP //
            // LABELS + IMAGE //
            Panels.get(4).add(image_label5);
            Panels.get(4).add(ForgetPassLabel1);
            Panels.get(4).add(ForgetPassLabel2);
            Panels.get(4).add(ForgetPassLabel3);
            Panels.get(4).add(ForgetPassLabel4);
            Panels.get(4).add(ForgetPassLabel5);
            Panels.get(4).add(ForgetPassLabel6);
            Panels.get(4).add(email_not_exist);
            // TEXTFIELDS //
            Panels.get(4).add(forgot_pass_email);
            Panels.get(4).add(new_pass_field);
            Panels.get(4).add(retype_new_pass_field);
            // BUTTONS //
            Panels.get(4).add(forgot_pass_email_button);
            Panels.get(4).add(PasswordCanNotBeChangedReturnButton);
            Panels.get(4).add(PasswordChangedReturnButton);
            Panels.get(4).add(forgot_back_button);

            flag_add = true;
        }
    }

    private void CreateAccountPage() {
        // IMAGE //
        image_label6.setBounds(215,20,110,97);

        // LABELS //
        CreatePassLabel13.setBackground(Color.LIGHT_GRAY);
        CreatePassLabel13.setBounds(150,235,140,30);
        CreatePassLabel13.setFont(new Font("Serif" , Font.PLAIN , 21));
        CreatePassLabel13.setText("New Email");
        CreatePassLabel13.setVisible(true);

        CreatePassLabel14.setBackground(Color.LIGHT_GRAY);
        CreatePassLabel14.setBounds(150,314,140,15);
        CreatePassLabel14.setFont(new Font("Serif" , Font.PLAIN , 21));
        CreatePassLabel14.setText("New Password");
        CreatePassLabel14.setVisible(true);

        CreatePassLabel15.setBackground(Color.LIGHT_GRAY);
        CreatePassLabel15.setBounds(150,385,210,17);
        CreatePassLabel15.setFont(new Font("Serif" , Font.PLAIN , 21));
        CreatePassLabel15.setText("Retype New Password");
        CreatePassLabel15.setVisible(true);

        CreatePassLabel16.setBackground(Color.LIGHT_GRAY);
        CreatePassLabel16.setBounds(110,139,320,50);
        CreatePassLabel16.setFont(new Font("Serif" , Font.PLAIN , 24));
        CreatePassLabel16.setText("       Create A New Account  ");
        CreatePassLabel16.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
        CreatePassLabel16.setVisible(true);

        create_acc_instruct_label.setBackground(Color.LIGHT_GRAY);
        create_acc_instruct_label.setBounds(25,205,500,22);
        create_acc_instruct_label.setFont(new Font("Serif" , Font.PLAIN , 15));
        create_acc_instruct_label.setText("* You Will Need At Least : 1 Digit , 1 Upper Case Letter and 1 Pumctuation Mark *");
        create_acc_instruct_label.setVisible(true);


        createAccNotValidDataLabel.setBackground(Color.LIGHT_GRAY);
        createAccNotValidDataLabel.setFont(new Font("Serif" , Font.PLAIN , 15));
        createAccNotValidDataLabel.setVisible(false);

        // TEXT-FIELDS //
        create_acc_email.setBounds(150, 267 ,240 , 22);
        create_acc_email.setEditable(true);
        create_acc_email.setFont(new Font("Serif" , Font.PLAIN , 15));
        create_acc_email.setBackground(Color.LIGHT_GRAY);
        create_acc_email.setVisible(true);
        create_acc_email.setEditable(true);

        create_acc_password.setBounds(150 , 337 ,240 , 22);
        create_acc_password.setEditable(true);
        create_acc_password.setFont(new Font("Serif" , Font.PLAIN , 15));
        create_acc_password.setBackground(Color.LIGHT_GRAY);
        create_acc_password.setVisible(true);
        create_acc_password.setEditable(true);

        create_acc_retpassword.setBounds(150 , 410 ,240 , 22);
        create_acc_retpassword.setEditable(true);
        create_acc_retpassword.setFont(new Font("Serif" , Font.PLAIN , 15));
        create_acc_retpassword.setBackground(Color.LIGHT_GRAY);
        create_acc_retpassword.setVisible(true);
        create_acc_retpassword.setEditable(true);

        // BUTTON //
        createAccSingUpButton.addActionListener(this);
        createAccSingUpButton.setFocusable(false);
        createAccSingUpButton.setBounds(191 , 454 , 125,32);
        createAccSingUpButton.setText("Sing Up");
        createAccSingUpButton.setFont(new Font("Serif" , Font.PLAIN , 15));
        createAccSingUpButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        createAccSingUpButton.setBackground(Color.white);

        createAccBackButton.addActionListener(this);
        createAccBackButton.setFocusable(false);
        createAccBackButton.setBounds(20, 35, 40, 27);
        createAccBackButton.setFont(new Font("Serif Bold", Font.PLAIN, 19));
        createAccBackButton.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        createAccBackButton.setBackground(Color.white);
        createAccBackButton.setVisible(true);
        createAccBackButton.setEnabled(true);

        AccountCreatedReturnButton.addActionListener(this);
        AccountCreatedReturnButton.setFocusable(false);
        AccountCreatedReturnButton.setText("Return To Home Page");
        AccountCreatedReturnButton.setBounds(180,265 , 170 , 60);
        AccountCreatedReturnButton.setFont(new Font("Serif" , Font.PLAIN , 17));
        AccountCreatedReturnButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        AccountCreatedReturnButton.setBackground(Color.white);
        AccountCreatedReturnButton.setVisible(false);
        AccountCreatedReturnButton.setEnabled(false);

        // SUM UP //
        Panels.get(5).add(image_label6);
        Panels.get(5).add(CreatePassLabel13);
        Panels.get(5).add(CreatePassLabel14);
        Panels.get(5).add(CreatePassLabel15);
        Panels.get(5).add(CreatePassLabel16);
        Panels.get(5).add(createAccNotValidDataLabel);
        Panels.get(5).add(accountCreatedLabel);
        Panels.get(5).add(create_acc_instruct_label);
        Panels.get(5).add(create_acc_email);
        Panels.get(5).add(create_acc_password);
        Panels.get(5).add(create_acc_retpassword);
        Panels.get(5).add(createAccSingUpButton);
        Panels.get(5).add(createAccBackButton);
        Panels.get(5).add(AccountCreatedReturnButton);
    }

    private void SumAndStart() {
        for (int i = 0 ; (i < numPages) ; ++i)
            Pages.get(i).add(Panels.get(i));

        Pages.get(0).setVisible(true);
    }

    private void OpenNewWindow() {
        Pages.get(PageCounter).setVisible(true);
    }
    private void CloseLastWindow() {
        Pages.get(PageCounter).setVisible(false);
    }

    private void Create2ndStageForgetPasswordPage() {
        // LABEL //
        ForgetPassLabel1.setText("   Type The Last Password You Remember  ");
        ForgetPassLabel1.setBounds(86, 140, 381, 52);
        ForgetPassLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
        ForgetPassLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));
        ForgetPassLabel1.setVisible(true);


        ForgetPassLabel2.setBounds(150,225,100,30);
        ForgetPassLabel2.setText("Password");
        ForgetPassLabel2.setFont(new Font("Serif" , Font.PLAIN , 21));
        ForgetPassLabel2.setVisible(true);

        ForgetPassLabel3.setVisible(false);
        ForgetPassLabel4.setVisible(false);
        ForgetPassLabel5.setVisible(false);
        ForgetPassLabel6.setVisible(false);
        email_not_exist.setVisible(false);

        // TEXTFIELD //
        forgot_pass_email.setText(null);
        forgot_pass_email.setVisible(true);
        forgot_pass_email.setEditable(true);

        new_pass_field.setText(null);
        new_pass_field.setEditable(false);
        new_pass_field.setVisible(false);

        retype_new_pass_field.setText(null);
        retype_new_pass_field.setVisible(false);
        retype_new_pass_field.setEditable(false);

        // BUTTONS //
        forgot_pass_email_button.setBounds(205 , 315, 130,35);
        forgot_pass_email_button.setText("Next");
        forgot_pass_email_button.setFont(new Font("Serif" , Font.PLAIN , 15));
        forgot_pass_email_button.setVisible(true);
        forgot_pass_email_button.setEnabled(true);

        forgot_back_button.setVisible(true);
        forgot_back_button.setEnabled(true);

        PasswordCanNotBeChangedReturnButton.setVisible(false);
        PasswordCanNotBeChangedReturnButton.setEnabled(false);
        PasswordChangedReturnButton.setVisible(false);
        PasswordChangedReturnButton.setEnabled(false);
    }
    private void Create3rdStageForgetPasswordPage() {
        // LABELS //
        ForgetPassLabel1.setText("   Create Your New Password  ");
        ForgetPassLabel1.setBounds(130, 135, 281, 52);
        ForgetPassLabel1.setFont(new Font("Serif" , Font.PLAIN , 21));
        ForgetPassLabel1.setVisible(true);

        ForgetPassLabel2.setText("Password");
        ForgetPassLabel2.setBounds(150,215,200,20);
        ForgetPassLabel2.setFont(new Font("Serif" , Font.PLAIN , 21));
        ForgetPassLabel2.setVisible(true);

        ForgetPassLabel3.setText("Retype Password");
        ForgetPassLabel3.setBounds(150,285,300,20);
        ForgetPassLabel3.setFont(new Font("Serif" , Font.PLAIN , 21));
        ForgetPassLabel3.setVisible(true);

        ForgetPassLabel4.setBackground(Color.LIGHT_GRAY);
        ForgetPassLabel4.setBounds(150 , 267,300,14);
        ForgetPassLabel4.setFont(new Font("Serif" , Font.PLAIN , 13));
        ForgetPassLabel4.setText("The Passwords Doesn't Match ");
        ForgetPassLabel4.setVisible(false);

        ForgetPassLabel5.setBackground(Color.LIGHT_GRAY);
        ForgetPassLabel5.setBounds(47 , 343,500,22);
        ForgetPassLabel5.setFont(new Font("Serif" , Font.PLAIN , 15));
        ForgetPassLabel5.setText("You Will Need At Least : 1 Digit , 1 Upper Case Letter , 1 Pumctuation Mark");
        ForgetPassLabel5.setVisible(true);

        ForgetPassLabel6.setVisible(false);
        email_not_exist.setVisible(false);

        // TEXTFIELDS //
        new_pass_field.setBounds(150 , 239 ,240 , 22);
        new_pass_field.setEditable(true);
        new_pass_field.setFont(new Font("Serif" , Font.PLAIN , 15));
        new_pass_field.setBackground(Color.LIGHT_GRAY);
        new_pass_field.setVisible(true);

        retype_new_pass_field.setBounds(150 , 312 ,240 , 22);
        retype_new_pass_field.setEditable(true);
        retype_new_pass_field.setFont(new Font("Serif" , Font.PLAIN , 15));
        retype_new_pass_field.setBackground(Color.LIGHT_GRAY);
        retype_new_pass_field.setVisible(true);

        forgot_pass_email.setVisible(false);
        forgot_pass_email.setEditable(false);

        // BUTTONS //
        forgot_pass_email_button.setBounds(205 , 375, 130,35);
        forgot_pass_email_button.setEnabled(true);
        forgot_pass_email_button.setVisible(true);

        PasswordCanNotBeChangedReturnButton.setVisible(false);
        PasswordCanNotBeChangedReturnButton.setEnabled(false);
        PasswordChangedReturnButton.setVisible(false);
        PasswordChangedReturnButton.setEnabled(false);
    }
    private void CreateErrorForgotPasswordPage() {
        // LABELS //
        ForgetPassLabel1.setText(" Sorry But We Can't Have You  ");
        ForgetPassLabel1.setBounds(123,166,361,19);
        ForgetPassLabel1.setFont(new Font("Serif" , Font.PLAIN , 24));
        ForgetPassLabel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY , 0));
        ForgetPassLabel1.setVisible(true);

        ForgetPassLabel2.setText("Access This Account");
        ForgetPassLabel2.setFont(new Font("Serif" , Font.PLAIN , 24));
        ForgetPassLabel2.setBounds(163, 195, 261, 19);
        ForgetPassLabel2.setVisible(true);

        if (try_cnt != 1)   // JUST TO CLARIFY THE USE OF PLURAL IN THE TEXT //
        ForgetPassLabel6.setText("  You Have " + try_cnt + " Tries Left  ");

        else
        ForgetPassLabel6.setText("  You Have " + try_cnt + " Try Left  ");

        ForgetPassLabel6.setFont(new Font("Serif" , Font.PLAIN , 23));
        ForgetPassLabel6.setBounds(147, 245, 242, 36);
        ForgetPassLabel6.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        ForgetPassLabel6.setVisible(true);

        ForgetPassLabel3.setVisible(false);
        ForgetPassLabel4.setVisible(false);
        email_not_exist.setVisible(false);

        // TEXTFIELD //
        forgot_pass_email.setVisible(false);
        forgot_pass_email.setEditable(false);

        new_pass_field.setVisible(false);
        new_pass_field.setEditable(false);

        retype_new_pass_field.setVisible(false);
        retype_new_pass_field.setEditable(false);

        // BUTTONS //
        forgot_pass_email_button.setBounds(105 , 300, 160,45);
        forgot_pass_email_button.setFont(new Font("Serif",Font.PLAIN,17));
        forgot_pass_email_button.setText("Try Again");
        forgot_pass_email_button.setVisible(true);
        forgot_pass_email_button.setEnabled(true);

        PasswordCanNotBeChangedReturnButton.setBounds(270 , 300, 170,45);
        PasswordCanNotBeChangedReturnButton.setText("Return To Home Page");
        PasswordCanNotBeChangedReturnButton.setFont(new Font("Serif" , Font.PLAIN , 17));
        PasswordCanNotBeChangedReturnButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        PasswordCanNotBeChangedReturnButton.setVisible(true);
        PasswordCanNotBeChangedReturnButton.setEnabled(true);

        PasswordChangedReturnButton.setVisible(false);
        PasswordChangedReturnButton.setEnabled(false);

        forgot_back_button.setVisible(false);
        forgot_back_button.setEnabled(false);
    }
    private void CreateCantChangePasswordPage() {
        // LABELS //
        ForgetPassLabel1.setText(" Sorry But We Can't Have You  ");
        ForgetPassLabel1.setBounds(123,166,361,19);
        ForgetPassLabel1.setFont(new Font("Serif" , Font.PLAIN , 24));
        ForgetPassLabel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY , 0));
        ForgetPassLabel1.setVisible(true);

        ForgetPassLabel2.setText("Access This Account");
        ForgetPassLabel2.setFont(new Font("Serif" , Font.PLAIN , 24));
        ForgetPassLabel2.setBounds(163, 195, 261, 19);
        ForgetPassLabel2.setVisible(true);

        ForgetPassLabel6.setText("  You Have " + 0 + " Tries Left  ");
        ForgetPassLabel6.setFont(new Font("Serif" , Font.PLAIN , 23));
        ForgetPassLabel6.setBounds(147, 245, 242, 36);
        ForgetPassLabel6.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        ForgetPassLabel6.setVisible(true);

        ForgetPassLabel3.setVisible(false);
        ForgetPassLabel4.setVisible(false);
        email_not_exist.setVisible(false);

        // TEXTFIELDS //
        forgot_pass_email.setVisible(false);
        forgot_pass_email.setEditable(false);

        // BUTTONS //
        forgot_pass_email_button.setBounds(105 , 300, 160,45);
        forgot_pass_email_button.setFont(new Font("Serif",Font.PLAIN,17));
        forgot_pass_email_button.setText("Try Again");
        forgot_pass_email_button.setVisible(true);
        forgot_pass_email_button.setEnabled(false);

        PasswordCanNotBeChangedReturnButton.setBounds(270 , 300, 170,45);
        PasswordCanNotBeChangedReturnButton.setText("Return To Home Page");
        PasswordCanNotBeChangedReturnButton.setFont(new Font("Serif" , Font.PLAIN , 17));
        PasswordCanNotBeChangedReturnButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        PasswordCanNotBeChangedReturnButton.setVisible(true);
        PasswordCanNotBeChangedReturnButton.setEnabled(true);

        PasswordChangedReturnButton.setVisible(false);
        PasswordChangedReturnButton.setEnabled(false);

    }

    private void CreatePasswordChangedPage() {
        // LABEL //
        ForgetPassLabel1.setText("  Your Password Has Changed  ");
        ForgetPassLabel2.setVisible(false);
        ForgetPassLabel3.setVisible(false);
        ForgetPassLabel4.setVisible(false);
        ForgetPassLabel5.setVisible(false);

        // TEXTFIELDS //
        new_pass_field.setVisible(false);
        new_pass_field.setEditable(false);
        retype_new_pass_field.setVisible(false);
        retype_new_pass_field.setEditable(false);
        forgot_pass_email.setVisible(false);
        forgot_pass_email.setEditable(false);

        // BUTTON //
        PasswordChangedReturnButton.addActionListener(this);
        PasswordChangedReturnButton.setFocusable(false);
        PasswordChangedReturnButton.setText("Return To Home Page");
        PasswordChangedReturnButton.setFont(new Font("Serif" , Font.PLAIN , 17));
        PasswordChangedReturnButton.setBorder(BorderFactory.createLineBorder(Color.black , 2));
        PasswordChangedReturnButton.setBackground(Color.white);
        PasswordChangedReturnButton.setVisible(true);
        PasswordChangedReturnButton.setEnabled(true);
        PasswordChangedReturnButton.setBounds(180,250 , 170 , 60);

        PasswordCanNotBeChangedReturnButton.setVisible(false);
        PasswordCanNotBeChangedReturnButton.setEnabled(false);

        forgot_pass_email_button.setVisible(false);
        forgot_pass_email_button.setEnabled(false);

        forgot_back_button.setVisible(false);
        forgot_back_button.setEnabled(false);
    }

    private void CreateAccountCreatedPage() {
        // LABELS //
        accountCreatedLabel.setText("    Your Account Has Been Created  ");
        accountCreatedLabel.setBounds(117, 160, 321, 52);
        accountCreatedLabel.setFont(new Font("Serif" , Font.PLAIN , 21));
        accountCreatedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
        accountCreatedLabel.setVisible(true);

        CreatePassLabel13.setVisible(false);
        CreatePassLabel14.setVisible(false);
        CreatePassLabel15.setVisible(false);
        CreatePassLabel16.setVisible(false);
        createAccNotValidDataLabel.setVisible(false);
        create_acc_instruct_label.setVisible(false);

        // TEXTFIELDS //
        create_acc_email.setVisible(false);
        create_acc_email.setEditable(false);
        create_acc_password.setVisible(false);
        create_acc_password.setEditable(false);
        create_acc_retpassword.setVisible(false);
        create_acc_retpassword.setEditable(false);

        // BUTTONS //
        AccountCreatedReturnButton.setVisible(true);
        AccountCreatedReturnButton.setEnabled(true);
        createAccSingUpButton.setVisible(false);
        createAccSingUpButton.setEnabled(false);
        createAccBackButton.setVisible(false);
        createAccBackButton.setEnabled(false);
    }

    private void RestartPage() {
        // DELETING PREVIOUS DATA //
        userDataHandling.Email = "";
        userDataHandling.PassWord = "";
        userDataHandling.RetypedPassword = "";
        forgotbuttonaction = 1;
        flag_add = false;
        try_cnt = 2;

        Pages.clear();
        Panels.clear();
        // RESTARTING PROCESS //
        CreatePageData();
        CreateHomeLogInPage();
        CreateLoggedInPage();
        CreateWrongEmailPage();
        CreateWrongPasswordPage();
        CreateForgetPassWordPage();
        CreateAccountPage();
        SumAndStart();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == log_button) {
                userDataHandling.Email = email.getText();
                userDataHandling.PassWord = password.getText();

                PageCounter = 0;
                CloseLastWindow();

                switch(userDataHandling.IsDataValid()) {
                    case "TRUE-BOTH": {
                        PageCounter = 1;    // LOGGING IN PAGE //
                            break; }
                    case "FALSE-EMAIL": {
                        PageCounter = 2;    // INCORRECT EMAIL PAGE //

                        // TEXTFIELD REDO //
                        email.setText("");
                        password.setText("");

                        break; }
                     case "FALSE-PASSWORD-CORRECT-EMAIL": {
                        PageCounter = 3;    // INCORRECT PASSWRD - CORRECT EMAIL //

                        // TEXTFIELD REDO //
                        password.setText("");

                        break; }
                }
                     OpenNewWindow();
            }

            if (e.getSource() == forgot_button) {
                PageCounter = 0;
                CloseLastWindow();

                PageCounter = 4;    // FORGOT PASSWORD PAGE //
                OpenNewWindow();

            }

            if (e.getSource() == createAccButton) {
                CloseLastWindow();
                PageCounter = 5;    // CREAT AN ACCOUNT PAGE //
                OpenNewWindow();
            }

            if (e.getSource() == createAccSingUpButton) {
                userDataHandling.Email = create_acc_email.getText();
                userDataHandling.PassWord = create_acc_password.getText();
                userDataHandling.RetypedPassword = create_acc_retpassword.getText();

                createAccNotValidDataLabel.setVisible(true);

                if ( !(userDataHandling.emailIsValid()) || (userDataHandling.emailAlreadyExists()) ) {
                    createAccNotValidDataLabel.setBounds(150,289,400,20);
                        if ( !(userDataHandling.emailIsValid()) )
                        createAccNotValidDataLabel.setText("Email not valid");

                        else if ( (userDataHandling.emailAlreadyExists()) )
                        createAccNotValidDataLabel.setText("An account with this email already exists");
                }

                else if ( !(userDataHandling.NewPasswordIsValid()) ) {
                    createAccNotValidDataLabel.setText("Password not valid");
                    createAccNotValidDataLabel.setBounds(150,359,300,20);
                }
                else if ( !(userDataHandling.PasswordsMatch()) ) {
                    createAccNotValidDataLabel.setText("Passwords do not match");
                    createAccNotValidDataLabel.setBounds(150,359,300,20);
                }

                else {
                    CreateAccountCreatedPage(); // EDIT THE PAGE 5 TO AN ACCOUNT CREATED PAGE //
                    userDataHandling.saveNewAccount();
                    System.out.println("\n");
    /* --> needs delete => */ for (int i = 0; ((i < userDataHandling.val_email.size()) && (userDataHandling.val_email.size() == userDataHandling.val_password.size())); ++i) {
                            System.out.println(userDataHandling.val_email.get(i) + " --> " + userDataHandling.val_password.get(i));
                         }
                }
            }

            if (e.getSource() == WrongEmailBackButton || e.getSource() == WrongPasswordBackButton) {
                CloseLastWindow();
                PageCounter = 0;    // RETURN TO HOME PAGE ( 0 ) AFTER TYPING WRONG EMAIL OR PASSWORD //
                OpenNewWindow();
            }

            if (e.getSource() == forgot_pass_email_button) {
                switch (forgotbuttonaction) {
                    case 1: {   // FIRST STAGE TO RETRIEVE PASSWORD //
                        userDataHandling.Email = forgot_pass_email.getText();

                        if (!(userDataHandling.EmailExists())) {
                            email_not_exist.setVisible(true);  // WRONG EMAIL LABEL //
                        } else {
                                Create2ndStageForgetPasswordPage();
                                forgotbuttonaction = 2;
                        }

                           break; }
                    case 2: {   // SECOND STAGE TO RETRIEVE PASSWORD : TRYING TO REMEMBER WHICH IT WAS AND IF CLOSE IT GIVES PERMISSION (3 TRIES OVERALL) //
                        userDataHandling.PassWord = forgot_pass_email.getText();

                         if ( !(userDataHandling.PasswordCloseToValid()) ) {
                                 CreateErrorForgotPasswordPage();
                                 forgotbuttonaction = 5;

                            if ( try_cnt == 0 )
                            CreateCantChangePasswordPage();

                        } else  {
                                forgotbuttonaction = 3;
                                Create3rdStageForgetPasswordPage();
                        }
                            break; }
                    case 3: {   // THIRD STAGE TO RETRIEVE PASSWORD : CHANGING THE ACCOUNTS OLD PASSWORD //
                        userDataHandling.PassWord = new_pass_field.getText();
                        userDataHandling.RetypedPassword = retype_new_pass_field.getText();

                        if (userDataHandling.NewPasswordIsValid()
                                && userDataHandling.PasswordsMatch()) {

                                userDataHandling.ChangeCurrentEmailPass();
                                CreatePasswordChangedPage();
                        } else {
                            if (userDataHandling.PasswordsMatch()) {
                                ForgetPassLabel4.setText("The Password Is Not Valid");
                            } else {
                                ForgetPassLabel4.setText("The Passwords Doesn't Match ");
                            }

                            ForgetPassLabel4.setVisible(true);
                        }
                            break; }
                    case 5: { // CASE OF WRONG TRY TO REMEMBER PASSWORD //
                        forgotbuttonaction = 2;
                        try_cnt--;
                        if ( !(try_cnt < 0) )
                        Create2ndStageForgetPasswordPage();

                            break;
                    }

                }

            }

            if (e.getSource() == PasswordCanNotBeChangedReturnButton || e.getSource() == PasswordChangedReturnButton
                    || e.getSource() == AccountCreatedReturnButton) {   // RETURNS TO HOMEPAGE AND RESTARTS PROCESS //
                CloseLastWindow();

                RestartPage();

                PageCounter = 0;
                OpenNewWindow();
            }

            if (e.getSource() == forgot_back_button) {
                switch (forgotbuttonaction) {   // THE BACK ARROW BUTTON HAS A DIFFERNT OUTPUT FOR EACH FORGET PASSWORD PAGE //
                    case 1: {                   // IT GOES IN REVERSE ORDER OF THE OTHER BUTTON //
                        CloseLastWindow();
                        forgot_pass_email.setText(null);
                        email_not_exist.setVisible(false);
                        PageCounter = 0;
                        try_cnt = 2;
                        OpenNewWindow();
                            break; }
                    case 2: {
                        CreateForgetPassWordPage();
                        forgotbuttonaction = 1;
                            break; }
                    case 3: {
                        Create2ndStageForgetPasswordPage();
                        forgotbuttonaction = 2;
                            break; }
                }
            }

            if (e.getSource() == createAccBackButton) { // BACK ARROW BUTTON FOR RETURN TO HOME PAGE FROM CREAT ACCOUNT PAGE //
                createAccNotValidDataLabel.setVisible(false);
                CloseLastWindow();
                PageCounter = 0;
                OpenNewWindow();
            }
    }

}
