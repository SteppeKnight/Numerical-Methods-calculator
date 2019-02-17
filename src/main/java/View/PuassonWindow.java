package View;

import ErrorWindow.ErrorWindow;
import PuassonEquation.PuassonEquation;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class PuassonWindow {

    private JPanel jPanel;
    private JTextField lastCond;
    private JTextField zeroCond;
    private JTextField nField;
    private JTextField FiField;
    private JTextField alphaField;
    private JTextField bettaField;
    private JButton calculateButton;
    private JLabel formulas;
    private JLabel textFieldLabel;

    public PuassonWindow(){

        JFrame jFrame = new JFrame();
        jFrame.setContentPane(this.jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setTitle("Puasson equation");
        jFrame.setLocation(400, 250);


        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Documents\\Projects\\IdealProject\\numericalMethods2\\src\\main\\resources\\Puasson.jpg");
        formulas.setText("");
        formulas.setIcon(icon);
        jFrame.pack();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String P = zeroCond.getText();
                    int toSub1 = P.indexOf('(') + 1;
                    int toSub2 = P.indexOf(')');
                    int toSub3 = P.indexOf('=') + 1;
                    double x0 = Double.parseDouble(P.substring(toSub1, toSub2));
                    double p0 = Double.parseDouble(P.substring(toSub3));
                    P = lastCond.getText();
                    toSub1 = P.indexOf('(') + 1;
                    toSub2 = P.indexOf(')');
                    toSub3 = P.indexOf('=') + 1;
                    double x1 = Double.parseDouble(P.substring(toSub1, toSub2));
                    double p1 = Double.parseDouble(P.substring(toSub3));


                    int n = Integer.parseInt(nField.getText());
                    double Fi = Double.parseDouble(FiField.getText());
                    double alpha = Double.parseDouble(alphaField.getText());
                    double betta = Double.parseDouble(bettaField.getText());

                    PuassonEquation equation = new PuassonEquation(x0, p0, x1, p1, Fi, n, alpha, betta);

                    Map<Integer, Double> map = equation.getP();
                    Set<Integer> keys = map.keySet();

                    createAnswer(map, keys);

                } catch (NumberFormatException n){
                    new ErrorWindow("Try to write '.' instead of ','.");
                }
            }
        });


    }

    private void createAnswer(Map<Integer, Double> map, Set<Integer> set){
        int keyNo = set.size();
        JFrame answer = new JFrame();
        answer.setTitle("Answer");
        JTable table = new JTable(keyNo + 1, 2);
        Iterator<Integer> iterator = set.iterator();
        table.setValueAt('X', 0, 0);
        table.setValueAt('P', 0, 1);

        for(int i = 1; i <= keyNo; i++){
            int key = iterator.next();
            table.setValueAt(key, i, 0);
            table.setValueAt(map.get(key), i, 1);
        }

        JScrollPane sp = new JScrollPane(table);
        answer.setLocation(300, 300);
        answer.add(sp);
        // Frame Size
        answer.setSize(300, 200);
        // Frame Visible = true
        answer.setVisible(true);
    }

}
