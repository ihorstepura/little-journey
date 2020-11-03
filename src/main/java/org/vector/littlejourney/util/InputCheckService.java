package org.vector.littlejourney.util;

public class InputCheckService {

    public static boolean checkFields(String... fields) {

        for (String field : fields) {

            if (field.equals("")) return true;
        }
        return false;
    }
}
