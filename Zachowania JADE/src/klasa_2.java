import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class klasa_2 extends Agent{
    protected void setup(){
        System.out.println("Startuje");

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("Wykonuje");
            }
        });
    }

    protected void takeDown(){
        System.out.println("Zaraz sie usune!");
        doDelete();
    }
}
