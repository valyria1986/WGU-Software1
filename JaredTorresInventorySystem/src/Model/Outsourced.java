package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Outsourced extends Part{
    private final StringProperty companyName;

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }
    public String getCompanyName() {
        return this.companyName.get();
    }

    public Outsourced(){
        super();
        companyName = new SimpleStringProperty();
        System.out.println("Outsourced Part Created");

    }


}
