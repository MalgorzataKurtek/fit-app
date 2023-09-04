package com.example.fitApp.service;

import com.example.fitApp.dto.TrainingPlanDTO;
import com.example.fitApp.entity.TrainingPlan;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TrainingPlanService {

    List<TrainingPlan> getTrainingPlansByUserEmail(String email);

    Optional<TrainingPlan> findById(Long id);

    void saveTrainingPlan(TrainingPlanDTO trainingPlanDTO);
}
