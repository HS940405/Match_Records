package ui;

import model.Event;
import model.EventLog;

public class ConsolePrinter implements LogPrinter {

    private String log;

    @Override
    public void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            log = log + next.toString() + "\n\n";
        }

        System.out.println(log);
    }
}
