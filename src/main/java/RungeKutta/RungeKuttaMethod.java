package RungeKutta;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RungeKuttaMethod {
    private HashMap<Double, Double> values = new HashMap<>();

    private final int X = 30;
    private final int Y = 350;
    private int SCALE;
    private int SCALE_Y = 5;
    private double step;  // Переменная для хранения значения для одной риски на графике

    public RungeKuttaMethod(String func, int x, double y, double a, double b, int n) throws ScriptException{
        this.step = (a+b)/n;
        this.SCALE = 270/n;
        this.step /= SCALE;

        calculateRunge(func, x, y, a, b,  n);

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        Toolkit tolls = Toolkit.getDefaultToolkit();
        Dimension dim = tolls.getScreenSize();
        jFrame.setBounds(dim.width/2 - 250, dim.height/2 - 150, 500, 500 );
        jFrame.setResizable(false);
        jFrame.setTitle("Results");
        jFrame.add(new RungeKuttaMethod.MyComponent());
    }

    private void calculateRunge(String func, int x, double y, double a, double b, int n) throws ScriptException {
          double h = (a + b) / n;
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript"); // Подключение библиотеки JavaScript
          for(double i = a; i <= b; i += h){
              values.put(i, y);
              String iStr = String.valueOf(i);
              if(iStr.length() > 3){
                  i = Double.valueOf(iStr.substring(0, 4));
              }
              iStr = null;
              System.gc();
              String currentFunc = functionMaker(func, i, y);
              double k1 = (Double)engine.eval(currentFunc);
              currentFunc = functionMaker(func, i + h/2, y + k1 * h/2 );
              double k2 = (Double)engine.eval(currentFunc);
              currentFunc = functionMaker(func, i + h/2, y + k2 * h/2);
              double k3 = (Double)engine.eval(currentFunc);
              currentFunc = functionMaker(func,i + h,y + h * k3);
              double k4 = (Double)engine.eval(currentFunc);
              y += h*(k1 + 2 * k2 + 2 * k3 + k4)/6;
          }
      }

    private String functionMaker(String str, double x, double y){ // Фунцкия создаёт рабочее математическое выражение из строки
        StringBuffer func = new StringBuffer(str);                // заменяя нейзвестные на числа
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

    private class MyComponent extends JComponent{
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;
            Line2D x_axis = new Line2D.Double(X, Y, Y, Y);
            Line2D y_axis = new Line2D.Double(X, Y, X, X);
            graphics2D.draw(x_axis);
            graphics2D.draw(y_axis);
            double stepX = X - SCALE;
            double stepY = Y;
            graphics2D.setPaint(Color.RED);
            Set<Double> x_es= getValues().keySet(); // Set of x-values
            List<Double> x_list = x_es.stream().sorted((x, y) -> x.compareTo(y)).collect(Collectors.toList());
            for(Double i : x_list){
                System.out.println(i + "    -|-    " + getValues().get(i));
            }
            x_es = null;
            System.gc();
            for(double i : x_list){ //Рисуем график по точкам
                double nextVal = getValues().get(i);
                double nextX = stepX + SCALE;
                double nextY = Y - nextVal/(SCALE_Y * step);
                Line2D eulerLine = new Line2D.Double(stepX, stepY, nextX, nextY);
                graphics2D.draw(eulerLine);
                graphics2D.drawString(String.valueOf(i).substring(0, 3), (float)nextX, (float) 330);
                graphics2D.drawString(String.valueOf(nextVal).substring(0, 3), (float) 7, (float) nextY);

                stepX = nextX;
                stepY = nextY;
            }


        }
    }

    private HashMap<Double, Double> getValues() {
        return values;
    }
}
