package com.english.dao;

public class DAOException extends Exception {
    public DAOException(String message, Throwable e) {
        super(message, e);
    }

    public DAOException(String message) {
        super(message);
    }
}
