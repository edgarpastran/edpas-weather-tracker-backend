package com.edpas.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edpas.dto.Statistic;
import com.edpas.repository.IStatisticRepository;
import com.edpas.service.IStatisticService;
import com.edpas.util.MyLocalDateTimeConverter;

@Service
public class StatisticService implements IStatisticService {

	@Autowired
	private IStatisticRepository repository;
	
	private String getFunctionName(String candidate) {
		candidate = candidate.toLowerCase().trim();
		String functionName = candidate;
		if (candidate.equals("average")) {
			functionName = "avg";
		}
		return functionName;
	}
	
	private Statistic getStatistic(String stat, String metric, Optional<String> fromDateTime, Optional<String> toDateTime) {
		Float value = null;
		Optional<LocalDateTime> from = Optional.ofNullable(null);
		Optional<LocalDateTime> to = Optional.ofNullable(null);
		if (fromDateTime.isPresent()) {
			from = Optional.ofNullable(MyLocalDateTimeConverter.converToLocalDateTime(fromDateTime.get()));			
		}
		if (toDateTime.isPresent()) {
			to = Optional.ofNullable(MyLocalDateTimeConverter.converToLocalDateTime(toDateTime.get()));
		}
		value = this.repository.getStatistic(this.getFunctionName(stat), metric, from, to);
		System.out.println(value+" - "+value.getClass().toString());
		
		if (value != null) {
			return new Statistic(metric, stat, value);
		}
		return null;
	}
	
	@Override
	public List<Statistic> getStatistics(List<String> stats, List<String> metrics, Optional<String> fromDateTime, Optional<String> toDateTime) {
		List<Statistic> statistics = new ArrayList<Statistic>();
		stats.forEach(stat -> {
			metrics.forEach(metric -> {
				Statistic statistic = this.getStatistic(stat, metric, fromDateTime, toDateTime);
				if (statistic != null) {
					statistics.add(statistic);
				}
			});
		});
		return statistics;
	}

}
