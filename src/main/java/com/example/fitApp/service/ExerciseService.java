package com.example.fitApp.service;

import com.example.fitApp.entity.Exercise;
import jakarta.transaction.Transactional;

import java.time.Duration;
import java.util.List;

@Transactional
public interface ExerciseService {
    List<Exercise> findByActivities_Id(Long id);

    long getDurationInSeconds(Duration duration);

    String formatDuration(long durationInSeconds);

}
