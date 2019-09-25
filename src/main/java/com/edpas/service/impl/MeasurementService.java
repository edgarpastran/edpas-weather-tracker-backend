package com.edpas.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edpas.dto.Statistic;
import com.edpas.model.Measurement;
import com.edpas.repository.IMeasurementRepository;
import com.edpas.repository.IStatisticRepository;
import com.edpas.service.IMeasurementService;
import com.edpas.util.MyLocalDateTimeConverter;

@Service
public class MeasurementService implements IMeasurementService {

	@Autowired
	private IMeasurementRepository repository;
	
	@Autowired
	private IStatisticRepository otherRepository;
	
	@Override
	public Measurement insert(Measurement t) {
		t.getMeasurementDetails().forEach(detail -> {
			detail.setMeasurement(t);
		});
		return this.repository.save(t);
	}

	@Override
	public Measurement update(Measurement t) {
		return this.repository.save(t);
	}

	@Override
	public void delete(Integer id) {
		this.repository.delete(id);
	}

	@Override
	public Measurement getOne(Integer id) {
		return this.repository.findOne(id);
	}

	@Override
	public List<Measurement> getAll() {
		return this.repository.findAll();
	}

	@Override
	public Page<Measurement> listPageable(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	@Override
	public Measurement getByTimeStamp(String timestamp) {
		LocalDateTime dateTime = MyLocalDateTimeConverter.converToLocalDateTime(timestamp);
		return this.repository.findOneByTimestamp(dateTime);
	}
	
}
