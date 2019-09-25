package com.edpas.dto;

public class Statistic {

	private String metric;
	
	private String stat;
	
	private Float value;

	
	public Statistic(String metric, String stat, Float value) {
		super();
		this.metric = metric;
		this.stat = stat;
		this.value = value;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
		
}
