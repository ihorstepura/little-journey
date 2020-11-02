package org.vector.littlejourney.utils;

public class InputCheckService {

    public static boolean checkFields(String firstField, String secondField) {

        return firstField.equals("") || secondField.equals("");
    }
}
