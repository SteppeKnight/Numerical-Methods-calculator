package ErrorWindow;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow {
    public ErrorWindow(String message){
        JFrame errorWin = new JFrame("Error message");
        JLabel error = new JLabel(message);
        error.setSize(100, 30);
        errorWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Two tools to get properties of screen
        Dimension dimension = toolkit.getScreenSize(); // Get screen size and use it below in placing of window
        errorWin.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 100, 100);
        errorWin.add(error);
        errorWin.setResizable(false);
        errorWin.setVisible(true);
    }
}
