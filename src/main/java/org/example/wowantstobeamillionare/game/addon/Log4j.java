package org.example.wowantstobeamillionare.game.addon;

import org.apache.log4j.Logger;

public class Log4j {
    public static final Logger logger = Logger.getLogger(Log4j.class);
    static void main(String[] args) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
    }
}
