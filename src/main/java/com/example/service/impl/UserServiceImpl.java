package com.example.service.impl;


import com.example.dao.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByUserName(username);
	}
	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userMapper.add(user);
	}
	@Override
	public List<User> findList(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return userMapper.findList(queryMap);
	}
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return userMapper.getTotal(queryMap);
	}
	@Override
	public int edit(User user) {
		// TODO Auto-generated method stub
		return userMapper.edit(user);
	}
	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return userMapper.delete(ids);
	}

}
