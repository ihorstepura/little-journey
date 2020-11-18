package org.vector.littlejourney.exception.entity;

import org.vector.littlejourney.util.constant.StringConstant;
import org.vector.littlejourney.util.constant.station.StationWarning;

public class InvalidStationException extends RuntimeException {

    public InvalidStationException(String message) {

        super(StationWarning.INVALID_STATION + StringConstant.COLON + message);
    }
}
