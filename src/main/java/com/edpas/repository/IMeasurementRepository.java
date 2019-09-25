package com.edpas.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edpas.model.Measurement;

@Repository
public interface IMeasurementRepository extends JpaRepository<Measurement, Integer> {

	public Measurement findOneByTimestamp(LocalDateTime timestamp);
		/*
	@Query(value = 
			"SELECT :stat as result " + 
			"FROM measurement_detail md, measurement m " + 
			"WHERE md.id_measurement = m.id_measurement " + 
			"AND m.timestamp BETWEEN :fromDateTime AND :toDateTime " + 
			"AND md.metric = :metric", nativeQuery = true)
	public Float getStatisticsByFromDateTimeAndToDateTime(@Param("stat") String stat, @Param("metric") String metric, @Param("fromDateTime") LocalDateTime fromDateTime, @Param("toDateTime") LocalDateTime toDateTime);
	
	@Query(value = 
			"SELECT :stat as result " + 
			"FROM measurement_detail md, measurement m " + 
			"WHERE md.id_measurement = m.id_measurement " + 
			"AND m.timestamp >= :fromDateTime " + 
			"AND md.metric = :metric", nativeQuery = true)
	public Float getStatisticsByFromDateTime(@Param("stat") String stat, @Param("metric") String metric, @Param("fromDateTime") LocalDateTime fromDateTime);
	
	@Query(value = 
			"SELECT :stat as result " + 
			"FROM measurement_detail md, measurement m " + 
			"WHERE md.id_measurement = m.id_measurement " + 
			"AND m.timestamp < :toDateTime " + 
			"AND md.metric = :metric", nativeQuery = true)
	public Float getStatisticsByToDateTime(@Param("stat") String stat, @Param("metric") String metric, @Param("toDateTime") LocalDateTime toDateTime);
	
	@Query(value = 
			"SELECT min(value) as result " + 
			"FROM measurement_detail md, measurement m " + 
			"WHERE md.id_measurement = m.id_measurement " +  
			"AND md.metric = :metric", nativeQuery = true)
	public Float getStatistics(@Param("stat") String stat, @Param("metric") String metric);
	*/
}
