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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class MainScreenController implements Initializable {

    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;


    @FXML TableView<Part> tvParts;
    @FXML TableColumn<Part, Integer> partId = new TableColumn<>("Part ID");
    @FXML TableColumn<Part, String> partName = new TableColumn<>("Part Name");
    @FXML TableColumn<Part, Double>partPrice = new TableColumn<>("Price/Cost");
    @FXML TableColumn<Part, Integer> partStock = new TableColumn<>("Stock");

    @FXML TableView<Product> tvProducts;
    @FXML TableColumn<Product, Integer> productId = new TableColumn<>("Part ID");
    @FXML TableColumn<Product, String> productName = new TableColumn<>("Part Name");
    @FXML TableColumn<Product, Double>productPrice;
    @FXML TableColumn<Product, Integer> productStock = new TableColumn<>("Stock");

    @FXML TextField searchPartField = new TextField();
    @FXML TextField searchProductField = new TextField();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        partName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        partPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        partStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());

        tvParts.setItems(InventoryProgram.inv.getAllParts());

        productId.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty().asObject());
        productStock.setCellValueFactory(cellData -> cellData.getValue().getStockProperty().asObject());

        tvProducts.setItems(InventoryProgram.inv.getAllProducts());

        TableView.TableViewSelectionModel<Part> tvSelPart = tvParts.getSelectionModel();
        TableView.TableViewSelectionModel<Product> tvSelProduct = tvProducts.getSelectionModel();


    }


    public static int getModifyPartIndex(){
        return modifyPartIndex;
    }

    public static int getModifyProductIndex(){
        return modifyProductIndex;
    }


    @FXML void searchPart(ActionEvent event) throws IOException {
        int index = -1;
        String partField = searchPartField.getText();
        if(InventoryProgram.inv.lookupPart(partField) != -1)
        {
            index = InventoryProgram.inv.lookupPart(partField);
            Part temp = InventoryProgram.inv.getAllParts().get(index);
            ObservableList<Part> tempList = FXCollections.observableArrayList();
            tempList.add(temp);
            tvParts.setItems(tempList);

        }
    }

    @FXML void searchProduct(ActionEvent event) throws IOException {

        String productField = searchProductField.getText();
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        tempList = InventoryProgram.inv.lookupProduct(productField);
        tvProducts.setItems(tempList);

    }


    @FXML
    private void openAddPart(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddPart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openModifyPart(ActionEvent event) throws IOException{
        modifyPart = tvParts.getSelectionModel().getSelectedItem();
        modifyPartIndex = InventoryProgram.inv.getAllParts().indexOf(modifyPart);
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPart.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openAddProduct(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/AddProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openModifyProduct(ActionEvent event) throws IOException{
        modifyProduct = tvProducts.getSelectionModel().getSelectedItem();
        modifyProductIndex = InventoryProgram.inv.getAllProducts().indexOf(modifyProduct);
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void deletePart(ActionEvent event) throws IOException{

        TilePane r = new TilePane();
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.CONFIRMATION);
        a.setContentText("Delete " + tvParts.getSelectionModel().getSelectedItem().getName() + "?");

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            tvParts.getItems().removeAll(tvParts.getSelectionModel().getSelectedItem());
            tvParts.setItems(InventoryProgram.inv.getAllParts());
            System.out.println("Product " + tvParts.getSelectionModel().getSelectedItem().getName() + " was removed.");
        } else {
            System.out.println("Product " + tvParts.getSelectionModel().getSelectedItem().getName() + " was not removed.");
        }

    }

    @FXML
    private void deleteProduct(ActionEvent event) throws IOException{
        TilePane r = new TilePane();
        Alert b = new Alert(AlertType.NONE);
        b.setAlertType(AlertType.CONFIRMATION);
        b.setContentText("Delete " + tvProducts.getSelectionModel().getSelectedItem().getName() + "?");

        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            tvProducts.getItems().removeAll(tvProducts.getSelectionModel().getSelectedItem());
            tvProducts.setItems(InventoryProgram.inv.getAllProducts());
            System.out.println("Product " + tvProducts.getSelectionModel().getSelectedItem().getName() + " was removed.");
        } else {
            System.out.println("Product " + tvProducts.getSelectionModel().getSelectedItem().getName() + " was not removed.");
        }


    }


    @FXML
    private void exit(ActionEvent event) throws IOException{
        System.exit(0);
    }




}