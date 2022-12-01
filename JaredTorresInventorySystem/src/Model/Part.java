package Model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;




public abstract class Part {


    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    private int min_;
    private int max_;


    public Part(){

        name = new SimpleStringProperty();
        stock = new SimpleIntegerProperty();
        id = new SimpleIntegerProperty();
        price = new SimpleDoubleProperty();

    }

    public IntegerProperty getIdProperty() {
        return id;
    }
    public StringProperty getNameProperty() {
        return name;
    }
    public DoubleProperty getPriceProperty() {
        return price;
    }
    public IntegerProperty getStockProperty() {
        return stock;
    }

    public void setId(int id) {
        this.id.set(id);
    }
    public int getId() {
        return this.id.get();
    }


    public void setName(String name) {
        this.name.set(name);
    }
    public String getName() {
        return this.name.get();
    }


    public void setPrice(double price) {
        this.price.set(price);
    }
    public double getPrice() {
        return this.price.get();
    }


    public void setStock(int stock) {
        this.stock.set(stock);
    }
    public int getStock() {
        return this.stock.get();
    }


    public void setMin(int min) {
        min_ = min;
    }
    public int getMin() {
        return min_;
    }

    public void setMax(int max) {
        max_ = max;
    }
    public int getMax() {
        return max_;
    }

}