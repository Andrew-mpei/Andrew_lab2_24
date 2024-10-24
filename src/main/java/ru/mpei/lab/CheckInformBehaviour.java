package ru.mpei.lab;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class CheckInformBehaviour extends Behaviour {
    Double X;
    Double dX;
    boolean flag = false;
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.DISCONFIRM));
        if (msg != null){
            if (Double.parseDouble(msg.getContent().split(",")[1]) > 0.0000001){
                 X = Double.parseDouble(msg.getContent().split(",")[0]);
                 dX = Double.parseDouble(msg.getContent().split(",")[1]);
                 myAgent.addBehaviour(new StartSumNewAgentBehaviour(this.myAgent, 1, X, dX));
            }else{
                System.out.println("Xmin = " + Double.parseDouble(msg.getContent().split(",")[0]));
                System.out.println("dX = " + Double.parseDouble(msg.getContent().split(",")[1]));
                Functions culcFunctions = new Functions(
                        Double.parseDouble(msg.getContent().split(",")[0])
                        , Double.parseDouble(msg.getContent().split(",")[1])
                );
                String result = String.valueOf(
                        culcFunctions.Func1().get(1)
                        + culcFunctions.Func2().get(1)
                        + culcFunctions.Func3().get(1)
                );
                System.out.println("Минимум функции e^(0.2x)+cos(x)+2^(-x) ");
                System.out.println("Ymin = " + result);
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
