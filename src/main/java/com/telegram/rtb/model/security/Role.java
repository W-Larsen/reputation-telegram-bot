package com.telegram.rtb.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    CREATOR("creator"),

    ADMIN("administrator"),

    MEMBER("member");

    private String value;

}
