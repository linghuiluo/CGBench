package de.fraunhofer.iem.springbench.handlerinterceptoradapterposthandle.LOGGER;

import java.util.logging.Logger;

public class MyLogger {
    private static final Logger logger = Logger.getLogger("MyLogger");

    public static void writeLog(String message) {
        logger.info(message);
    }
}
