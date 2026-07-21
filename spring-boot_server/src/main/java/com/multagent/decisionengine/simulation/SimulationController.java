package com.multagent.decisionengine.simulation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.PostMapping;
import org.springframework.web.bind.RequestBody;
import org.springframework.web.bind.RequestMapping;
import org.springframework.web.bind.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/simulation")
public class SimulationController {

    @PostMapping("")
    @PreAuthorize("hasRole('RUN_SIMULATION')")
    public ResponseEntity<String> runSimulation(@RequestBody Map<String, Object> simulationData) {
        return ResponseEntity.ok("Simulation started");
    }
}