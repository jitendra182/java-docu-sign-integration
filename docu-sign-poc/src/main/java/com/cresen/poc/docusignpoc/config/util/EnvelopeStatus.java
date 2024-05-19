package com.cresen.poc.docusignpoc.config.util;

public enum EnvelopeStatus {
    CREATED("created"),
    SENT("sent"),
    DELIVERED("delivered"),
    SIGNED("signed"),
    COMPLETED("completed"),
    DECLINED("declined"),
    VOIDED("voided"),
    TIMED_OUT("timed_out");

    private final String value;

    EnvelopeStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Optional: Create a method to get Enum constant from a String value
    public static EnvelopeStatus fromValue(String value) {
        for (EnvelopeStatus status : EnvelopeStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown EnvelopeStatus value: " + value);
    }
}
