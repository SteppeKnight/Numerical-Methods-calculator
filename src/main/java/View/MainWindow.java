package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public static void main(String[] args) {
        final JFrame jFrame = getJFrame();
        final JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        FlowLayout fl = new FlowLayout();
        fl.setHgap(30);
        fl.setVgap(10);
        jPanel.setLayout(fl); // Установка упорядочивателя

        Font font = new Font("Berlin Sans FB", Font.ITALIC, 17);
        JLabel mainLabel = new JLabel("Choose your problem:"); // Надпись в начале
        mainLabel.setFont(font);

        JButton eulerDiffEq = new JButton("Euler's dif.eq"); // Создание кнопки для методы Эйлера
        eulerDiffEq.setSize(30, 10);

        JButton rungeKutta = new JButton("Runge-Kutta meth.");
        rungeKutta.setSize(30,10);
        rungeKutta.setLocation(mainLabel.getX(), mainLabel.getY() + 30);

        JButton nextMeth = new JButton("Next time");
        nextMeth.setSize(30, 10);

        jPanel.add(mainLabel);
        jPanel.add(eulerDiffEq);
        jPanel.add(rungeKutta);
        jPanel.add(nextMeth);

        eulerDiffEq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                EulerWindow eulerWindow = new EulerWindow();

                jFrame.dispose();
            }
        });
    }


    static JFrame getJFrame(){ // Создание пустого окна
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Two tools to get properties of screen
        Dimension dimension = toolkit.getScreenSize(); // Get screen size and use it below in placing of window
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 500, 400 );
        jFrame.setResizable(false);
        jFrame.setTitle("SteppeKnight NMC-2");
        return  jFrame;
    }

}
