package org.vector.littlejourney.dal.exception.entity;

import org.vector.littlejourney.dal.constant.StringConstant;
import org.vector.littlejourney.dal.constant.station.StationWarning;

public class InvalidStationException extends RuntimeException {

    public InvalidStationException(String message) {

        super(StationWarning.INVALID_STATION + StringConstant.COLON + message);
    }
}
