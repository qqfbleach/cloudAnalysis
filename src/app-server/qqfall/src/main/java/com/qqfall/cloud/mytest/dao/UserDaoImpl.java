package com.qqfall.cloud.mytest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class UserDaoImpl implements UserDaoCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public User getAll() {
		return mongoTemplate.findAll(User.class).get(0);
	}

	@Override
	public User add(User user) {
		mongoTemplate.insert(user);
		return user;
	}

}
