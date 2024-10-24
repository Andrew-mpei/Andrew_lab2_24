package ru.mpei.lab;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

public class CheckInformBehaviour extends Behaviour {
    private List<Double> xdX = List.of(0.0, 0.0);
    Double X;
    Double dX;
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if (msg != null){
            if (Double.parseDouble(msg.getContent().split(",")[1]) > 0.001){
                 X = Double.parseDouble(msg.getContent().split(",")[0]);
                 dX = Double.parseDouble(msg.getContent().split(",")[1]);
                myAgent.addBehaviour(new StartSumNewAgentBehaviour(this.myAgent, 1000, X, dX));
            }else{
                System.out.println("X = " + Double.parseDouble(msg.getContent().split(",")[0]));
                System.out.println("dX = " + Double.parseDouble(msg.getContent().split(",")[1]));
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
