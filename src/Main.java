
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ATM");

        TextField CARD_NUMBER_TEXTFIELD = new PasswordField();
        TextField CARD_PIN_TEXTFIELD = new PasswordField();
        Button SUBMIT_CARD_INFO = new Button("Submit");
        GridPane root = new GridPane();
        root.addRow(0, new Label("Input Credit Card Number:\t"),CARD_NUMBER_TEXTFIELD);
        root.addRow(1,new Label("Input PIN:\t"),CARD_PIN_TEXTFIELD);
        root.addRow(2,SUBMIT_CARD_INFO);

        Scene scene = new Scene(root, 350,200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}