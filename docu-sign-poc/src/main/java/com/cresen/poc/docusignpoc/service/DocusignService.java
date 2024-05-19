package com.cresen.poc.docusignpoc.service;

import com.cresen.poc.docusignpoc.config.ApiClientConfiguration;
import com.cresen.poc.docusignpoc.config.util.EnvelopeStatus;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class DocusignService {

    @Autowired
    private EnvelopeService envelopeService;
    @Autowired
    private ApiClientConfiguration apiClientConfiguration;

    public void sendEnvelopeViaDocusign() {
        ApiClient apiClient = apiClientConfiguration.configureApiClient();
        try {
            envelopeService.sendEnvelope(apiClient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEnvelopeViaDocusign(Map<String, Object> request) {
        ApiClient apiClient = apiClientConfiguration.configureApiClient();
        try {
            envelopeService.sendEnvelopeDynamic(apiClient, request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Value("${docusign.account-id}")
    private String accountId;

    @PostConstruct
    public void startUp() {

        ApiClient apiClient = apiClientConfiguration.configureApiClient();
        EnvelopesApi envelopesApi = new EnvelopesApi(apiClient);
        String envelopeId = "";
        try {
            Envelope envelope = envelopesApi.getEnvelope(accountId, envelopeId);
            System.out.println(envelope);
            String envelopeStatus = envelope.getStatus();
            EnvelopeStatus envelopeStatusValue = EnvelopeStatus.fromValue(envelopeStatus);
            switch (envelopeStatusValue) {
                case COMPLETED:
                    System.out.println("COMPLETED");
                    String documentId = "1";

                    byte[] documentBytes = envelopesApi.getDocument(accountId, envelopeId, documentId);
                    try (FileOutputStream fos = new FileOutputStream("C://Jitendra//POC//" + documentId + ".pdf")) {
                        fos.write(documentBytes);
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    break;

                case SIGNED:
                    System.out.println("SIGNED");
                    break;
                case VOIDED:
                    System.out.println("DECLINED");
                    break;
                case DECLINED:
                    System.out.println("DECLINED");
                    break;
            }
        }
        catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
