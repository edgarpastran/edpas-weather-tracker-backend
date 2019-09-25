package com.edpas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edpas.exception.ModelNotFoundException;
import com.edpas.model.Measurement;
import com.edpas.service.IMeasurementService;
import com.edpas.util.MyLocalDateTimeConverter;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

	@Autowired
	private IMeasurementService measurementService;
	
	@GetMapping
	public ResponseEntity<List<Measurement>> list() {
		List<Measurement> list = this.measurementService.getAll();
		return new ResponseEntity<List<Measurement>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/{timestamp}")
	public ResponseEntity<Measurement> find(@PathVariable("timestamp") String timestamp) {
		Measurement object = this.measurementService.getByTimeStamp(timestamp);
		if (object == null) {
			throw new ModelNotFoundException("Measurement not found with TimeStamp:"+timestamp);
		}
		return new ResponseEntity<Measurement>(object, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> register(@Valid @RequestBody Measurement measurement) {
		Measurement saved = this.measurementService.insert(measurement);
		String timestamp = MyLocalDateTimeConverter.converToString(saved.getTimestamp());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{timestamp}").buildAndExpand(timestamp).toUri();
		return ResponseEntity.created(location).build();
	}
}
