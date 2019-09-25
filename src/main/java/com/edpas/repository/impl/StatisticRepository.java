package com.edpas.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.edpas.repository.IStatisticRepository;
import com.edpas.util.MyLocalDateTimeConverter;

@Repository
public class StatisticRepository implements IStatisticRepository {

	@PersistenceContext
    private EntityManager em;
	
	
	@Override
	public Float getStatistic(String stat, String metric,  Optional<LocalDateTime> fromDateTime, Optional<LocalDateTime> toDateTime) {
		String sql = "";
		sql += "SELECT CAST ("+stat+"(value) AS DOUBLE PRECISION) as result ";
		sql += "FROM measurement_detail md, measurement m "; 
		sql += "WHERE md.id_measurement = m.id_measurement ";
		if (fromDateTime.isPresent() && toDateTime.isPresent()) {
			String from = MyLocalDateTimeConverter.converToString(fromDateTime.get());
			String to = MyLocalDateTimeConverter.converToString(toDateTime.get());
			sql += "AND m.timestamp BETWEEN '"+from+"' AND '"+to+"' ";			
		} 
		else if (fromDateTime.isPresent()) {
			sql += "AND m.timestamp >= '"+MyLocalDateTimeConverter.converToString(fromDateTime.get())+"' "; 
		}
		else if (toDateTime.isPresent()) {
			sql += "AND m.timestamp < '"+MyLocalDateTimeConverter.converToString(toDateTime.get())+" "; 
		}
		sql += "AND md.metric = '"+metric+"'";
		System.out.println(sql);
		List<Double> result = em.createNativeQuery(sql).getResultList();
        return result.size()>0?result.get(0).floatValue():null;
	}

}
