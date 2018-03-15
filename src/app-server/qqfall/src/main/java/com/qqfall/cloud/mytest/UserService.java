package com.qqfall.cloud.mytest;

import java.util.List;

import com.qqfall.cloud.mytest.dao.User;

public interface UserService {
	public List<User> getAll2();
	
	public User add(UserDto userDto);
}
