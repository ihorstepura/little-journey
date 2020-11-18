package org.vector.littlejourney.util.constant;

public enum Extension {

    TXT("txt"), DOCX("docx"), XLSX("xlsx");

    private final String value;

    Extension(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }
}
