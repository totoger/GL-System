package com.totoger.glsystem.dao;

import com.totoger.glsystem.pojo.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface TeacherMapper {
	public Teacher findByUserName(String username);
	public int add(Teacher teacher);
	public int edit(Teacher teacher);
	public int delete(String ids);
	public List<Teacher> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
