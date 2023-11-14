package org.example.global.util;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class InputRequest {
    private String status;
    @Getter
    private String action;
    private String queryString;
    private Map<String, String> paramsMap;

    public InputRequest(String status) {
        paramsMap = new HashMap<>();

        this.status = status;

        String[] statusSplit = status.split("\\?", 2);
        action = statusSplit[0].trim();

        if (statusSplit.length == 1) {
            return;
        }

        queryString = statusSplit[1].trim();

        String[] queryStringSplit = queryString.split("&");

        for (int i = 0; i < queryStringSplit.length; i++) {
            String queryParamString = queryStringSplit[i];
            String[] queryParamStringSplit = queryParamString.split("=");

            String paramName = queryParamStringSplit[0];
            String paramValue = queryParamStringSplit[1];

            paramsMap.put(paramName, paramValue);
        }
    }

    public int getIndexByParam(String paramName, int defaultValue) {
        if (paramsMap.get(paramName) != null) {
            try {
                return Integer.parseInt(paramsMap.get(paramName));
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

}
