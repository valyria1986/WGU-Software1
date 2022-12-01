package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import Main.InventoryProgram;
import static Main.InventoryProgram.inv;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class ModifyPartController implements Initializable {
    int index = View_Controller.MainScreenController.getModifyPartIndex();
    private boolean isOutsourced;
    int id;
    InHouse newInHouse = new InHouse();
    Outsourced newOutsourced = new Outsourced();

    @FXML TextField modifyPartIdField;
    @FXML TextField modifyPartNameField;
    @FXML TextField modifyPartStockField;
    @FXML TextField modifyPartPriceField;
    @FXML TextField modifyPartMinField;
    @FXML TextField modifyPartMaxField;
    @FXML TextField dynamicField;
    @FXML Label modifyPartLabel;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Part part = inv.getAllParts().get(index);
        id = inv.getAllParts().get(index).getId();
        String getName = inv.getAllParts().get(index).getName();
        modifyPartIdField.setText(Integer.toString(id));
        modifyPartNameField.setText(getName);
        modifyPartStockField.setText(Integer.toString(inv.getAllParts().get(index).getStock()));
        modifyPartPriceField.setText(Double.toString(part.getPrice()));
        modifyPartMinField.setText(Integer.toString(part.getMin()));
        modifyPartMaxField.setText(Integer.toString(part.getMax()));


        ToggleGroup radioButtonGroup = new ToggleGroup();
        RadioButton inHouse = inHouseRadio;
        RadioButton outsourced = outsourcedRadio;
        inHouse.setToggleGroup(radioButtonGroup);
        outsourced.setToggleGroup(radioButtonGroup);

        if(part instanceof Outsourced){
            System.out.println("stuck");
            outsourcedRadioPress();
            System.out.println("got through radio press function call");
            outsourced.setSelected(true);
            inHouse.setDisable(true);
            outsourced.setDisable(true);
            Outsourced parts = new Outsourced();
            parts =((Outsourced) InventoryProgram.inv.getAllParts().get(index));
            dynamicField.setText(parts.getCompanyName());
            System.out.println("WTF");

        }else {

            inHouseRadioPress();
            inHouse.setSelected(true);
            inHouse.setDisable(true);
            outsourced.setDisable(true);
            dynamicField.setText(Integer.toString(((InHouse) InventoryProgram.inv.getAllParts().get(index)).getMachineId()));
        }
    }

    @FXML RadioButton inHouseRadio = new RadioButton();
    @FXML RadioButton outsourcedRadio = new RadioButton();


    @FXML void cancel(ActionEvent event){
        TilePane r = new TilePane();
        Alert b = new Alert(Alert.AlertType.NONE);
        b.setAlertType(Alert.AlertType.CONFIRMATION);
        b.setContentText("Leave Modify Part?");

        Optional<ButtonType> result = b.showAndWait();
        if (result.get() == ButtonType.OK) {
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }
    }


    void inHouseRadioPress(){
        isOutsourced = false;
        modifyPartLabel.setText("Machine ID");
        dynamicField.setText(Integer.toString(((InHouse) InventoryProgram.inv.getAllParts().get(index)).getMachineId()));
    }

    void outsourcedRadioPress(){
        isOutsourced = true;
        modifyPartLabel.setText("Company Name");
        dynamicField.setText(((Outsourced) InventoryProgram.inv.getAllParts().get(index)).getCompanyName());


    }



    @FXML private void save(ActionEvent event){

        String idField = modifyPartIdField.getText();
        String nameField = modifyPartNameField.getText();
        String priceField = modifyPartPriceField.getText();
        String stockField = modifyPartStockField.getText();
        String minField = modifyPartMinField.getText();
        String maxField = modifyPartMaxField.getText();
        String switchField = dynamicField.getText();

        if (isOutsourced == false){

            InHouse newInHousePart = new InHouse();
            newInHousePart.setId(Integer.parseInt(idField));
            newInHousePart.setPrice(Double.parseDouble(priceField));
            newInHousePart.setStock(Integer.parseInt(stockField));
            newInHousePart.setMin(Integer.parseInt(minField));
            newInHousePart.setMax(Integer.parseInt(maxField));
            newInHousePart.setName(nameField);
            newInHousePart.setMachineId(Integer.parseInt(switchField));
            inv.updatePart(index, newInHousePart);
        }

        if (isOutsourced == true){

            Outsourced newOutsourcedPart = new Outsourced();
            newOutsourcedPart.setId(Integer.parseInt(idField));
            newOutsourcedPart.setPrice(Double.parseDouble(priceField));
            newOutsourcedPart.setStock(Integer.parseInt(stockField));
            newOutsourcedPart.setMin(Integer.parseInt(minField));
            newOutsourcedPart.setMax(Integer.parseInt(maxField));
            newOutsourcedPart.setName(nameField);
            newOutsourcedPart.setCompanyName(switchField);
            inv.updatePart(index, newOutsourcedPart);

        }


        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

    }

}
