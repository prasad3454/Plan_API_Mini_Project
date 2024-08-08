package com.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.plan.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

}
