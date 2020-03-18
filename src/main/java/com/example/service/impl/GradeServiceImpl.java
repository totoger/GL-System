package com.example.service.impl;


import com.example.dao.GradeMapper;
import com.example.pojo.Grade;
import com.example.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeMapper gradeMapper;
	
	@Override
	public int add(Grade grade) {
		// TODO Auto-generated method stub
		return gradeMapper.add(grade);
	}

	@Override
	public int edit(Grade grade) {
		// TODO Auto-generated method stub
		return gradeMapper.edit(grade);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return gradeMapper.delete(ids);
	}

	@Override
	public List<Grade> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return gradeMapper.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return gradeMapper.getTotal(queryMap);
	}

	@Override
	public List<Grade> findAll() {
		// TODO Auto-generated method stub
		return gradeMapper.findAll();
	}

}
