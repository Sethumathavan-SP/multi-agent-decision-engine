package com.multagent.decisionengine.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.GetMapping;
import org.springframework.web.bind.RequestMapping;
import org.springframework.web.bind.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @GetMapping("")
    @PreAuthorize("hasRole('VIEW_DASHBOARD')")
    public ResponseEntity<String> getDashboard() {
        return ResponseEntity.ok("Dashboard data");
    }
}