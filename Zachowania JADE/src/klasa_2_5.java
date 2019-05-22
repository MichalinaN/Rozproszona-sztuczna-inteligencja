import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class klasa_2_5 extends Agent{
    protected void setup(){
        ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();
        Behaviour a = new CyclicBehaviour(this) {
            @Override
            public void action() {
                System.out.println("cyclic 1");
            }
        };
        Behaviour b = new CyclicBehaviour(this) {
            @Override
            public void action() {
                System.out.println("cyclic 2");
            }
        };
        addBehaviour(tbf.wrap(a));
        addBehaviour(tbf.wrap(b));
    }
}
