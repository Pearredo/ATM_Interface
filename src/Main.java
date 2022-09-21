import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AtomicReference<Account> currentAccount= new AtomicReference<>(new Account(null, null, 0, null, 0));
        HashMap<String, Account> AccountMap = new HashMap<>();
        primaryStage.setTitle("ATM");

        //SCENE STACK
        Stack<Scene> sceneStack = new Stack<>();

        //start Scene
        Button startButton = new Button("Start ATM");
        GridPane rootStart = new GridPane();
        rootStart.addRow(0,new Label("Welcome to the ATM"));
        rootStart.addRow(1,startButton);
        Scene startScene = new Scene(rootStart);

        //Primary scene
        TextField CARD_NUMBER_TEXTFIELD = new PasswordField();
        TextField CARD_PIN_TEXTFIELD = new PasswordField();
        Button SUBMIT_CARD_INFO = new Button("Submit");
        Label STATUS_LABEL = new Label();
        Button TO_CREATE_ACCOUNT = new Button("Create Account");

        GridPane rootMain = new GridPane();
        rootMain.addRow(0, new Label("Input Credit Card Number:\t"),CARD_NUMBER_TEXTFIELD);
        rootMain.addRow(1,new Label("Input PIN:\t"),CARD_PIN_TEXTFIELD);
        rootMain.addRow(2,SUBMIT_CARD_INFO,STATUS_LABEL);
        rootMain.addRow(3,new Label("If you have not made an account yet please click here:\t"),TO_CREATE_ACCOUNT);

        Scene scene = new Scene(rootMain, 450,100);

        //ATM FUNCTIONS SCENE
        Button TO_WITHDRAW = new Button("WITHDRAW");
        Button TO_DEPOSIT = new Button("DEPOSIT");
        Button TO_CHECK_BALANCE = new Button("CHECK BALANCE");

        
        GridPane rootOptions = new GridPane();
        rootOptions.addRow(0, TO_WITHDRAW, TO_DEPOSIT);
        rootOptions.addRow(1, TO_CHECK_BALANCE);

        Scene optionsScene = new Scene(rootOptions,350,200);

        //DEPOSIT FUNCTIONS SCENE
        Button DEPOSIT_MONEY_BUTTON = new Button("Deposit Amount");
        TextField DEPOSIT_TEXTFIELD = new TextField();
        GridPane rootDeposit = new GridPane();
        rootDeposit.addRow(0,new Label("Deposit Amount:"),DEPOSIT_TEXTFIELD,DEPOSIT_MONEY_BUTTON);

        Scene depositScene = new Scene(rootDeposit, 350,200);

        //MONEY DEPOSITED BUTTON
        GridPane rootDeposit2 = new GridPane();
        Label MONEY_DEPOSITED_LABEL = new Label();
        Button MD_TO_OP = new Button("Back");
        rootDeposit2.addRow(0, MONEY_DEPOSITED_LABEL,MD_TO_OP);

        Scene MONEY_DEPOSITED_SCENE = new Scene(rootDeposit2,350,200);

        //WITHDRAW SCENE
        TextField WITHDRAW_MONEY_TEXTFIELD = new TextField();
        Button WITHDRAW_MONEY_BUTTON = new Button("WITHDRAW");
        GridPane rootWithdraw = new GridPane();
        rootWithdraw.addRow(0,new Label("Withdraw Amount:"), WITHDRAW_MONEY_TEXTFIELD,WITHDRAW_MONEY_BUTTON);

        Scene withdrawScene = new Scene(rootWithdraw,350,200);

        //Money Withdrawn Scene
        Label withdrewMoneyLabel = new Label();
        Button W_TO_OP = new Button("Back");
        GridPane rootWithdraw2 = new GridPane();
        rootWithdraw2.addRow(0,withdrewMoneyLabel,W_TO_OP);

        Scene MONEY_WITHDRAWN_SCENE = new Scene(rootWithdraw2,350,200);

        //CHECK BALANCE SCENE
        GridPane rootCheckBalance = new GridPane();
        Label currentBalanceLabel = new Label();
        Button CB_TO_OPTIONS = new Button("Back");
        rootCheckBalance.addRow(0, currentBalanceLabel);
        rootCheckBalance.addRow(1,CB_TO_OPTIONS);

        Scene checkBalanceScene = new Scene(rootCheckBalance, 350,200);

        //create Account Scene
        TextField FirstNameInput = new TextField();
        TextField LastNameInput = new TextField();
        TextField PINInput = new TextField();
        TextField InitialDepositMoney = new TextField();
        Button CREATE_ACCOUNT_BUTTON = new Button("Generate Account");
        GridPane rootCreateAccount = new GridPane();

        rootCreateAccount.addRow(0,new Label("First Name:\t"), FirstNameInput, new Label("Last Name:\t"), LastNameInput);
        rootCreateAccount.addRow(1,new Label("Input Initial Deposit:\t$"),InitialDepositMoney,new Label("Input PIN"),PINInput);
        rootCreateAccount.addRow(2,CREATE_ACCOUNT_BUTTON);

        Scene createAccountScene = new Scene(rootCreateAccount, 550,100);

        //account created scene
        Button ACtoPRIMARY = new Button("Back to main page");
        GridPane rootNewAccount = new GridPane();

        Label displayName = new Label("hello");
        Label displayCreditCardInfo = new Label("world");
        Label displayBalance = new Label("something");
        rootNewAccount.addRow(0,displayName);
        rootNewAccount.addRow(1,displayCreditCardInfo);
        rootNewAccount.addRow(2,displayBalance);
        rootNewAccount.addRow(3,ACtoPRIMARY);


        Scene accountCreatedScene = new Scene(rootNewAccount);


        //button events
        TO_CREATE_ACCOUNT.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(createAccountScene);
        });
        SUBMIT_CARD_INFO.setOnAction(event -> {
            try{
                Account a = AccountMap.get(CARD_NUMBER_TEXTFIELD.getText());
                if(a.getPIN()==Integer.parseInt(CARD_PIN_TEXTFIELD.getText())){
                    sceneStack.push(primaryStage.getScene());
                    primaryStage.setScene(optionsScene);

                    currentAccount.set(a);
                }
            }catch (Exception e){

            }


        });
        TO_DEPOSIT.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(depositScene);
        });
        TO_WITHDRAW.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(withdrawScene);
        });
        WITHDRAW_MONEY_BUTTON.setOnAction(event -> {
            withdrewMoneyLabel.setText("You Withdrew:\t$"+WITHDRAW_MONEY_TEXTFIELD.getText());
            currentAccount.get().withdrawDeposit(Float.parseFloat(WITHDRAW_MONEY_TEXTFIELD.getText()));

            //UPDATE FILES
            try{
                FileOutputStream fos = new FileOutputStream("Accounts.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                ArrayList<Account> accountArrayList = new ArrayList<>();

                for(Account a: AccountMap.values()){
                    accountArrayList.add(a);
                }
                oos.writeObject(accountArrayList);
                oos.flush();

                oos.close();
                fos.close();
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }

            primaryStage.setScene(MONEY_WITHDRAWN_SCENE);
        });
        W_TO_OP.setOnAction(event -> primaryStage.setScene(sceneStack.pop()));
        TO_CHECK_BALANCE.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());

            currentBalanceLabel.setText("Your Current Balance is:\t$"+String.valueOf(currentAccount.get().DEPOSIT));

            primaryStage.setScene(checkBalanceScene);
        });
        DEPOSIT_MONEY_BUTTON.setOnAction(event -> {
            currentAccount.get().addDeposit(Float.parseFloat(DEPOSIT_TEXTFIELD.getText()));
            MONEY_DEPOSITED_LABEL.setText("Deposited\t$"+DEPOSIT_TEXTFIELD.getText());

            //UPDATE FILES
            try{
                FileOutputStream fos = new FileOutputStream("Accounts.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                ArrayList<Account> accountArrayList = new ArrayList<>();

                for(Account a: AccountMap.values()){
                    accountArrayList.add(a);
                }
                oos.writeObject(accountArrayList);
                oos.flush();

                oos.close();
                fos.close();
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }

            primaryStage.setScene(MONEY_DEPOSITED_SCENE);
        });
        CREATE_ACCOUNT_BUTTON.setOnAction(event -> {
            //creating account
            Random rnd = new Random();
            int GEN_CC_1 = rnd.nextInt(99999999);
            int GEN_CC_2 = rnd.nextInt(99999999);
            String newCC = String.valueOf(GEN_CC_1) + String.valueOf(GEN_CC_2);
            float f = Float.parseFloat(InitialDepositMoney.getText());
            Account newAccount = new Account(FirstNameInput.getText(),LastNameInput.getText(),Integer.parseInt(PINInput.getText(0,4)),newCC,f);

            //setting new account as current account
            currentAccount.set(newAccount);
            AccountMap.put(newCC,newAccount);

            //setting next scene
            displayName.setText("Hello "+currentAccount.get().FirstName+" "+currentAccount.get().LastName);
            displayCreditCardInfo.setText("Your Credit Card Number is:\t"+currentAccount.get().CC_NUMBER+"\tYour PIN is:\t"+String.valueOf(currentAccount.get().PIN));
            displayBalance.setText("Current Balance:\t"+String.format("%.2f",currentAccount.get().DEPOSIT));

            //serialize data
            try{
                FileOutputStream fos = new FileOutputStream("Accounts.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                ArrayList<Account> accountArrayList = new ArrayList<>();

                for(Account a: AccountMap.values()){
                    accountArrayList.add(a);
                }
                oos.writeObject(accountArrayList);
                oos.flush();

                oos.close();
                fos.close();
            }catch (IOException ioe) {
                ioe.printStackTrace();
            }

            primaryStage.setScene(accountCreatedScene);
        });
        ACtoPRIMARY.setOnAction(event -> {
            primaryStage.setScene(scene);
        });
        MD_TO_OP.setOnAction(event -> primaryStage.setScene(sceneStack.pop()));
        CB_TO_OPTIONS.setOnAction(event -> primaryStage.setScene(sceneStack.pop()));
        startButton.setOnAction(event -> {
            try{
                File serializedAccounts = new File("Accounts.ser");
                if(serializedAccounts.createNewFile()){
                    System.out.println("File Created:\t"+serializedAccounts.getName());
                }else{
                    System.out.println("File Already Exists");

                    FileInputStream fis = new FileInputStream(new File("Accounts.ser"));
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    ArrayList<Account> accountArrayList = new ArrayList<>();
                    accountArrayList = (ArrayList) ois.readObject();

                    for(Account a: accountArrayList){
                        System.out.println(a.CC_NUMBER);
                        System.out.println(a.getPIN());
                        AccountMap.put(a.CC_NUMBER,a);
                    }

                    ois.close();
                    fis.close();
                }

            }catch (IOException ioe){
                System.out.println("An error has occurred.");
                ioe.printStackTrace();
            }catch (ClassNotFoundException c){
                c.printStackTrace();
            }
            primaryStage.setScene(scene);
        });
        primaryStage.setScene(startScene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}