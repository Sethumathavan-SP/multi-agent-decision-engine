package com.multagent.decisionengine.repository;

import com.multagent.decisionengine.entity.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DecisionRepository extends JpaRepository<Decision, Long> {
    List<Decision> findAllByOrderByCreatedAtDesc();
}