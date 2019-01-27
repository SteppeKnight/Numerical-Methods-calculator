package View;

import EulerMethod.EulerMethod;
import  ErrorWindow.ErrorWindow;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EulerWindow {
    JFrame jFrame = new JFrame();
    public EulerWindow(){
        jFrame = MainWindow.getJFrame();
        jFrame.setSize(300, 500);
        jFrame.setResizable(false);
        final JPanel jPanel = new JPanel();
        jFrame.add(jPanel);



        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        jPanel.setLayout(gridBagLayout);

        // Редактирование размещения элементов в окне
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.fill   = GridBagConstraints.NONE;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth  = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.insets = new Insets(40, 0, 30, 0);
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;

        JLabel eqLabel = new JLabel("Enter your equation: ");
        eqLabel.setFont(new Font("Berlin Sans FB", Font.ITALIC, 20));
        gridBagLayout.setConstraints(eqLabel, gridBagConstraints);
        jPanel.add(eqLabel);

        gridBagConstraints.insets = new Insets(5, 10, 30, 10);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JTextField equation = new JTextField("y' = ");
        equation.setSize(200, 30);
        gridBagLayout.setConstraints(equation ,gridBagConstraints);
        jPanel.add(equation);

        gridBagConstraints.insets = new Insets(5, 2, 20, 2);
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        JLabel intLabel = new JLabel("Enter the domain of 'x': ");
        eqLabel.setFont(new Font("Berlin Sans FB", Font.ITALIC, 20));
        gridBagLayout.setConstraints(intLabel, gridBagConstraints);
        jPanel.add(intLabel);

        gridBagConstraints.insets = new Insets(5, 2, 20, 10);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 30;

        JTextField interval1 = new JTextField();
        interval1.setSize(50, 30);
        gridBagLayout.setConstraints(interval1 ,gridBagConstraints);
        jPanel.add(interval1);

        gridBagConstraints.insets = new Insets(5, 2, 20, 10);

        JTextField interval2 = new JTextField();
        interval2.setSize(50, 30);
        gridBagLayout.setConstraints(interval2, gridBagConstraints);
        jPanel.add(interval2);

        gridBagConstraints.gridy = interval2.getY() + 5;
        gridBagConstraints.insets = new Insets(5, 100, 20, 2);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        JTextField stepH = new HintTextField("Parts");
        stepH.setSize(50, 30);
        gridBagLayout.setConstraints(stepH ,gridBagConstraints);
        jPanel.add(stepH);

        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill   = GridBagConstraints.NONE;
        gridBagConstraints.gridx = GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new Insets(5, 10, 10, 3);
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        JLabel condLabel = new JLabel("Enter the initial condition: ");
        eqLabel.setFont(new Font("Berlin Sans FB", Font.ITALIC, 20));
        gridBagLayout.setConstraints(condLabel, gridBagConstraints);
        jPanel.add(condLabel);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(5, 50, 20, 50);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = GridBagConstraints.NONE;


        JTextField init1 = new HintTextField("x = ... ");
        init1.setSize(50, 30);
        gridBagLayout.setConstraints(init1 ,gridBagConstraints);
        jPanel.add(init1);

        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(5, 2, 20, 2);

        JTextField init2 = new HintTextField("y(x) = ... ");
        init2.setSize(50, 30);
        gridBagLayout.setConstraints(init2, gridBagConstraints);
        jPanel.add(init2);

        gridBagConstraints.insets = new Insets(2, 30, 5, 2);
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JButton calculate = new JButton("Evaluate");
        calculate.setSize(40, 15);
        gridBagConstraints.gridwidth = 1;
        calculate.setBackground(new Color(127, 255, 212));
        gridBagLayout.setConstraints(calculate, gridBagConstraints);
        jPanel.add(calculate);

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String function = equation.getText().substring(5);
                try {
                    int a = Integer.valueOf(interval1.getText());
                    int b = Integer.valueOf(interval2.getText());
                    int x = Integer.valueOf(init1.getText());
                    double y = Double.valueOf(init2.getText());
                    int n = Integer.valueOf(stepH.getText());
                    new EulerMethod(function, x, y, a, b, n);
                }  catch (NumberFormatException e3) {
                    e3.printStackTrace();
                    new ErrorWindow("Wrong input format!");
                } catch (ScriptException e1){
                    e1.printStackTrace();
                    new ErrorWindow("Something wrong with JavaScript, please, check your initial data.");
                } catch (Exception e2){
                    e2.printStackTrace();
                    new ErrorWindow("Check initial data, if it's not working... well, I'm sorry :(");
                }
            }
        });

        jPanel.revalidate();
    }

}
