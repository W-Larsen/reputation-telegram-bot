package com.telegram.drb.model.domain;

/**
 * Parse mode.
 */
public enum ParseMode {

    MARKDOWN("Markdown"),

    MARKDOWN_V2("MarkdownV2"),

    HTML("HTML");

    private String value;

    ParseMode(String value) {
        this.value = value;
    }

    /**
     * Gets parse mode value.
     *
     * @return parse mode value
     */
    public String getValue() {
        return value;
    }

}
