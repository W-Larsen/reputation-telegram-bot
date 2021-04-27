package com.telegram.rtb.model.keyword;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum KeywordFunction {

    PLUS("+"),

    MINUS("-");

    private String value;
}
