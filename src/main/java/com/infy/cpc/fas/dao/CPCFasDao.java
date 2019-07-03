package com.infy.cpc.fas.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.cpc.fas.entity.Address;
import com.infy.cpc.fas.entity.User;
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

	public ResponseStatus updateUser(FASRequestBody req) {
		ResponseStatus response = new ResponseStatus();
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
}