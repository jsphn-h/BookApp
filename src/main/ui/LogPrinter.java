package ui;

import model.EventLog;
import exception.LogException;

// Defines behaviors that event log printers must support
public interface LogPrinter {
    void printLog(EventLog el) throws LogException;
}
