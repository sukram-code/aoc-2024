package lt.sukram.util;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return String.format("[%1$tF %1$tT] [%2$s] %3$s%n",
                record.getMillis(), record.getLevel(), formatMessage(record));
    }
}
