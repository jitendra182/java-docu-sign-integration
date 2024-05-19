package com.cresen.poc.docusignpoc.config.util;

public enum DocuSignRoles {
    SIGNER("Signer"),
    APPROVER("Approver"),
    CARBON_COPY("CarbonCopy"),
    CERTIFIED_DELIVERY("CertifiedDelivery"),
    IN_PERSON_SIGNER("InPersonSigner"),
    AGENT("Agent"),
    EDITOR("Editor"),
    INTERMEDIARY("Intermediary");
    private final String value;
    DocuSignRoles(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    // Optional: Create a method to get Enum constant from a String value
    public static DocuSignRoles fromValue(String value) {
        for (DocuSignRoles role : DocuSignRoles.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown EnvelopeStatus value: " + value);
    }
}
