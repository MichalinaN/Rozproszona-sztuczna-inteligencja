package com.company;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.FIPAException;

public class wykonawca extends Agent{

    protected void setup() {

        super.setup();

        DFAgentDescription dfAgentDescription = new DFAgentDescription(); //do ustawienia serwisu zoltych stron
        dfAgentDescription.setName( getAID() );
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType( "POTEGA" ); //rejestracja w serwisie o typie potega
        serviceDescription.setName( getLocalName() );
        dfAgentDescription.addServices(serviceDescription);

        try {
            DFService.register(this, dfAgentDescription);
        }
        catch (FIPAException e) { e.printStackTrace(); }


        MessageTemplate messageTemplate = MessageTemplate.MatchPerformative( ACLMessage.REQUEST); //jesli otrzyma typ request
        addBehaviour( new CyclicBehaviour()
        {
            public void action() {

                ACLMessage msg = blockingReceive(messageTemplate);
                String message = msg.getContent();

                if(msg!=null){
                    System.out.println("Wykonawca : Odebrano wiadomosc typu REQUEST : " + message); //liczba, podnosi do potegi 2, odpowiada typ informa

                    Double number = Double.parseDouble(message);
                    if (message == null){
                        System.out.println("Wykonawca : Bledne dane");
                        AID sender = msg.getSender();
                        System.out.println("Wykonawca: Wysylam wiadomosc typu INFORM");
                        ACLMessage response = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);
                        response.addReceiver(sender);
                        response.setContent("Error - incorrect data");
                        send(response);
                    }
                    else if(number != null){
                        System.out.println("Wykonawca : Wczytana liczba : " + number);
                        number = Math.pow(number, 2);
                        AID sender = msg.getSender();
                        System.out.println("Wykonawca : Wysylam wiadomosc typu INFORM: " + Double.toString(number)); //wyswietlenie potegi
                        ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                        response.addReceiver(sender);
                        response.setContent(Double.toString(number));
                        send(response);
                    }
                }
                else{
                    block();
                }
            }
        });
    }
    protected void takeDown() {
        try {
            DFService.deregister(this); //przed usunieciem wyrejestrowuwuje sie z serwisu
        } catch (FIPAException e) {e.printStackTrace();}

    }
}