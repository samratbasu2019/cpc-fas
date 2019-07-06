package com.infy.cpc.fas.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.cpc.fas.request.FASRequestBody;
import com.infy.cpc.fas.response.ResponseStatus;
import com.infy.cpc.fas.service.CPCFasService;

@RestController
@RequestMapping(value = "/cpc")
public class CPCFASController {
	private final static Logger log = Logger.getLogger(CPCFASController.class);

	@Autowired
	private CPCFasService fasService;
	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/updateFas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateFAS(@RequestBody FASRequestBody requestBody) {
		log.debug("Inside updateFAS Controller");
		long starttime = System.currentTimeMillis();
		String jsonInString = null;
		Object result = null;

		try {
			result = fasService.updateFASDataSet(requestBody);
			jsonInString = mapper.writeValueAsString(result);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getCause().toString(), HttpStatus.EXPECTATION_FAILED);

		}

		long endtime = System.currentTimeMillis();
		log.debug("Total processing time " + (endtime - starttime) + " ms.");
		return result != null ? new ResponseEntity<>(jsonInString, HttpStatus.OK)
				: new ResponseEntity<>(jsonInString, HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(value = "/getFASDataSet/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFASData(@PathVariable String id) {
		List<?> result = null;
		String jsonInString = null;
		try {
			result = fasService.getFASDataSet(id);
			jsonInString = mapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(e.getCause().toString(), HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<>(jsonInString,HttpStatus.OK);
	}
}
