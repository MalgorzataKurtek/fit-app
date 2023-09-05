package com.example.fitApp.service;

import com.example.fitApp.entity.Activity;
import com.example.fitApp.entity.Exercise;
import com.example.fitApp.repository.ActivityRepository;
import com.example.fitApp.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;
    private ExerciseRepository exerciseRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository, ExerciseRepository exerciseRepository) {
        this.activityRepository = activityRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return activityRepository.findById(id);
    }

    public List<Exercise> getExercisesByActivityId(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with ID: " + id));
        return exerciseRepository.findByActivities_Id(id);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}
