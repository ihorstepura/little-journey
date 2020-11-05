package org.vector.littlejourney.exception.station;

import org.vector.littlejourney.constant.station.StationWarning;

public class InvalidStationException extends RuntimeException {

    public InvalidStationException(String message) {

        super(StationWarning.INVALID_STATION + message);
    }
}
