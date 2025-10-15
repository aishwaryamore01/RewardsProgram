package com.infy.api;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.validation.annotation.Validated;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import com.infy.dto.RewardRequestDTO;
import com.infy.dto.RewardResponseDTO;
import com.infy.exception.RewardprogramCustomerException;
import com.infy.service.RewardsService;

import jakarta.validation.Valid;


	@RestController
	@RequestMapping("/api/rewards")
	@Validated
	public class RewardsController {
	 @Autowired
	 private RewardsService rewardsService;
	 

	   @PostMapping("/customer")
	   public RewardResponseDTO getRewards(@RequestBody @Valid RewardRequestDTO request) throws RewardprogramCustomerException {
	       if (request.getCustomerId() == null || request.getStartDate() == null || request.getEndDate() == null) {
	           throw new IllegalArgumentException("Customer ID, start date, and end date must be provided.");
	       }

	       if (request.getStartDate().isAfter(request.getEndDate())) {
	           throw new IllegalArgumentException("Start date cannot be after end date.");
	       }

	       return rewardsService.calculateRewards(request.getCustomerId(), request.getStartDate(), request.getEndDate());
	   }

	}


