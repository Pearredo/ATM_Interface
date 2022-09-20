import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ATM");

        //SCENE STACK
        Stack sceneStack = new Stack();

        //Primary scene
        TextField CARD_NUMBER_TEXTFIELD = new PasswordField();
        TextField CARD_PIN_TEXTFIELD = new PasswordField();
        Button SUBMIT_CARD_INFO = new Button("Submit");
        Label STATUS_LABEL = new Label();

        GridPane rootMain = new GridPane();
        rootMain.addRow(0, new Label("Input Credit Card Number:\t"),CARD_NUMBER_TEXTFIELD);
        rootMain.addRow(1,new Label("Input PIN:\t"),CARD_PIN_TEXTFIELD);
        rootMain.addRow(2,SUBMIT_CARD_INFO,STATUS_LABEL);

        Scene scene = new Scene(rootMain, 350,200);

        //ATM FUNCTIONS SCENE
        Button TO_WITHDRAW = new Button("WITHDRAW");
        Button TO_DEPOSIT = new Button("DEPOSIT");
        Button TO_CHECK_BALANCE = new Button("CHECK BALANCE");
        Button TO_PRINT_STATEMENT = new Button("PRINT STATEMENT");
        
        GridPane rootOptions = new GridPane();
        rootOptions.addRow(0, TO_WITHDRAW, TO_DEPOSIT);
        rootOptions.addRow(1, TO_CHECK_BALANCE,TO_PRINT_STATEMENT);

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
        rootDeposit2.addRow(0, MONEY_DEPOSITED_LABEL);

        Scene MONEY_DEPOSITED_SCENE = new Scene(rootDeposit2,350,200);

        //WITHDRAW SCENE
        TextField WITHDRAW_MONEY_TEXTFIELD = new TextField();
        Button WITHDRAW_MONEY_BUTTON = new Button("WITHDRAW");
        GridPane rootWithdraw = new GridPane();
        rootWithdraw.addRow(0,new Label("Withdraw Amount:"), WITHDRAW_MONEY_TEXTFIELD,WITHDRAW_MONEY_BUTTON);

        Scene withdrawScene = new Scene(rootWithdraw,350,200);

        //CHECK BALANCE SCENE
        GridPane rootCheckBalance = new GridPane();
        rootCheckBalance.addRow(0,new Label("Current Balance:\t0.00"));

        Scene checkBalanceScene = new Scene(rootCheckBalance, 350,200);

        //create Account Scene
        TextField FirstNameInput = new TextField();
        TextField LastNameInput = new TextField();
        TextField PINInput = new TextField();
        TextField InitialDepositMoney = new TextField();
        Button CREATE_ACCOUNT_BUTTON = new Button("Generate Account");
        GridPane rootCreateAccount = new GridPane();

        rootCreateAccount.addRow(0,new Label("First Name:\t"), FirstNameInput, new Label("Last Name:\t"), LastNameInput);
        rootCreateAccount.addRow(1,new Label("Input Initial Deposit:\t$"),InitialDepositMoney,new Label("Input PIN",PINInput));
        rootCreateAccount.addRow(2,CREATE_ACCOUNT_BUTTON);

        //account created scene
        Button ACtoPRIMARY = new Button("Back to main page");

        //button events
        SUBMIT_CARD_INFO.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(optionsScene);
        });
        TO_DEPOSIT.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(depositScene);
        });
        TO_WITHDRAW.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(withdrawScene);
        });
        TO_CHECK_BALANCE.setOnAction(event -> {
            sceneStack.push(primaryStage.getScene());
            primaryStage.setScene(checkBalanceScene);
        });
        DEPOSIT_MONEY_BUTTON.setOnAction(event -> {
            primaryStage.setScene(MONEY_DEPOSITED_SCENE);
        });


        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}