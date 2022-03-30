package ui;

import model.EventLog;
import model.Event;

import javax.swing.*;
import java.awt.*;

public class ScreenPrinter extends JInternalFrame implements LogPrinter {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private JTextArea logArea;

    public ScreenPrinter(Component parent) {
        super("Event log", false, true, false, false);
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setLocation(parent.getWidth() - WIDTH - 10, parent.getHeight() - HEIGHT - 10);
        setVisible(true);
    }

    @Override
    public void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            logArea.setText(logArea.getText() + next.toString() + "\n\n");
        }

        repaint();
    }
}
