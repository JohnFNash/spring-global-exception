package com.johnfnash.study.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.johnfnash.study.dao.TestDao;
import com.johnfnash.study.exception.BusinessException;
import com.johnfnash.study.exception.ParameterException;
import com.johnfnash.study.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Resource
	private TestDao testDao;
	
	@Override
	public void exception(Integer id) throws Exception {
		switch (id) {
		case 1:
			throw new BusinessException("11", "service11");
		case 2:
			throw new BusinessException("21", "service21");
		case 3:
			throw new BusinessException("31", "service31");
		case 4:
			throw new BusinessException("41", "service41");
		case 5:
			throw new BusinessException("51", "service51");
		default:
			throw new ParameterException("Service Parameter Error");
		}
	}

	@Override
	public void dao(Integer id) throws Exception {
		testDao.exception(id);
	}

}
