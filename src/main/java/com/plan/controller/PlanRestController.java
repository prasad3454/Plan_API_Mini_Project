package com.plan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plan.constants.AppConstants;
import com.plan.entity.Plan;
import com.plan.props.AppProperties;
import com.plan.service.PlanServiceImpl;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PlanRestController {

	private PlanServiceImpl planServiceImpl;

	private Map<String, String> messages;

	public PlanRestController(PlanServiceImpl planServiceImpl, AppProperties appProperties) {
		this.planServiceImpl = planServiceImpl;
		this.messages = appProperties.getMessages();
		System.out.println(this.messages);
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {

		Map<Integer, String> categories = planServiceImpl.getPlanCategories();

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String responseMsg = AppConstants.EMPTY_STR;

		boolean isSaved = planServiceImpl.savePlan(plan);

		if (isSaved) {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_SAVE_FAIL);
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {

		List<Plan> allPlans = planServiceImpl.getAllPlans();

		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan plan = planServiceImpl.getPlanById(planId);

		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {

		String responseMsg = AppConstants.EMPTY_STR;

		boolean isUpdated = planServiceImpl.updatePlan(plan);

		if (isUpdated) {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer planId) {

		String responseMsg = AppConstants.EMPTY_STR;

		boolean planDelete = planServiceImpl.deletePlanById(planId);

		if (planDelete) {
			responseMsg = messages.get(AppConstants.PLAN_DELETE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId, @PathVariable String status) {

		boolean planChange = planServiceImpl.planStatusChange(planId, status);

		String responseMsg = AppConstants.EMPTY_STR;

		if (planChange) {
			responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_SUCC);
		} else {
			responseMsg = messages.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}
}
