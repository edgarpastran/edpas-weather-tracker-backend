package com.edpas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edpas.dto.Statistic;
import com.edpas.service.IMeasurementService;
import com.edpas.service.IStatisticService;

@RestController
@RequestMapping("/stats")
public class StatisticController {

	@Autowired
	private IStatisticService statisticService;
	
	@GetMapping
	public ResponseEntity<List<Statistic>> list(
			@RequestParam("stat") List<String> stats,
            @RequestParam("metric") List<String> metrics,
            @RequestParam("fromDateTime") Optional<String> fromDateTime,
            @RequestParam("toDateTime") Optional<String> toDateTime) {
		List<Statistic> list = this.statisticService.getStatistics(stats, metrics, fromDateTime, toDateTime);
		return new ResponseEntity<List<Statistic>>(list, HttpStatus.OK);
	}
	
}
