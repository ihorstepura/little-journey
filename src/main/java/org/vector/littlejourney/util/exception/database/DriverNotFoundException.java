package org.vector.littlejourney.util.exception.database;

import org.vector.littlejourney.util.constant.database.DatabaseConstant;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(String message) {

        super(DatabaseConstant.DRIVER_NOT_FOUND + ":" + message);
    }
}
