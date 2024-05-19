package com.cresen.poc.docusignpoc.config.util;

import com.docusign.esign.model.*;

public class TabFactory {

    // Method to create SignHere tab with anchor text and position
    public static SignHere createSignHereTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits) {
        SignHere signHereTab = new SignHere();
        signHereTab.setTabLabel(label); // Unique identifier for the tab
        signHereTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            signHereTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            signHereTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
//            signHereTab.setAnchorUnits("inches"); //("pixels");
            signHereTab.setAnchorUnits(anchorUnits); //("pixels");
        return signHereTab;
    }

    // Method to create DateSigned tab with anchor text and position
    public static DateSigned createDateSignedTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits) {
        DateSigned dateSignedTab = new DateSigned();
        dateSignedTab.setTabLabel(label); // Unique identifier for the tab
        dateSignedTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            dateSignedTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            dateSignedTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            dateSignedTab.setAnchorUnits(anchorUnits);
        return dateSignedTab;
    }

    public static Title createTitleTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits){
        Title titleTab = new Title();
        titleTab.setTabLabel(label); // Unique identifier for the tab
        titleTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            titleTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            titleTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            titleTab.setAnchorUnits(anchorUnits);
        return titleTab;
    }


    // Method to create InitialHere tab with anchor text and position
    public static InitialHere createInitialHereTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits) {
        InitialHere initialHereTab = new InitialHere();
        initialHereTab.setTabLabel(label); // Unique identifier for the tab
        initialHereTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            initialHereTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            initialHereTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            initialHereTab.setAnchorUnits(anchorUnits);
        return initialHereTab;
    }

    // Method to create FullName tab with anchor text and position
    public static FullName createFullNameTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits) {
        FullName fullNameTab = new FullName();
        fullNameTab.setTabLabel(label); // Unique identifier for the tab
        fullNameTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            fullNameTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            fullNameTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            fullNameTab.setAnchorUnits(anchorUnits);
        return fullNameTab;
    }

    // Method to create Checkbox tab with anchor text and position
    public static Checkbox createCheckboxTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits) {
        Checkbox checkboxTab = new Checkbox();
        checkboxTab.setTabLabel(label); // Unique identifier for the tab
        checkboxTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            checkboxTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            checkboxTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            checkboxTab.setAnchorUnits(anchorUnits);
        return checkboxTab;
    }

    // Method to create Company tab with anchor text and position
    public static Company createCompanyTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits) {
        Company companyTab = new Company();
        companyTab.setTabLabel(label); // Unique identifier for the tab
        companyTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            companyTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            companyTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            companyTab.setAnchorUnits(anchorUnits);
        return companyTab;
    }

    public static Text createTextTab(String label, String anchorText, String anchorXOffset, String anchorYOffset, String anchorUnits){
        Text textTab = new Text();
        textTab.setTabLabel(label); // Unique identifier for the tab
        textTab.setAnchorString(anchorText); // Anchor text in the document
        if (anchorXOffset != null)
            textTab.setAnchorXOffset(anchorXOffset);
        if (anchorYOffset != null)
            textTab.setAnchorYOffset(anchorYOffset);
        if (anchorUnits != null)
            textTab.setAnchorUnits(anchorUnits);
        return textTab;
    }
}

