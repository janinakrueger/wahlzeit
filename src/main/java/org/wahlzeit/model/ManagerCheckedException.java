package org.wahlzeit.model;
// Class which represents the checked exception which is thrown by the managers
// (right now only PhotoManager), which are the component borders, to the environment

public class ManagerCheckedException extends Exception {
    public ManagerCheckedException(String errorMessage) {
        super(errorMessage);
    }

    public ManagerCheckedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
