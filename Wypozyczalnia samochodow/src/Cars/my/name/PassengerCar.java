package Cars.my.name;

import Cars.my.name.Car;
import com.company.Print;

public class PassengerCar extends Car implements Print {
    String brand;
    String fuelType;
    int yearOfProduction;
    private static volatile int counter = 100;

    public PassengerCar() {

    }

    public PassengerCar(String brand, String fuelType, int yearOfProduction) {
        this.brand = brand;
        this.fuelType = fuelType;
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public int hire(int counter) {
        this.counter = this.counter - 1;
        return this.counter;
    }

    @Override
    public int returning(int counter) {
        this.counter = this.counter + 1;
        return this.counter++;
    }

    @Override
    public void print() {

    }

    @Override
    public void print1() {
        System.out.println("We have " + hire(counter) + " cars left. Your car is booked. Our employee will contact you");
    }

    @Override
    public void print2() {
        System.out.println("We have " + returning(counter) + " cars left");
    }

}
