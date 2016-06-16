package sample;

import com.sun.javaws.progress.Progress;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    TextField fromBox;
    @FXML
    TextField toBox;
    @FXML
    TextArea outputArea;

    private Thread thread;

    @FXML
    public void onStart(ActionEvent actionEvent) {
        outputArea.clear();
        int from = Integer.parseInt(fromBox.getText());
        int to = Integer.parseInt(toBox.getText());
        thread = new Thread(() -> printPrimesInRange(from, to));
        thread.setDaemon(false);
        thread.start();
    }

    private void printPrimesInRange(int from, int to) {
        StringBuilder primesBuilder = new StringBuilder();
        if (from < 2){
            from = 2;
        }

        for (int number = from; number < to; number++) {
            boolean isPrime = isPrime(number);
            if (isPrime) {
                primesBuilder.append(String.format("%d%n", number));
            }
            if (thread.isInterrupted()){
                primesBuilder.insert(0, String.format("Worker thread %d is interrupted. The results so far are:%n", thread.getId()));
                break;
            }
        }

        outputArea.appendText(primesBuilder.toString());
    }

    private boolean isPrime(int number) {
        boolean isPrime = true;
        double maxDivider = Math.sqrt(number);
        for (int divider = 2; divider <= maxDivider; divider++) {
            if (number % divider == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    @FXML
    public void onStop(ActionEvent actionEvent) {
        thread.interrupt();
    }
}
