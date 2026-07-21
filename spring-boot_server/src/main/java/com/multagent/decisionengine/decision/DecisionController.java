package com.multagent.decisionengine.decision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.*;

import com.multagent.decisionengine.entity.Decision;
import com.multagent.decisionengine.repository.DecisionRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/decision")
public class DecisionController {

    @Autowired
    private DecisionRepository decisionRepository;

    @PostMapping("/run")
    @PreAuthorize("hasRole('RUN_DECISION')")
    public ResponseEntity<Decision> runDecision(@RequestBody Decision decision) {
        // Set timestamps
        decision.setCreatedAt(LocalDateTime.now());
        decision.setUpdatedAt(LocalDateTime.now());
        Decision saved = decisionRepository.save(decision);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('RUN_DECISION')")
    public ResponseEntity<List<Decision>> getHistory() {
        List<Decision> decisions = decisionRepository.findAllByOrderByCreatedAtDesc();
        return ResponseEntity.ok(decisions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RUN_DECISION')")
    public ResponseEntity<Decision> getDecisionById(@PathVariable Long id) {
        return decisionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}