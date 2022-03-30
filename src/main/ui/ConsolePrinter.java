package ui;

import model.Event;
import model.EventLog;

//print log in console
public class ConsolePrinter implements LogPrinter {

    private String log;

    //constructor
    //EFFECTS: create ConsolePrinter
    public ConsolePrinter() {
        log = "";
    }

    //EFFECTS: convert event to String(log) and print logs to console
    @Override
    public void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            log = log + next.toString() + "\n\n";
        }

        System.out.println(log);
    }
}
