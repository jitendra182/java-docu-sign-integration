package com.cresen.poc.docusignpoc.controller;

import com.cresen.poc.docusignpoc.service.DocusignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DocusignController {

    @Autowired
    private DocusignService docusignService;

    @GetMapping("/sendEnvelope")
    public ResponseEntity sendEnvelopeViaDocusign() {
        docusignService.sendEnvelopeViaDocusign();
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/post/sendEnvelope")
    public ResponseEntity sendEnvelopeViaDocusignPost(@RequestBody Map<String, Object> request) {
        docusignService.sendEnvelopeViaDocusign(request);
        return ResponseEntity.ok("SUCCESS");
    }
}
