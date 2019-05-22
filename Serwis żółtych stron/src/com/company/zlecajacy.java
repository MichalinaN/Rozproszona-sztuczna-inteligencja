package com.company;

import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class zlecajacy extends Agent{
    private zlecajacy myAgent;
    private Random random;
    private Integer number;

    protected void setup() {
        super.setup();

        myAgent = this;
        random = new Random();
        number = null;

        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                System.out.println("Zlecajacy: Trwa losowanie liczby");
                number = (int)(Math.round(random.nextDouble()*100)); //losowanie z przedzialu 0-100
                System.out.println("\t"+number);
            }
        });

        FSMBehaviour fsm = new FSMBehaviour(this) {
            public int onEnd() {
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        addBehaviour(new TickerBehaviour(this, 2000) {
            protected void onTick() {
                System.out.println("Zlecajacy: Sprawdzam uslugi");  //co 20 sekund dowiaduje sie w serwisie ktorzy agenci oferuja typ potega

                DFAgentDescription dfd = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType( "POTEGA" );
                dfd.addServices(sd);

                SearchConstraints ALL = new SearchConstraints();
                ALL.setMaxResults((long) -1);
                try
                {
                    DFAgentDescription[] result = DFService.search(myAgent, dfd, ALL);
                    System.out.println("Zlecajacy: Ilosc wyszukanych uslug: " + result.length);
                    if(result.length>0) {
                        send(result[0].getName());
                        recieve(this);
                    }
                }
                catch (FIPAException fe) { System.out.println("Zlecajacy: Wystapil problem przy wyszukaniu uslugi"); }
            }
        });
    }
    private void send(AID aid) {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);  //jesli z powyzszego zwrocono agenta to wykonuje zadania:
        msg.addReceiver(aid);
        msg.setContent(Integer.toString(number));
        send(msg);
        System.out.println("Zlecajacy: Wysylam wiadomosc REQUEST");  //wysyla wiad typu request, zwracajaca wylosowana liczbe
    }
    private void recieve(Behaviour b) {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg1 = blockingReceive(mt);
        if(msg1!= null) {
            if(msg1.getPerformative() == ACLMessage.INFORM){
                System.out.println("Zlecajacy: Odebralem wiadomosc INFORM");  //odbiera wiad typu inform i wypisuja ja na ekran
                System.out.println( "\t" + msg1.getContent());
                myAgent.doDelete(); //agent sie usuwa
            }
        }
        else {
            b.block();
        }
    }
    protected void takeDown() {
        System.out.println("Zlecajacy: Koncze swoje dzialanie");
    }
}