package com.example.fitApp.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "plan")
@Setter
@Getter
public class TrainingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "plan_user",
            joinColumns = {@JoinColumn(name = "PLAN_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")})
    private Collection<User> users;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "plan_activity",
            joinColumns = {@JoinColumn(name = "PLAN_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ACTIVITY_ID", referencedColumnName = "ID")}
    )
    private Collection<Activity> activities;

    @OneToMany(mappedBy = "trainingPlan", fetch = FetchType.LAZY)
    private List<PlanActivity> planActivities;
}
