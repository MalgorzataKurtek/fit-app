package com.example.fitApp.repository;

import com.example.fitApp.entity.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {
    List<TrainingPlan> findByUsers_Email(String email);

}
