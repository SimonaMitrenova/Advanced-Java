package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private TableView<Product> table;
    private TextField nameInput;
    private TextField priceInput;
    private TextField quantityInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Products");

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table = new TableView<>();
        table.setItems(getProducts());
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        priceInput = new TextField();
        priceInput.setPromptText("Price");

        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addProduct());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteProduct());

        HBox bottomLayout = new HBox();
        bottomLayout.setPadding(new Insets(10, 10, 10, 10));
        bottomLayout.setSpacing(10);
        bottomLayout.setStyle("-fx-background-color: #1d1d1d");
        bottomLayout.getChildren().addAll(nameInput, priceInput, quantityInput, addButton, deleteButton);

        VBox layout = new VBox();
        layout.getChildren().addAll(table, bottomLayout);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void deleteProduct() {
        ObservableList<Product> selectedItems = table.getSelectionModel().getSelectedItems();
        selectedItems.forEach(table.getItems()::remove);
    }

    private void addProduct() {
        if (isPriceValid(priceInput.getText()) && isQuantityValid(quantityInput.getText())){
            String name = nameInput.getText();
            double price = Double.parseDouble(priceInput.getText());
            int quantity = Integer.parseInt(quantityInput.getText());
            Product product = new Product(name, price, quantity);
            table.getItems().add(product);
        }
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    private boolean isPriceValid(String input){
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    private boolean isQuantityValid(String input){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop", 1500.00, 28));
        products.add(new Product("Switch", 700.00, 35));
        products.add(new Product("Router", 2400.00, 13));
        products.add(new Product("Mouse", 13.00, 76));
        products.add(new Product("Keyboard", 20.00, 123));
        products.add(new Product("HTML cable", 10.00, 234));
        products.add(new Product("Monitor", 450.00, 54));
        products.add(new Product("HDD SSD", 300.00, 145));
        products.add(new Product("Video card", 532.00, 125));
        products.add(new Product("MS Office", 140.00, 341));
        products.add(new Product("Windows 10", 300.00, 231));

        return products;
    }
}
