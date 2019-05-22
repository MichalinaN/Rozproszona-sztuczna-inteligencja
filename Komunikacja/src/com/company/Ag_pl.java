package com.company;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Ag_pl extends Agent
{
    private MessageTemplate template = MessageTemplate.MatchLanguage("Polski"); //pobiera zawartosc skrzynki jesli w language ma "Polski" wszystkie inne nieodebrane
    protected void setup()
    {
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage message = myAgent.receive(template);
                if(message!=null)
                {
                    System.out.println("Message : "+message.getContent()); //po odebraniu wiad jest wypisywana na ekran
                }
            }
        });
    }
}
