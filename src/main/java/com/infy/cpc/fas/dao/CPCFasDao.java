package com.infy.cpc.fas.dao;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

	@SuppressWarnings("unchecked")
	public List<?> getStudentDataSet(String id) {
		
		List<StudentDetails> list = new ArrayList<>();
		list = em.createNamedQuery("GET_STUDENT_DETAILS_HQL_QUERY").setParameter("id", Integer.parseInt(id)).getResultList();
		ObjectMapper mapper = new ObjectMapper();
		int index =0;
		for (Object sd : list) {
			try {
				String jsonInString=mapper.writeValueAsString(list.get(index)).toString();
				Map<String, Object> map=mapper.readValue(jsonInString, Map.class);
				log.debug("iterated result :"+map.get("address"));
				index++;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		/* Without OBJECT through JPQL Working*/
		/*List<Object[]> list = new ArrayList<>();
		list = em.createNamedQuery("GET_STUDENT_DETAILS_NATIVE_QUERY").setParameter("id", Integer.parseInt(id)).getResultList();
		for (Object[] sd : list)
			log.debug("iterated result - name :"+sd[1]+" DOB :"+sd[2]+" address :"+sd[4]+" and id :"+sd[0]);*/
		
		
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