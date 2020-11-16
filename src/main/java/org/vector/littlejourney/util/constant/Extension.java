package org.vector.littlejourney.util.constant;

public enum Extension {

//    TXT, DOCX, XLSX

    TXT("txt"), DOCX("docx"), XLSX("xlsx");

    private final String value;

    Extension(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }

    //TODO:: think about it

    /*TXT("txt"), DOCX("docx"), XLSX;

    private String value;

    Extension(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Extension getExtension(String value){

        return Extension.valueOf(value);
    }*/
}
