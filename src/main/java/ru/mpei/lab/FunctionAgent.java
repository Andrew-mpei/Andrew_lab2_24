package ru.mpei.lab;

import jade.core.Agent;


public class FunctionAgent extends Agent {
    @Override
    protected void setup() {
        double X = Math.round(Math.random()*1000);
        double dX = Math.round(Math.random()*10);
        if (this.getLocalName().equals("fun1")){
            this.addBehaviour(new StartSumNewAgentBehaviour(this, 10, X, dX));
        }
        this.addBehaviour(new ResultFunctionBehaviour());
        this.addBehaviour(new CheckInformBehaviour());
    }
}
