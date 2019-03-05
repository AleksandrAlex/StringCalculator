package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textResult;

    @FXML
    private Button btnResult;

    @FXML
    private TextField textFormula;



    @FXML
    void initialize() {
        btnResult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // Calculator calculator = new Calculator();
                String string ="";
                string = Calculator.stringToRPN(textFormula.getText());
                double res = Calculator.RPNtoAnswer(string);
                textResult.setText(String.valueOf(res));
            }
        });


    }
}
