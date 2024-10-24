package ru.mpei.lab;

import jade.core.Agent;

import java.util.List;
import java.util.Scanner;

public class FunctionAgent extends Agent {
    @Override
    protected void setup() {
        double X = Math.random()*10;
        double dX = Math.random()*10;
        if (this.getLocalName().equals("fun1")){
            this.addBehaviour(new StartSumNewAgentBehaviour(this, 1, X, dX));
        }
        this.addBehaviour(new ResultFunctionBehaviour());
        this.addBehaviour(new CheckInformBehaviour());
    }
}
