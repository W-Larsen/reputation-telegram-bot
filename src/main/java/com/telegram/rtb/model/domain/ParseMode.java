package com.telegram.rtb.model.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Parse mode.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParseMode {

    MARKDOWN("Markdown"),

    MARKDOWN_V2("MarkdownV2"),

    HTML("HTML");

    private String value;

    /**
     * Gets parse mode value.
     *
     * @return parse mode value
     */
    public String getValue() {
        return value;
    }

}
