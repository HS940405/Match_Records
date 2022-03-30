package ui;

import model.EventLog;

//Interface for printing log
public interface LogPrinter {

    //EFFECTS: print logs in eventLog
    void printLog(EventLog eventLog);
}
