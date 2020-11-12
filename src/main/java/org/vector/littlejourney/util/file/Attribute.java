package org.vector.littlejourney.util.file;

public class Attribute {

    private String attribute;

    private boolean necessarily;

    private boolean allowedEmpty;

    public String getName() {
        return attribute;
    }

    public void setName(String attribute) {
        this.attribute = attribute;
    }

    public boolean isNecessarily() {
        return necessarily;
    }

    public void setNecessarily(boolean necessarily) {
        this.necessarily = necessarily;
    }

    public boolean isAllowedEmpty() {
        return allowedEmpty;
    }

    public void setAllowedEmpty(boolean allowedEmpty) {
        this.allowedEmpty = allowedEmpty;
    }
}
