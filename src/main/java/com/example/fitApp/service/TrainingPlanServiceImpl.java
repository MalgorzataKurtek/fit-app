package com.example.fitApp.service;

import com.example.fitApp.dto.TrainingPlanDTO;
import com.example.fitApp.entity.TrainingPlan;
import com.example.fitApp.entity.User;
import com.example.fitApp.repository.TrainingPlanRepository;
import com.example.fitApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingPlanServiceImpl implements TrainingPlanService {
    private TrainingPlanRepository trainingPlanRepository;
    private UserRepository userRepository;


    public TrainingPlanServiceImpl(TrainingPlanRepository trainingPlanRepository, UserRepository userRepository) {
        this.trainingPlanRepository = trainingPlanRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TrainingPlan> getTrainingPlansByUserEmail(String email) {
        return trainingPlanRepository.findByUsers_Email(email);
    }


    @Override
    public Optional<TrainingPlan> findById(Long id) {
        return trainingPlanRepository.findById(id);
    }

    @Override
    public void saveTrainingPlan(TrainingPlanDTO trainingPlanDTO) {
        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setName(trainingPlanDTO.getName());

        User user = userRepository.findByEmail(trainingPlanDTO.getEmail());
        if (user != null) {
            trainingPlan.setUsers(Arrays.asList(user));
        }
        trainingPlanRepository.save(trainingPlan);
    }
}
