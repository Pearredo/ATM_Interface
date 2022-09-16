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

        //Back Button
        Button BACK_BUTTON = new Button("back");

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
        rootOptions.addRow(2, BACK_BUTTON);
        
        Scene optionsScene = new Scene(rootOptions,350,200);

        //DEPOSIT FUNCTIONS SCENE
        Button DEPOSIT_MONEY_BUTTON = new Button("Deposit Amount");
        TextField DEPOSIT_TEXTFIELD = new TextField();
        GridPane rootDeposit = new GridPane();
        rootDeposit.addRow(0,new Label("Deposit Amount:"),DEPOSIT_TEXTFIELD,DEPOSIT_MONEY_BUTTON);
        rootDeposit.addRow(1,BACK_BUTTON);

        Scene depositScene = new Scene(rootDeposit, 350,200);

        //MONEY DEPOSITED BUTTON
        GridPane rootDeposit2 = new GridPane();
        Label MONEY_DEPOSITED_LABEL = new Label();
        rootDeposit2.addRow(0, MONEY_DEPOSITED_LABEL,BACK_BUTTON);

        Scene MONEY_DEPOSITED_SCENE = new Scene(rootDeposit2,350,200);

        //WITHDRAW SCENE
        TextField WITHDRAW_MONEY_TEXTFIELD = new TextField();
        Button WITHDRAW_MONEY_BUTTON = new Button("WITHDRAW");
        GridPane rootWithdraw = new GridPane();
        rootWithdraw.addRow(0,new Label("Withdraw Amount:"), WITHDRAW_MONEY_TEXTFIELD,WITHDRAW_MONEY_BUTTON);
        rootWithdraw.addRow(1,BACK_BUTTON);

        Scene withdrawScene = new Scene(rootWithdraw,350,200);

        //CHECK BALANCE SCENE
        GridPane rootCheckBalance = new GridPane();
        rootCheckBalance.addRow(0,new Label("Current Balance:\t0.00"));
        rootCheckBalance.addRow(1,BACK_BUTTON);

        Scene checkBalanceScene = new Scene(rootCheckBalance, 350,200);

        //button events
        SUBMIT_CARD_INFO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneStack.push(primaryStage.getScene());
                primaryStage.setScene(optionsScene);
            }
        });
        TO_DEPOSIT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneStack.push(primaryStage.getScene());
                primaryStage.setScene(depositScene);
            }
        });
        TO_WITHDRAW.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneStack.push(primaryStage.getScene());
                primaryStage.setScene(withdrawScene);
            }
        });
        TO_CHECK_BALANCE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneStack.push(primaryStage.getScene());
                primaryStage.setScene(checkBalanceScene);
            }
        });
        BACK_BUTTON.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene((Scene) sceneStack.pop());
            }
        });
        DEPOSIT_MONEY_BUTTON.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MONEY_DEPOSITED_LABEL.setText("Deposited:\t$"+DEPOSIT_TEXTFIELD.getText());
                primaryStage.setScene(MONEY_DEPOSITED_SCENE);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}