package com.edpas.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
public class Measurement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMeasurement;
	
	@JsonSerialize(using = ToStringSerializer.class)
	@Column(name = "timestamp", nullable = false, unique=true)
	private LocalDateTime timestamp;
	
	@OneToMany(mappedBy = "measurement", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<MeasurementDetail> measurementDetails;

	
	public Integer getIdMeasurement() {
		return idMeasurement;
	}

	public void setIdMeasurement(Integer idMeasurement) {
		this.idMeasurement = idMeasurement;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<MeasurementDetail> getMeasurementDetails() {
		return measurementDetails;
	}

	public void setMeasurementDetails(List<MeasurementDetail> measurementDetails) {
		this.measurementDetails = measurementDetails;
	}
		
}
