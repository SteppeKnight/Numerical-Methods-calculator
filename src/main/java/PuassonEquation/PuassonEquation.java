package PuassonEquation;

import java.util.HashMap;

public class PuassonEquation {
    private HashMap<Integer, Double> P;
    private HashMap<Integer, Double> alpha;
    private HashMap<Integer, Double> betta;

    public PuassonEquation(double x0, double p0, double xn, double pn, double f, int n, double alpha1, double betta1){
        P = new HashMap<>();
        alpha = new HashMap<>();
        betta = new HashMap<>();
        alpha.put(1, alpha1);
        betta.put(1, betta1);
        P.put(0, p0);
        P.put(n, pn);
        calculatePuasson(x0, xn, f, n);
    }

    public void calculatePuasson(double x0, double xn, double f, int n){
        double deltaX = (xn - x0) / n;
        double A = 1/Math.pow(deltaX, 2);
        double C = A;
        double B = - 2 / Math.pow(deltaX, 2);
        for(int i = 2; i <= n; i++){
            double alphaVar = A/(B + C * alpha.get(i-1));
            double bettaVar = (f - C * betta.get(i-1))/(B + C * alpha.get(i-1));
            alpha.put(i, alphaVar);
            betta.put(i, bettaVar);
        }
        for(int i = n-1; i > 0; i--){
            double pVar = alpha.get(i + 1) * P.get(i + 1) + betta.get(i + 1);
            P.put(i, pVar);
        }
    }

    public HashMap<Integer, Double> getP() {
        return P;
    }

    public static void main(String[] args) {
        PuassonEquation pe = new PuassonEquation(0, 1, 20, 0, 0, 10, 0, 1);
        HashMap hm = pe.getP();
        for(int i = 0; i <= 10; i++){
            System.out.println("P"+i+"   =>   "+ hm.get(i));
        }
    }
}
