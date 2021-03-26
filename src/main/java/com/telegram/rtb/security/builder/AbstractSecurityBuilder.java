package com.telegram.rtb.security.builder;

import java.util.List;
import java.util.Map;

public abstract class AbstractSecurityBuilder {

    public abstract AbstractSecurityBuilder apply();

    public abstract Map<String, List<String>> getCommandPermissions();
}
