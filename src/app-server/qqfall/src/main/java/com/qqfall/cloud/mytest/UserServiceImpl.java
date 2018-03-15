package com.qqfall.cloud.mytest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqfall.cloud.mytest.dao.User;
import com.qqfall.cloud.mytest.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public List<User> getAll2() {
		// User user = new User("qqf");
		User user = userDao.getAll();
		List<User> users = new ArrayList<>();
		users.add(user);

		return users;
	}

	@Override
	public User add(UserDto userDto) {
		User user = new User(userDto.getName());
		return userDao.insert(user);
	}

}
