package com.cresen.poc.docusignpoc.service;

import com.cresen.poc.docusignpoc.config.util.DocuSignRoles;
import com.cresen.poc.docusignpoc.config.util.EnvelopeStatus;
import com.cresen.poc.docusignpoc.config.util.TabFactory;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Component
public class EnvelopeService {
    @Autowired
    private Environment environment;
    public void sendEnvelope(ApiClient apiClient) throws ApiException {

        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);

        // Create envelopeDefinition object
        EnvelopeDefinition envelope = new EnvelopeDefinition();
        envelope.setEmailSubject("Please sign this document set");

        TemplateRole templateRole = new TemplateRole();
        templateRole.setEmail(environment.getProperty("recipientEmail"));
        templateRole.setName(environment.getProperty("recipientName"));
        templateRole.setRoleName("signer");

        List<TemplateRole> templateRoleList = new ArrayList<>();
        templateRoleList.add(templateRole);

        String templateId = environment.getProperty("docusign.envelop.template-id");
        envelope.setTemplateId(templateId);
        envelope.setTemplateRoles(templateRoleList);
        envelope.setStatus("sent");

        EnvelopeSummary results = envelopesApi.createEnvelope(environment.getProperty("docusign.account-id"), envelope);
        System.out.println(results);
    }

    public void sendEnvelopeDynamic(ApiClient apiClient, Map<String, Object> request) throws ApiException, IOException {
        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);

        Document document = getDocument(request);

        Recipients recipients = new Recipients();
        List<Signer> signerList = getSigners(request);
        List<CarbonCopy> carbonCopyRecipientList = getCarbonCopyRecipient(request);
        recipients.setSigners(signerList);
        recipients.setCarbonCopies(carbonCopyRecipientList);




        InlineTemplate inlineTemplate = new InlineTemplate();
        inlineTemplate.setSequence("1");
        inlineTemplate.setRecipients(recipients);
        inlineTemplate.setDocuments(List.of(document));



        CompositeTemplate compositeTemplate = new CompositeTemplate();
        compositeTemplate.setInlineTemplates(List.of(inlineTemplate));


        // Create EnvelopeDefinition object
        EnvelopeDefinition envelope = new EnvelopeDefinition();
        envelope.setEmailSubject(request.get("emailSubject").toString());
        envelope.setCompositeTemplates(List.of(compositeTemplate));
        envelope.setStatus(EnvelopeStatus.SENT.getValue());



        String accountId = environment.getProperty("docusign.account-id");

        setEnvelopeExpire(envelope);

        // Create Envelope
        EnvelopeSummary results = envelopesApi.createEnvelope(accountId, envelope);
        System.out.println(results);

    }

    private void setEnvelopeExpire(EnvelopeDefinition envelope) {
        LocalDate date = LocalDate.now().plusYears(5);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        envelope.setExpireDateTime(formattedDate);
    }

    private Document getDocument(Map<String, Object> request) throws IOException {
        // Define document(s)
        String filePath = environment.getProperty("documentFilePath");
        byte[] pdfBytes = Files.readAllBytes(Paths.get(filePath));

        Document document = new Document();
        document.setDocumentBase64(Base64.getEncoder().encodeToString(pdfBytes));
        String documentName = request.get("documentName").toString();
        document.setName(documentName);
        document.setDocumentId("1");
        document.setFileExtension(documentName.split("\\.")[1]);
        return document;
    }

    private ArrayList<Signer> getSigners(Map<String, Object> request) {
        List<Map<String, Object>> signers = (List<Map<String, Object>>) request.get("signer");
        Boolean isOrderRequired = (Boolean) request.get("isOrderRequired");
        Boolean isTabsPresent = (Boolean) request.get("isTabsPresent");
        ArrayList<Signer> signerList = new ArrayList<>();
        for(Map<String, Object> signerObject : signers) {
            Signer signer = new Signer();
            signer.setEmail(signerObject.get("email").toString());
            signer.setName(signerObject.get("name").toString());
            signer.setRoleName(DocuSignRoles.SIGNER.getValue());
            signer.setRecipientId(signerObject.get("id").toString());
            if(isOrderRequired) {
                signer.setRoutingOrder(signerObject.get("id").toString());
            }
            if(isTabsPresent) {
                setTabs(signer);
            }
            signerList.add(signer);
        }
        return signerList;
    }

    private List<CarbonCopy> getCarbonCopyRecipient(Map<String, Object> request) {
        List<Map<String, Object>> carbonCopy = (List<Map<String, Object>>) request.get("carbonCopy");
        if(carbonCopy == null || carbonCopy.isEmpty()) return null;

        ArrayList<CarbonCopy> carbonCopyList = new ArrayList<>();
        for(Map<String, Object> cb : carbonCopy) {
            CarbonCopy cp = new CarbonCopy();
            cp.setEmail(cb.get("email").toString());
            cp.setName(cb.get("name").toString());
            cp.setRoleName(DocuSignRoles.CARBON_COPY.getValue());
            cp.setRecipientId(cb.get("id").toString());
            carbonCopyList.add(cp);
        }
        return carbonCopyList;
    }


    public void setTabs(Signer signer) {

        Tabs tab = new Tabs();
        SignHere signHereTab =
                TabFactory.createSignHereTab("SignatureTab", "Sign:-", "40", "0", null);
        DateSigned dateSignedTab =
                TabFactory.createDateSignedTab("DateSignedTab", "Date:-", "30", "0", null);
        ;
//        InitialHere initialHereTab =
//                TabFactory.createInitialHereTab("initialHereTab", "initialHere:-", "60", "0", null);


        tab.setDateSignedTabs(List.of(dateSignedTab));
        tab.setSignHereTabs(List.of(signHereTab));
//        tab.setInitialHereTabs(List.of(initialHereTab));
        signer.setTabs(tab);
    }
}