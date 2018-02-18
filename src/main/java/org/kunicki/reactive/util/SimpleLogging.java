package org.kunicki.reactive.util;

public interface SimpleLogging {

    private void log(String level, String message) {
        System.out.printf("[%s] %s %s\n", getClass().getSimpleName(), level, message);
    }

    default void info(String message) {
        log("INFO", message);
    }

    default void error(String message) {
        log("ERROR", message);
    }
}
