package com.example.fitApp.service;

import com.example.fitApp.entity.Exercise;
import com.example.fitApp.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> findByActivities_Id(Long id) {
        return exerciseRepository.findByActivities_Id(id);
    }

    @Override
    public String formatDuration(long durationInSeconds) {
        long hours = durationInSeconds / 3600;
        long minutes = (durationInSeconds % 3600) / 60;
        long seconds = durationInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public long getDurationInSeconds(Duration duration) {
        return duration.getSeconds();
    }

}
