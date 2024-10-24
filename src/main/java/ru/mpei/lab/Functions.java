package ru.mpei.lab;

import java.util.ArrayList;
import java.util.List;

public class Functions {
    private double X;
    private double dX;
    private List<Double> fun = new ArrayList<>();

    public Functions(double X, double dX) {
        this.X = X;
        this.dX = dX;
    }

    public List<Double> getFun() {
        return fun;
    }

    public List<Double> Func1(){
        getFun().add(Math.exp(0.2*(X-dX)));
        getFun().add(Math.exp(0.2*(X)));
        getFun().add(Math.exp(0.2*(X+dX)));

        return getFun();
    }
    public List<Double> Func2(){
        getFun().add(Math.pow(2, (-1)*(X-dX)));
        getFun().add(Math.pow(2, (-1)*X));
        getFun().add(Math.pow(2, (-1)*(X+dX)));
        return getFun();
    }
    public List<Double> Func3(){
        getFun().add(Math.cos(X-dX));
        getFun().add(Math.cos(X));
        getFun().add(Math.cos(X+dX));
        return getFun();
    }
}