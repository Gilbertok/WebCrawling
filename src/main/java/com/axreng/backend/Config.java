package com.axreng.backend;

import com.axreng.backend.utility.CommonUtils;

import static com.axreng.backend.utility.CommonUtils.isNotNull;
import static com.axreng.backend.utility.CommonUtils.isNumeric;

public class Config {

    private static final int DEFAULT_MAX_RESULTS = -1;
    private final String baseUrl;
    private final String keyword;
    private final Integer maxResults;

    public Config() {
        this.baseUrl = this.baseUrl();
        this.keyword = this.keyword();
        this.maxResults = this.maxResults();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public Integer getMaxResults() {
        return maxResults > 0 ? maxResults : Integer.MAX_VALUE;
    }

    private String baseUrl() {
        String value = System.getenv("BASE_URL");
        if (value != null) {
            return value;
        } else {
            throw new IllegalArgumentException("Missing required environment variable: BASE_URL");
        }
    }

    private String keyword() {
        String value = System.getenv("KEYWORD");
        if (CommonUtils.isValidKEYWORD(value)) {
            return value;
        }
        throw new IllegalArgumentException("Invalid environment variable value: KEYWORD");
    }

    private int maxResults() {
        String value = System.getenv("MAX_RESULTS");
        if (isNotNull(value) && isNumeric(value)) {
            int result = Integer.parseInt(value);
            if (result > 0) {
                return result;
            }
        }
        return DEFAULT_MAX_RESULTS;
    }

}
