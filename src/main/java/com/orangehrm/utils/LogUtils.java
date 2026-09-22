package com.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    //Explanation: This utility class provides a method to get a logger instance for a given class. It uses Log4j2's LogManager to create and return a logger, which can be used for logging messages in the application.
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }
}