package View;

import javax.swing.*;
import java.awt.*;

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
        JTextField stepH = new HintTextField("Step H");
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

        jPanel.revalidate();
    }

}
