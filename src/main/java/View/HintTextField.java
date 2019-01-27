package View;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class HintTextField extends JTextField implements FocusListener {

    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    public void focusGained(FocusEvent e) {
        if(this.getText().equals("")) {
            super.setText("");
            showingHint = false;
        }
    }

    public void focusLost(FocusEvent e) {
        if(this.getText().equals("")) {
            super.setText(hint);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}
