/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.InHouse;
import Model.Outsourced;
import Main.InventoryProgram;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 **/
public class AddPartController implements Initializable {

    int id;

    @FXML TextField idField = new TextField();
    @FXML TextField nameField = new TextField();
    @FXML TextField priceField = new TextField();
    @FXML TextField stockField = new TextField();
    @FXML TextField minField = new TextField();
    @FXML TextField maxField = new TextField();
    @FXML TextField machineIdField = new TextField();

    @FXML Label addPartLabel = new Label();




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ToggleGroup radioButtonGroup = new ToggleGroup();
        RadioButton inHouse = addPartInHouseRadio;
        RadioButton outsourced = addPartOutsourcedRadio;
        inHouse.setToggleGroup(radioButtonGroup);
        outsourced.setToggleGroup(radioButtonGroup);

        id = InventoryProgram.inv.getPartIdCount();
        idField.setText("Auto-Generated ID: " + id);

    }



    @FXML RadioButton addPartInHouseRadio = new RadioButton();
    @FXML RadioButton addPartOutsourcedRadio = new RadioButton();

    @FXML void inHouseRadioPress(){

        addPartLabel.setText("Machine ID");
        machineIdField.setPromptText("Enter Machine ID");
    }

    @FXML void outsourcedRadioPress(){

        addPartLabel.setText("Company Name");
        machineIdField.setPromptText("Company ABC");

    }

    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane();

        Alert b = new Alert(Alert.AlertType.NONE);

        b.setAlertType(Alert.AlertType.CONFIRMATION);
        b.setContentText("Leave Add Part? ");


        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }

    }


    @FXML private void save(ActionEvent event){

        if (priceField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a price!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No price entered!");
            return;
        }

        if (stockField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter amount for inventory!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No inventory entered!");
            return;
        }

        if (nameField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please part enter name!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No part name entered!");
            return;
        }

        if (minField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a minimum!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No minimum entered!");
            return;
        }

        if (maxField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a maximum!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No maximum entered!");
            return;
        }

        if (machineIdField.getText().isEmpty()) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Please enter a ID or Company Name!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("No ID or Company name entered!");
            return;
        }

        double price= Double.parseDouble(priceField.getText());
        int stock = Integer.parseInt(stockField.getText());
        String nameText = nameField.getText();
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());
        int machineId = Integer.parseInt(machineIdField.getText());
        String companyNameText = machineIdField.getText();


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

        if (price < 0) {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Price cannot be less than 0!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("Invalid price entered!");
            return;
        }

        if (addPartInHouseRadio.isSelected()) {

            InHouse newInHouse = new InHouse();
            newInHouse.setId(id);
            newInHouse.setPrice(price);
            newInHouse.setStock(stock);
            newInHouse.setMin(min);
            newInHouse.setMax(max);
            newInHouse.setName(nameText);
            newInHouse.setMachineId(machineId);
            InventoryProgram.inv.addPart(newInHouse);

            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        } else if (addPartOutsourcedRadio.isSelected()){
            Outsourced newOutsourced = new Outsourced();
            newOutsourced.setId(id);
            newOutsourced.setPrice(price);
            newOutsourced.setStock(stock);
            newOutsourced.setMin(min);
            newOutsourced.setMax(max);
            newOutsourced.setName(nameText);
            newOutsourced.setCompanyName(companyNameText);

            InventoryProgram.inv.addPart(newOutsourced);

            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();


        } else {
            TilePane r = new TilePane();

            Alert b = new Alert(Alert.AlertType.NONE);
            b.setAlertType(Alert.AlertType.CONFIRMATION);
            b.setContentText("Select In-House or Outsourced!");
            Optional<ButtonType> result = b.showAndWait();
            System.out.println("Did not select In-House or Outsourced!");
        }


    }


}
