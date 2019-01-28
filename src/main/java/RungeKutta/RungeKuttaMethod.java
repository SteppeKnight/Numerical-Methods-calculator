package RungeKutta;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class RungeKuttaMethod {
    private HashMap<Double, Double> values = new HashMap<Double, Double>();
    private HashMap<Double, Double> valuesZ = new HashMap<Double, Double>();

    private double func(double x, double y){
        return 0.25 * y * y + x * x;
    }

    private double func2(double x, double y){
        return y - x;
    }
    private double func3(double x, double y){
        return y/x - y * y;
    }

    private double func4(double x, double y){
        return y/x - y * y;
    }

    private double func5(double x, double y, double z){
        return z + 1;
    }
    private  double func52 (double x, double y, double z){
        return y - x;
    }

    public void calculateRunge(int x, double y, double a, double b, int n){
          double h = (a + b) / n;
          for(double i = a; i <= b; i += h){
              values.put(i, y);
              double k1 = func2(i, y);
              double k2 = func2(i + h/2, y + k1 * h/2 );
              double k3 = func2(i + h/2, y + k2 * h/2);
              double k4 = func2(i + h, y + h * k3);

              y += h*(k1 + 2 * k2 + 2 * k3 + k4)/6;
          }
      }

    public HashMap<Double, Double> getValuesZ() {
        return valuesZ;
    }

    public void calculateRungeSys(int x, double y, double z, double a, double b, int n){
          double h = (a + b) / n;
          for(double i = a; i <= b; i += h){
              values.put(i, y);
              valuesZ.put(i, z);
              double k1 = func5(i, y, z);
              double k2 = func5(i + h/2, y + k1 * h/2, z+ k1 * h/2);
              double k3 = func5(i + h/2, y + k2 * h/2, z+ k1 * h/2);
              double k4 = func5(i + h, y + h * k3, z + h * k3);

              y += h*(k1 + 2 * k2 + 2 * k3 + k4)/6;

              double k12 = func52(i, y, z);
              double k22 = func52(i + h/2, y + k1 * h/2, z+ k1 * h/2);
              double k32 = func52(i + h/2, y + k2 * h/2, z+ k1 * h/2);
              double k42 = func52(i + h, y + h * k3, z + h * k3);

              z += h*(k12 + 2 * k22 + 2 * k32 + k42)/6;

          }
      }

    public static void main(String[] args) {
        System.out.println("Enter the x0, y0, a, b ,z and n");
        Scanner scanner = new Scanner(System.in);
        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod();
        int x = scanner.nextInt();
        double y = scanner.nextDouble();
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double z = scanner.nextDouble();
        int n = scanner.nextInt();
        rungeKuttaMethod.calculateRungeSys(x, y,z, a, b, n);
        //rungeKuttaMethod.calculateRunge(x, y, a, b, n);
        Set<Double> keys = rungeKuttaMethod.getValues().keySet();
        List<Double> x_list = keys.stream().sorted((k, t) -> k.compareTo(t)).collect(Collectors.toList());
        System.out.println("Func Y: ");
        for(Double i : x_list){
            System.out.println(i + "    -|-    " + rungeKuttaMethod.getValues().get(i));

        }
        System.out.println(" ");
        System.out.println("Func Z: ");
        for (Double i : x_list){
            System.out.println(i + "    -|-    " + rungeKuttaMethod.getValuesZ().get(i));
        }


    }

    public HashMap<Double, Double> getValues() {
        return values;
    }
}
