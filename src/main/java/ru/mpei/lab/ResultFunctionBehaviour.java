package ru.mpei.lab;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class ResultFunctionBehaviour extends Behaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE));
        if(msg != null){
            Double X = Double.valueOf(msg.getContent().split(",")[0]);
            Double dX = Double.valueOf(msg.getContent().split(",")[1]);

            Functions culcFunctions = new Functions(X, dX);
            List<Double> fun = new ArrayList<>();
            if(myAgent.getLocalName().equals("fun1")){
                fun = culcFunctions.Func1();
            }
            if (myAgent.getLocalName().equals("fun2")){
                fun = culcFunctions.Func2();
            }
            if (myAgent.getLocalName().equals("fun3")){
                fun = culcFunctions.Func3();
            }

            String content = fun.get(0) + "," ;
            content += fun.get(1) + "," ;
            content += fun.get(2);
            ACLMessage msg2 = new ACLMessage(ACLMessage.CONFIRM);
            msg2.setContent(content);
            msg2.addReceiver(new AID(msg.getSender().getLocalName(), false));
            myAgent.send(msg2);


        }else{
            block();
        }

    }

    @Override
    public boolean done() {
        return false;
    }
}
