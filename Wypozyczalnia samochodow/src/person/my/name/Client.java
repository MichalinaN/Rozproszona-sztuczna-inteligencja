package person.my.name;

import com.company.Print;

import java.io.*;

public class Client implements Print, Serializable {
    String clientName;
    String clientSurname;
    int creditCardNumber;
    int creditCardNumber2;
    double clientAge;

    public Client() {

    }

    public Client(String clientName, String clientSurname, int creditCardNumber, double clientAge){
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.creditCardNumber = creditCardNumber;
        this.clientAge = clientAge;
    }

    @Override
    public void print() throws FileNotFoundException {
        System.out.println("Summary of your data: \n Name: " + clientName + "\n Surname: " + clientSurname + "\n Number of your credit card: " +
                creditCardNumber + "\n Your age: " + clientAge);
        PrintWriter write = new PrintWriter("mywriter.txt");
        write.println(clientName);
        write.println(clientSurname);
        write.println(creditCardNumber);
        write.println(clientAge);
        write.close();
    }

    @Override
    public void print1() {

    }

    @Override
    public void print2() {

    }

    public void objectToFile(){
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(("objects.bin")))){
            outStream.writeObject(clientName);
            outStream.writeObject(clientSurname);
            outStream.writeObject(clientAge);
            outStream.writeObject(creditCardNumber);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void objectFromFile(){
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(("objects.bin")))){
            clientAge = (Integer)inStream.readObject();
            clientName = (String)inStream.readObject();
            clientSurname = (String)inStream.readObject();
            creditCardNumber = (Integer)inStream.readObject();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException c) {
            System.out.println(c.getMessage());
        }
    }
}
