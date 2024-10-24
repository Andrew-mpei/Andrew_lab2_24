package ru.mpei.lab;

import jade.core.Agent;

import java.util.List;

public class FunctionAgent extends Agent {
    @Override
    protected void setup() {

        System.out.println("go");
        double X = Math.random()*10;
        double dX = Math.random()*10;
        System.out.println(X);
        if (this.getLocalName().equals("fun1")){
            this.addBehaviour(new StartSumNewAgentBehaviour(this, 1000, X, dX));
        }
        this.addBehaviour(new ResultFunctionBehaviour());
        this.addBehaviour(new CheckInformBehaviour());
    }
}
