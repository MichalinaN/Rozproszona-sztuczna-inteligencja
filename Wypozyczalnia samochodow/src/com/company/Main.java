package com.company;

import Cars.my.name.Camper;
import Cars.my.name.Microbus;
import Cars.my.name.PassengerCar;
import person.my.name.Client;
import person.my.name.MyException;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws InputMismatchException, FileNotFoundException, MyException {
        File file = new File("myfile.txt");

        boolean exit = true;
        Scanner scanner = new Scanner(System.in);

        while (exit) {

            PassengerCar myPC = new PassengerCar();
            System.out.println("Welcome to car rental. How can we help you? Choose from the following: \n 1. Rent a car \n 2. Return the car \n " +
                    "3. Talk with an employee \n 4. None of the above, I want to leave");

            int choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    System.out.println("Enter your data: ");
                    System.out.println("Name: ");
                    String clientName;
                    clientName = scanner.next();

                    System.out.println("Surnname: ");
                    String clientSurname = scanner.next();

                    System.out.println("Number of your credit card: ");
                    int creditCardNumber = 0;
                    boolean myresult = false;
                    while (!myresult) {
                        try {
                            creditCardNumber = scanner.nextInt();
                            if (creditCardNumber <= 1000000000) {
                                System.out.println("Too short number of your credit card, try again");
                                //scanner.next();
                            } else {
                                myresult = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong type, try again");
                            scanner.next();
                        }
                    }

                    System.out.println("Your age: ");
                    float clientAge;
                    Scanner input = new Scanner(file);
                    clientAge = input.nextFloat();
                    System.out.println(clientAge);

                    Client myclient2 = new Client(clientName, clientSurname, creditCardNumber, clientAge);
                    myclient2.print();
                    myclient2.objectToFile();
                    //myclient2.objectFromFile();

                    System.out.println("Which kind of car would you like to rent? Choose from the following: \n 1. Passenger Car \n 2. Microbus" +
                            "\n 3. Camper");
                    int choose2 = scanner.nextInt();

                    switch (choose2) {
                        case 1:
                            System.out.println("Your favourite brand: ");
                            String brand = scanner.next();

                            System.out.println("What kind of fuel do you prefer?");
                            String fuelType = scanner.next();

                            System.out.println("Which year of production do you prefer?");
                            int yearOfProduction = 0;
                            boolean myresult3 = false;
                            while (!myresult3) {
                                try {
                                    yearOfProduction = scanner.nextInt();
                                    if (yearOfProduction >= 2000)
                                        myresult3 = true;
                                    else {
                                        System.out.println("We have not older cars then 2000.");
                                        System.exit(0);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Exception! Wrong type");
                                    throw new MyException("Incorrerct type");
                                } finally {
                                    System.out.println("Thank you for using our services ");
                                }
                            }

                            PassengerCar myPC2 = new PassengerCar(brand, fuelType, yearOfProduction);
                            myPC2.print1();
                            break;
                        case 2:
                            System.out.println("Your favourite brand: ");
                            String mbrand = scanner.next();

                            System.out.println("What kind of fuel do you prefer?");
                            String mfuelType = scanner.next();

                            System.out.println("Which year of production do you prefer?");
                            boolean myresult4 = false;
                            int myearOfProduction = 0;
                            while (!myresult4) {
                                try {
                                    myearOfProduction = scanner.nextInt();
                                    if (myearOfProduction >= 2000)
                                        myresult4 = true;
                                    else {
                                        System.out.println("We have not older cars then 2000.");
                                        System.exit(0);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Exception! Wrong type");
                                    throw new MyException("Incorrerct type");
                                } finally {
                                    System.out.println("Thank you for using our services ");
                                }
                            }
                            Microbus myMB = new Microbus(mbrand, mfuelType, myearOfProduction);
                            myMB.print1();
                            break;
                        case 3:
                            System.out.println("Your favourite brand: ");
                            String cbrand = scanner.next();

                            System.out.println("What kind of fuel do you prefer?");
                            String cfuelType = scanner.next();

                            System.out.println("Which year of production do you prefer?");
                            boolean myresult5 = false;
                            int cyearOfProduction = 0;
                            while (!myresult5) {
                                try {
                                    cyearOfProduction = scanner.nextInt();
                                    if (cyearOfProduction >= 2000)
                                        myresult5 = true;
                                    else {
                                        System.out.println("We have not older cars then 2000.");
                                        System.exit(0);
                                    }
                                } catch (Exception e) {
                                    System.out.println("Exception! Wrong type");
                                    throw new MyException("Incorrerct type");
                                } finally {
                                    System.out.println("Thank you for using our services ");
                                }
                            }
                            Camper myCP = new Camper(cbrand, cfuelType, cyearOfProduction);
                            myCP.print1();
                            break;
                        default:
                            System.out.println("Wrong choice");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Enter number of your credit card: ");
                    int creditCard = scanner.nextInt();
                    if (creditCard <= 1000000000) {
                        throw new InputMismatchException("Wrong amount of numbers");
                    } else {
                        System.out.println("Your payment is done, thank you for using our services ");
                        myPC.print2();
                    }
                    break;
                case 3:
                    System.out.println("If you want to contact employee, call 432 520 10");
                    break;
                case 4:
                    System.out.println("Thank you, we recommend our company for the future!");
                    exit = false;
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }

    }
}
