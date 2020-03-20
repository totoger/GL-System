package com.totoger.glsystem.service.impl;


import com.totoger.glsystem.dao.ClazzMapper;
import com.totoger.glsystem.pojo.Clazz;
import com.totoger.glsystem.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzMapper clazzMapper;
	
	@Override
	public int add(Clazz clazz) {
		// TODO Auto-generated method stub
		return clazzMapper.add(clazz);
	}

	@Override
	public int edit(Clazz clazz) {
		// TODO Auto-generated method stub
		return clazzMapper.edit(clazz);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return clazzMapper.delete(ids);
	}

	@Override
	public List<Clazz> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return clazzMapper.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return clazzMapper.getTotal(queryMap);
	}

	@Override
	public List<Clazz> findAll() {
		// TODO Auto-generated method stub
		return clazzMapper.findAll();
	}

}
