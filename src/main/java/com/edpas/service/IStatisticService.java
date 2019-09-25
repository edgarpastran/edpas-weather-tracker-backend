package com.edpas.service;

import java.util.List;
import java.util.Optional;

import com.edpas.dto.Statistic;

public interface IStatisticService {

	public List<Statistic> getStatistics(List<String> stats, List<String> metrics, Optional<String> fromDateTime, Optional<String> toDateTime);
}
