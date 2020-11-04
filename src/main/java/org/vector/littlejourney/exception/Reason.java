package org.vector.littlejourney.exception;

public enum Reason {

    NULL_REFERENCE("Object cannot be referenced to null"),

    LESS_THAN_ZERO_NUMBER_INPUT("Input number cannot be less than 0");

    private final String reason;

    Reason(String reason) {

        this.reason = reason;
    }

    public String getReason() {

        return reason;
    }
}
