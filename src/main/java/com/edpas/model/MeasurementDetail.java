package com.edpas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MeasurementDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMeasurementDetail;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_measurement", nullable = false, foreignKey = @ForeignKey(name = "fk_measurement_detail_ref_measurement"))
	private Measurement measurement;
	
	@Size(min = 2, max = 50, message = "Metric must have between 2 and 50 characters")
	@Column(name = "metric", nullable = false, length = 50)
	private String metric;
	
	@Column(name = "value", nullable = false)
	private Float value;

	
	public Integer getIdMeasurementDetail() {
		return idMeasurementDetail;
	}

	public void setIdMeasurementDetail(Integer idMeasurementDetail) {
		this.idMeasurementDetail = idMeasurementDetail;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
		
}
