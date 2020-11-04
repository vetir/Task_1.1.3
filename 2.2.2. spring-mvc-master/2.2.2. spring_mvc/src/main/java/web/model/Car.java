package web.model;

public class Car {
    private int id;
    private String model;
    private int number;
    private double weight;

    public Car() {

    }

    public Car(String model, int number, double weight) {
        this.model = model;
        this.number = number;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", number=" + number +
                ", weight=" + weight +
                '}';
    }
}
