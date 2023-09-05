package com.example.fitApp.controller;

import com.example.fitApp.dto.TrainingPlanDTO;
import com.example.fitApp.dto.UserDTO;
import com.example.fitApp.entity.Activity;
import com.example.fitApp.entity.Exercise;
import com.example.fitApp.entity.TrainingPlan;
import com.example.fitApp.entity.User;
import com.example.fitApp.service.ActivityService;
import com.example.fitApp.service.ExerciseService;
import com.example.fitApp.service.TrainingPlanService;
import com.example.fitApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AllController {

    private UserService userService;
    private TrainingPlanService trainingPlanService;
    private ExerciseService exerciseService;
    private ActivityService activityService;

    public AllController(UserService userService, TrainingPlanService trainingPlanService, ExerciseService exerciseService, ActivityService activityService) {
        this.userService = userService;
        this.trainingPlanService = trainingPlanService;
        this.exerciseService = exerciseService;
        this.activityService = activityService;
    }


    @GetMapping("/index")
    public String home() {
        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDTO.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "An account with this email address already exists.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "/register";
        }

        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("email", principal.getName());
        }
        return "userProfile";
    }

    @GetMapping("/show-user-plans")
    public String appPlans(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            List<TrainingPlan> userTrainingPlans = trainingPlanService.getTrainingPlansByUserEmail(email);
            model.addAttribute("email", email);
            model.addAttribute("userTrainingPlans", userTrainingPlans);
        }
        return "show-user-plans";
    }

    @GetMapping("/add-plan")
    public String addPlanForm(Model model) {
        TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
        model.addAttribute("plan", trainingPlanDTO);
        return "add-plan";
    }

    @PostMapping("/add-plan/save")
    public String saveTrainingPlan(@Valid @ModelAttribute("plan") TrainingPlanDTO trainingPlanDTO,
                                   BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("plan", trainingPlanDTO);
            return "add-plan";
        }
        trainingPlanDTO.setEmail(principal.getName());
        trainingPlanService.saveTrainingPlan(trainingPlanDTO);
        return "redirect:/show-user-plans";
    }

    @GetMapping("/plan-details/{id}")
    public String showTrainingPlan(@PathVariable Long id, Model model, Principal principal) {
        Optional<TrainingPlan> trainingPlanOptional = trainingPlanService.findById(id);
        if (trainingPlanOptional.isPresent()) {
            TrainingPlan trainingPlan = trainingPlanOptional.get();

            model.addAttribute("trainingPlan", trainingPlan);

            if (principal != null) {
                String userEmail = principal.getName();
                model.addAttribute("userEmail", userEmail);
            }
            return "plan-details";
        } else {
            return "errorPage";
        }
    }


    @GetMapping("/activity-exercises/{id}")
    public String showExercisesForActivity(@PathVariable Long id, Model model, Principal principal) {
        Optional<Activity> optionalActivity = activityService.findById(id);

        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            List<Exercise> exercises = exerciseService.findByActivities_Id(id);

            exercises.forEach(exercise -> {
                long durationInSeconds = exercise.getDuration();
                String formattedDuration = exerciseService.formatDuration(durationInSeconds);
                exercise.setFormattedDuration(formattedDuration);
            });

            model.addAttribute("activity", activity);
            model.addAttribute("exercises", exercises);

            if (principal != null) {
                String userEmail = principal.getName();
                model.addAttribute("userEmail", userEmail);
            }
            return "activity-exercises";
        } else {
            return "activity-not-found";
        }
    }


    @PostMapping("/delete-plan/{id}")
    public String deleteTrainingPlan(@PathVariable Long id) {
        trainingPlanService.deleteById(id);
        return "redirect:/show-user-plans";
    }

    @GetMapping("/show-activity")
    public String showActivity(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            List<Activity> allActivities = activityService.getAllActivities();
            model.addAttribute("email", email);
            model.addAttribute("allActivities", allActivities);

        }
        return "show-activity";
    }

    @GetMapping("/show-exercises/{id}")
    public String showExercises(@PathVariable Long id, Model model, Principal principal) {
        Optional<Activity> optionalActivity = activityService.findById(id);

        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            List<Exercise> exercises = exerciseService.findByActivities_Id(id);

            model.addAttribute("activity", activity);
            model.addAttribute("exercises", exercises);

            if (principal != null) {
                String userEmail = principal.getName();
                model.addAttribute("userEmail", userEmail);
            }
            return "show-exercises";
        } else {
            return "errorPage";
        }
    }
}