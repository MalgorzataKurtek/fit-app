package com.example.fitApp.service;

import com.example.fitApp.entity.Activity;
import com.example.fitApp.entity.Exercise;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface ActivityService {
    Optional<Activity> findById(Long id);

    List<Exercise> getExercisesByActivityId(Long id);

    List<Activity> getAllActivities();
}
