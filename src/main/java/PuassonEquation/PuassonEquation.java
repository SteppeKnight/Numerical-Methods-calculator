package PuassonEquation;

import java.util.HashMap;

public class PuassonEquation {
    private HashMap<Integer , Double> P;
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
        double deltaX = roundToTheFourthPoint((xn - x0) / n);
        double A = roundToTheFourthPoint(1/Math.pow(deltaX, 2));
        double C = A;
        double B = roundToTheFourthPoint(- 2 / Math.pow(deltaX, 2));
        for(int i = 2; i <= n; i++){
            double alphaVar = roundToTheFourthPoint(A/(B + C * alpha.get(i-1)));
            double bettaVar = roundToTheFourthPoint((f - C * betta.get(i-1))/(B + C * alpha.get(i-1)));
            alpha.put(i, alphaVar);
            betta.put(i, bettaVar);
        }
        for(int i = n-1; i > 0; i--){
            double pVar = roundToTheFourthPoint(alpha.get(i + 1) * P.get(i + 1) + betta.get(i + 1));
            P.put(i, pVar);
        }
    }

    public HashMap<Integer, Double> getP() {
        return P;
    }

    static double roundToTheFourthPoint(double d){ // Method to round part after point to four digits.
        d *= 10000;
        int temp = (int)Math.round(d);
        d = (double)temp/10000;
        return d;
    }
}
