package Cars.my.name;

import Cars.my.name.Car;
import com.company.Print;

public class Microbus extends Car implements Print {
    String mbrand;
    String mfuelType;
    int myearOfProduction;
    private static volatile int counter = 100;

    public Microbus() {

    }

    public Microbus(String mbrand, String mfuelType, int myearOfProduction) {
        this.mbrand = mbrand;
        this.mfuelType = mfuelType;
        this.myearOfProduction = myearOfProduction;
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
