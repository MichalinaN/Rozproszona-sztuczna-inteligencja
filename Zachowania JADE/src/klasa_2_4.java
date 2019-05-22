import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class klasa_2_4 extends Agent{
    protected void setup(){
        System.out.println("Startuje");
        addBehaviour(new FourStepBehaviour());
        addBehaviour(new FourStepBehaviour());
        addBehaviour(new FourStepBehaviour());
    }
    private class FourStepBehaviour extends Behaviour{
        private int step = 1;

        public void action(){
            switch(step){
                case 1:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        @Override
                        public void action() {
                            System.out.println("pierwszy krok");
                        }
                    });
                    break;
                case 2:
                    myAgent.addBehaviour(new OneShotBehaviour(myAgent) {
                        @Override
                        public void action() {
                            System.out.println("drugi krok");
                        }
                    });
                    break;
                case 3:
                    Behaviour b = new OneShotBehaviour() {
                        @Override
                        public void action() {
                            System.out.println("trzeci krok");
                            removeBehaviour(this);
                        }
                    };
                    myAgent.addBehaviour(b);
                    break;
            }
            step++;
        }
        public boolean done(){
            return step == 4;
        }
    }
}
