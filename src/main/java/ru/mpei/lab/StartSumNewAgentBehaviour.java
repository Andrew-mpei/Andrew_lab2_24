package ru.mpei.lab;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

public class StartSumNewAgentBehaviour extends TickerBehaviour {
    private List<Double> xdX = List.of(0.0, 0.0);
    private String[] functions = new String[] {"fun1", "fun2", "fun3"};
    private boolean flag = true;
    private boolean flag2 = true;
    private double X;
    private double dX;
    private double sumMines = 0;
    private double sumPlus = 0;
    private double sumCenter = 0;
    private MessageTemplate mt;
    private int count = 0;


    public StartSumNewAgentBehaviour(Agent a, long period, double X, double dX) {
        super(a, period);
        this.X = X;
        this.dX = dX;
        this.myAgent = a;
    }

    @Override
    public void onStart() {
        this.mt = MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.SUBSCRIBE),
                MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
    }

    @Override
    protected void onTick() {

        if (flag && flag2){//отправляем всем (старт)
            ACLMessage msg1 = new ACLMessage(ACLMessage.SUBSCRIBE);
            msg1.setContent(X + "," + dX);
            for (int i = 1; i <= 3; i++){
                msg1.addReceiver(new AID("fun" + i, false));
            }
            myAgent.send(msg1);
            flag = false;
        }
        //ожидаем получить суммы после ответа всех функций
        if (flag2 && !flag){
            ACLMessage msg2 = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CONFIRM));
            if (msg2 != null){
                sumMines += Double.parseDouble(msg2.getContent().split(",")[0]);
                sumCenter += Double.parseDouble(msg2.getContent().split(",")[1]);
                sumPlus += Double.parseDouble(msg2.getContent().split(",")[2]);
                count ++;
                if (count == 3){
                    flag = true;
                    flag2 = false;
                }

            }
            else{
                block();
            }
        }
        if(!flag2 && flag){//определяем нового агента и отправляем ему новые Х и dX
            if (sumCenter > sumMines && sumPlus > sumMines){
                X -= dX;
            }
            if (sumPlus < sumCenter && sumPlus < sumMines){
                X += dX;
            }else{
                dX /= 2;
            }
            String newAgent = functions[(int) (Math.random() * 3)];
            while (!myAgent.getLocalName().equals(newAgent)){
                newAgent = functions[(int) (Math.random() * 3)];
            }
            ACLMessage msg3 = new ACLMessage(ACLMessage.SUBSCRIBE);
            msg3.setContent(X+ "," + dX);

            msg3.addReceiver(new AID(newAgent, false));
            myAgent.send(msg3);
            flag2 = true;
            myAgent.removeBehaviour(this);
        }
    }
}