package Cars.my.name;

import Cars.my.name.Car;
import com.company.Print;

public class Camper extends Car implements Print {
    String cbrand;
    String cfuelType;
    int cyearOfProduction;
    private static volatile int counter = 100;

    public Camper() {

    }

    public Camper(String cbrand, String cfuelType, int cyearOfProduction) {
        this.cbrand = cbrand;
        this.cfuelType = cfuelType;
        this.cyearOfProduction = cyearOfProduction;
    }

    @Override
    public int hire(int counter) {
        counter = counter - 1;
        return counter;
    }

    @Override
    public int returning(int counter) {
        return counter;
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
