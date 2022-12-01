package View_Controller;

import Model.Part;
import Model.Product;
import Main.InventoryProgram;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class AddProductController implements Initializable {

    private int id;
    private ObservableList<Part> productParts = FXCollections.observableArrayList();

    int index = MainScreenController.getModifyProductIndex();


    @FXML TextField addProductIdField = new TextField();
    @FXML TextField addProductNameField = new TextField();
    @FXML TextField addProductPriceField = new TextField();
    @FXML TextField addProductStockField = new TextField();
    @FXML TextField addProductMinField = new TextField();
    @FXML TextField addProductMaxField = new TextField();

    @FXML TextField addProductSearchField = new TextField();


    @FXML TableView<Part> tvParts;
    @FXML TableColumn<Part, Integer > partId = new TableColumn<>("Part ID");
    @FXML TableColumn<Part, String > partName = new TableColumn<>("Part Name");
    @FXML TableColumn<Part, Double > partPrice = new TableColumn<>("Price/Cost");
    @FXML TableColumn<Part, Integer > partStock = new TableColumn<>("Stock");


    @FXML TableView<Part> tvProductParts;
    @FXML TableColumn<Part, Integer > productId = new TableColumn<>("Part ID");
    @FXML TableColumn<Part, String > productName = new TableColumn<>("Part Name");
    @FXML TableColumn<Part, Double > productPrice = new TableColumn<>("Price/Cost");
    @FXML TableColumn<Part, Integer > productStock = new TableColumn<>("Stock");



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id = InventoryProgram.inv.getProductIdCount();
        addProductIdField.setText("Auto Generated ID: " + id);
        Part part = tvParts.getSelectionModel().getSelectedItem();


        partId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        partPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        partStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());


        tvParts.setItems(InventoryProgram.inv.getAllParts());

        productId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        productStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());



        TableView.TableViewSelectionModel<Part> tvSelPart = tvParts.getSelectionModel();
        TableView.TableViewSelectionModel<Part> tvProductPart = tvProductParts.getSelectionModel();


    }

    public ObservableList<Part> getProductParts(){
        return this.productParts;
    }

    @FXML private void save(ActionEvent event){

        if (addProductPriceField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a price!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No price entered!");
            return;
        }

        if (addProductStockField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter amount for inventory!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No inventory entered!");
            return;
        }

        if (addProductNameField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please part enter name!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No part name entered!");
            return;
        }

        if (addProductMinField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a minimum!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No minimum entered!");
            return;
        }

        if (addProductMaxField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a maximum!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No maximum entered!");
            return;
        }


        double price = Double.parseDouble(addProductPriceField.getText());
        int stock = Integer.parseInt(addProductStockField.getText());
        String name = addProductNameField.getText();
        int min = Integer.parseInt(addProductMinField.getText());
        int max = Integer.parseInt(addProductMaxField.getText());

        if (min > max) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Min cannot be greater than Max!!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("Minimum was greater than maximum!");
            return;
        }

        if (max < 1) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Maximum cannot be less than 1!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("Maximum was less than 1!");
            return;
        }

        if (min < 0) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Min cannot be less than 0!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("Minimum was less than 0!");
            return;
        }

        if (max < stock) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Cannot exceed maximum stock!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("Maximum stock was exceeded!");
            return;
        }

        Product product = new Product();

        product.setId(id);
        product.setPrice(price);
        product.setStock(stock);
        product.setMin(min);
        product.setMax(max);
        product.setName(name);

        double partTotalCost = 0;
        for (int i = 0; i < productParts.size(); i++) {
            product.addAssociatedPart(productParts.get(i));
            partTotalCost = partTotalCost + productParts.get(i).getPrice();
        }

        if(price < partTotalCost){
            TilePane r = new TilePane();
            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Part cost is greater than Product price!");
            Optional<ButtonType> result = b.showAndWait();
            return;
        }


        if (productParts.isEmpty()){
            TilePane r = new TilePane();
            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Product must contain at least 1 part!");
            Optional<ButtonType> result = b.showAndWait();

        }else {
            InventoryProgram.inv.addProduct(product);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
    }

    ObservableList<Part> parts = FXCollections.observableArrayList();

    @FXML private void add(ActionEvent event){

        Part part = tvParts.getSelectionModel().getSelectedItem();
        productParts.add(part);
        tvProductParts.setItems(productParts);

    }

    @FXML void searchProduct(ActionEvent event) throws IOException {


        int index = -1;
        String partField = addProductSearchField.getText();

        if(InventoryProgram.inv.lookupPart(partField) != -1)
        {
            index = InventoryProgram.inv.lookupPart(partField);
            Part temp = InventoryProgram.inv.getAllParts().get(index);
            ObservableList<Part> tempList = FXCollections.observableArrayList();
            tempList.add(temp);
            tvParts.setItems(tempList);

        }

    }

    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane();
        Alert b = new Alert(Alert.AlertType.NONE);
        b.setAlertType(Alert.AlertType.CONFIRMATION);
        b.setContentText("Leave Add Product? ");

        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
    }

    @FXML
    private void deletePart(ActionEvent event) throws IOException{

        Part part = tvProductParts.getSelectionModel().getSelectedItem();
        productParts.remove(part);
        tvProductParts.setItems(productParts);


    }

}
