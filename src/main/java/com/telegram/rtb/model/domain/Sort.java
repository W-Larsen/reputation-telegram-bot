package com.telegram.rtb.model.domain;

/**
 * Sort mode.
 */
public enum Sort {

    ASC("asc"),

    DESC("desc");

    private String value;

    Sort(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return value
     */
    public String getValue() {
        return value;
    }
}
