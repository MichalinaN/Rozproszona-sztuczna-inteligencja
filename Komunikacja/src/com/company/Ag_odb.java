package com.company;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Ag_odb extends Agent {
    protected void setup() {
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage message = myAgent.receive();
                if(message != null) {
                    ACLMessage reply = message.createReply();
                    if(message.getPerformative() == 16) {
                        System.out.println("REQUEST message : "+message.getContent()); //jesli request wypisuje tresc na ekran
                        reply.setPerformative(ACLMessage.INFORM);  //i wysyla do nadawcy komunikat typu inform 'wykonalem'
                        reply.setContent("Done!");
                    }
                    else if(message.getPerformative()==3) {
                        System.out.println("CFP message : "+message.getContent()); //jesli cfp wypisuje tres wiad na ekran
                        reply.setPerformative(ACLMessage.REQUEST); //i wysyla do nadawcy komunikat typu inform 'jeszcze raz'
                        reply.setContent("Once again.");
                    }
                    else {
                        reply.setPerformative(ACLMessage.NOT_UNDERSTOOD); //jesli wiad innego typu wysyla wiad ze nie zrozumiano
                        reply.setContent("Message has other type.");
                    }
                    myAgent.send(reply);
                }
                else {
                    block();
                }
            }
        });
    }
}
