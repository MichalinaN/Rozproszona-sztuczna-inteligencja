import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import java.util.Random;

public class klasa_1_2 extends Agent {
    protected void setup(){
        System.out.println("Startuje");
        addBehaviour(new FourStepBehaviour());
    }

    private class FourStepBehaviour extends Behaviour{
        int step = 1;
        int krok = 0;
        Random r;
        public void action(){
            switch(step){
                case 1:
                    krok = 0;
                    System.out.println("A - " + krok);
                    break;
                case 2:
                    r = new Random();
                    krok = r.nextInt(2);
                    System.out.println("B - " + krok);
                    if(krok == 0){
                        step = 3;
                    }
                    break;
                case 3:
                    System.out.println("C - " + krok);
                    break;
                case 4:
                    r = new Random();
                    krok = r.nextInt(2);
                    System.out.println("D - " + krok);
                    if(krok ==1){
                        step=0;
                    }
                    break;
                case 5:
                    System.out.println("E - " + krok);
                    break;
            }
            step++;
        }
        public boolean done(){
            return step == 6;
        }
    }
}
