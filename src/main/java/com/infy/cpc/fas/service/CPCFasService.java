package com.infy.cpc.fas.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.cpc.fas.dao.CPCFasDao;
import com.infy.cpc.fas.request.FASRequestBody;
import com.infy.cpc.fas.response.ResponseStatus;

@Service
public class CPCFasService {
	private final static Logger LOG = Logger.getLogger(CPCFasService.class);
	
	
	/*
	 * EntityManagerFactory emf =
	 * Persistence.createEntityManagerFactory("db-queries");
	 */  
	/* EntityManager em=emf.createEntityManager(); */ 
	 
	
	@Autowired
	private CPCFasDao cpcfasdao;
	
	public ResponseStatus updateFASDataSet(FASRequestBody req) {
				 return cpcfasdao.updateUser(req);
	}

}
