package org.vector.littlejourney.entity.exception;

import org.vector.littlejourney.util.constant.station.StationWarning;

public class InvalidStationException extends RuntimeException {

    public InvalidStationException(String message) {

        super(StationWarning.INVALID_STATION + ":" + message);
    }
}
