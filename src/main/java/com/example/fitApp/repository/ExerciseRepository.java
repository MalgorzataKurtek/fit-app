package com.example.fitApp.repository;

import com.example.fitApp.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByActivities_Id(Long id);

}

