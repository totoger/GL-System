package com.example.dao;


import com.example.pojo.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClazzMapper {
	public int add(Clazz clazz);
	public int edit(Clazz clazz);
	public int delete(String ids);
	public List<Clazz> findList(Map<String, Object> queryMap);
	public List<Clazz> findAll();
	public int getTotal(Map<String, Object> queryMap);
}
