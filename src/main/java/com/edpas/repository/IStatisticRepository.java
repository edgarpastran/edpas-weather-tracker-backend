package com.edpas.repository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IStatisticRepository {

	public Float getStatistic(String stat, String metric,  Optional<LocalDateTime> fromDateTime, Optional<LocalDateTime> toDateTime);
}
