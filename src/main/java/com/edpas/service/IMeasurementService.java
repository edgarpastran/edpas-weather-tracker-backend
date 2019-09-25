package com.edpas.service;

import java.util.List;
import java.util.Optional;

import com.edpas.dto.Statistic;
import com.edpas.model.Measurement;

public interface IMeasurementService extends ICRUDService<Measurement> {

	public Measurement getByTimeStamp(String timestamp);	
	
}
