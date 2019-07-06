package com.infy.cpc.fas.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.infy.cpc.fas.dao.CPCFasDao;
import com.infy.cpc.fas.request.FASRequestBody;
import com.infy.cpc.fas.response.ResponseStatus;

@Service
public class CPCFasService<T> {
	private final static Logger LOG = Logger.getLogger(CPCFasService.class);
	@Autowired
	private CPCFasDao cpcfasdao;
	@Transactional( propagation = Propagation.REQUIRES_NEW )
	public ResponseStatus updateFASDataSet(FASRequestBody req) {
		return cpcfasdao.updateUser(req);
	}
	@Transactional( propagation = Propagation.REQUIRES_NEW,readOnly = true)
	public List<?> getFASDataSet(String id) {
		return cpcfasdao.getStudentDataSet(id);
	}
}
