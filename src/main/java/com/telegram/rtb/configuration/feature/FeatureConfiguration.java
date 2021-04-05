package com.telegram.rtb.configuration.feature;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Feature flags configuration.
 *
 * @author Valentyn Korniienko
 */
@Component
public class FeatureConfiguration {

    @Value("${feature.keywords}")
    private Boolean featureKeywords;

    /**
     * Keywords.
     *
     * @return true or false
     */
    public Boolean isFeatureKeywordsEnabled() {
        return Boolean.TRUE.equals(featureKeywords);
    }

}
