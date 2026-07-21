package com.multagent.decisionengine.approval;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.PathVariable;
import org.springframework.web.bind.PostMapping;
import org.springframework.web.bind.RequestBody;
import org.springframework.web.bind.RequestMapping;
import org.springframework.web.bind.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/approval")
public class ApprovalController {

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('APPROVE_DECISION')")
    public ResponseEntity<String> handleApproval(@PathVariable Long id, @RequestBody Map<String, String> approvalData) {
        return ResponseEntity.ok("Approval processed for decision ID: " + id);
    }
}