package EulerMethod;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class EulerMethod {

    private HashMap<Double, Double> values = new HashMap<Double, Double>();

    public EulerMethod(String func, int x, int y, int a, int b) throws ScriptException {

        calculateEuler(x, y, a, b, func);

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tolls = Toolkit.getDefaultToolkit();
        Dimension dim = tolls.getScreenSize();
        jFrame.setBounds(dim.width/2 - 250, dim.height/2 - 150, 100, 100 );
        jFrame.setResizable(false);
        jFrame.setTitle("Results");

        JPanel jPanel = new JPanel();


    }

    public void calculateEuler(int x, double y, int a, int b, String func) throws ScriptException {
        double h = (double)(b - a)/5;
        for(double i = a; i <= b; i += h) {
            values.put(i, y);
            String iStr = String.valueOf(i);
            if(iStr.length() > 3){
                i = Double.valueOf(iStr.substring(0, 4));
            }
            iStr = null;
            System.gc();
            String currentFunc = functionMaker(func, i, y);
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            y += h * (Double)engine.eval(currentFunc);
        }
    }



    private String functionMaker(String str, double x, double y){
        StringBuffer func = new StringBuffer(str);
        for(int i = 0; i < func.length(); i++){
            char c = func.charAt(i);
            if (c == 'x') {
                StringBuffer var = new StringBuffer(Double.toString(x));
                if (var.length() < 6) {
                    func.replace(i, i + 1, var.toString());
                } else {
                    func.replace(i, i + 1, var.substring(0, 6));
                }
            } else if(c == 'y'){
                StringBuffer var = new StringBuffer(Double.toString(y));
                if (var.length() < 6) {
                    func.replace(i, i + 1, var.toString());
                } else {
                    func.replace(i, i + 1, var.substring(0, 6));
                }
            }
        }
        return func.toString();
    }

    public HashMap<Double, Double> getValues() {
        return values;
    }





}
