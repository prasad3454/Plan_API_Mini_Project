package com.plan.service;

import java.util.List;
import java.util.Map;

import com.plan.entity.Plan;

public interface PlanService {
	
	public Map<Integer, String> getPlanCategories();
	
	public boolean savePlan(Plan plan);
	
	public List<Plan> getAllPlans();
	
	public Plan getPlanById(Integer planId);
	
	public boolean updatePlan(Plan plan);
	
	public boolean deletePlanById(Integer id);
	
	public boolean planStatusChange(Integer planId, String status);
	
}
