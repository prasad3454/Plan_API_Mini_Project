package com.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plan.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
