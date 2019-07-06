package com.infy.cpc.fas.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.cpc.fas.entity.Address;
import com.infy.cpc.fas.entity.User;
import com.infy.cpc.fas.model.StudentDetails;
import com.infy.cpc.fas.repository.CRUDAddressRepository;
import com.infy.cpc.fas.repository.CRUDUserRepository;
import com.infy.cpc.fas.request.FASRequestAddress;
import com.infy.cpc.fas.request.FASRequestBody;
import com.infy.cpc.fas.response.ResponseStatus;

@Repository
public class CPCFasDao<T> {
	private final static Logger log = Logger.getLogger(CPCFasDao.class);
	@Autowired
	private CRUDUserRepository userRepository;
	@Autowired
	private CRUDAddressRepository addressRepository;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("db-queries");
	EntityManager em = emf.createEntityManager();
	ResponseStatus response = new ResponseStatus();
	public ResponseStatus updateUser(FASRequestBody req) {
		
		try {

			User user = new User();
			user.setName(req.getName());
			user.setSex(req.getSex());
			user.setDob(req.getDob());

			userRepository.saveAndFlush(user);

			for (FASRequestAddress userAddress : req.getFasAddress()) {
				Address address = new Address();
				address.setAddress(userAddress.getAddress());
				address.setLandmark(userAddress.getLandmark());
				address.setUserid(user.getId());
				addressRepository.saveAndFlush(address);
			}

			response.setStatus("success");
			response.setStatCode(200);
			response.setMessage("Data saved successfully");

		} catch (Exception ex) {
			response.setStatus("failed");
			response.setStatCode(417);
			response.setMessage(ex.getCause().toString());
			ex.printStackTrace();
		}
		return response;
	}

	public List<?> getStudentDataSet(String id) {
		List<StudentDetails> list = em.createNamedQuery("GET_STUDENT_DETAILS").setParameter("id", Integer.parseInt(id)).getResultList();
		if (!list.isEmpty())
			return list;
		else {
			List<ResponseStatus> lstEmpty = new ArrayList<>();
			response.setStatus("failed");
			response.setStatCode(204);
			response.setMessage("No records found.");
			lstEmpty.add(response);
			return lstEmpty;
		}
	}
}