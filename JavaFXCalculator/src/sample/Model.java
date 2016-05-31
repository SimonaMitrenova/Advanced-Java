package sample;

public class Model {

    public double calculate(double number1, double number2, String operator){
        double result = 0d;
        switch (operator){
            case "+":
                result = number1 + number2;
                break;

            case "-":
                result = number1 - number2;
                break;

            case "*":
                result = number1 * number2;
                break;

            case "/":
                if (number2 == 0){
                    break;
                }
                result = number1 / number2;
                break;

            default:
                System.out.println("Unknown operator " + operator);
                break;
        }

        return result;
    }
}
