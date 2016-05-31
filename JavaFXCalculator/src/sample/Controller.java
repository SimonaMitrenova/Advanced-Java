package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Text output;

    private Model model = new Model();
    private String operator = "";
    private double number1 = 0;
    private boolean start = true;

    @FXML
    private void processNumpad(ActionEvent event){
        if (start){
            output.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (!"=".equals(value)){
            if (!operator.isEmpty()){
                return;
            }
            number1 = Double.parseDouble(output.getText());
            operator = value;
            start = true;
            return;
        }

        if (operator.isEmpty()){
            return;
        }
        double number2 = Double.parseDouble(output.getText());
        Double result = model.calculate(number1, number2, operator);
        output.setText(String.valueOf(result));
        operator = "";
        start = true;
    }
}
