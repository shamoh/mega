package cz.kramolis.mega.runtime.logging.internal;

import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MegaFormatter extends Formatter {

    private final MessageFormat messageFormat = new MessageFormat("[{0,date}, {0,time}] {3} ({4}) [{1}#{2}] {5}\n");

    public String format(LogRecord record) {
        Object[] arguments = new Object[6];
        arguments[0] = new Date(record.getMillis());
        arguments[1] = record.getSourceClassName();
        arguments[2] = record.getSourceMethodName();
        arguments[3] = record.getLevel();
        //arguments[4] = Long.toString( Thread.currentThread().getId() );
        arguments[4] = Thread.currentThread().getName();
        arguments[5] = record.getMessage();

        return messageFormat.format(arguments);
    }

}
