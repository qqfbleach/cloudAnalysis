package com.qqfall.cloud.mytest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, Long>, UserDaoCustom {

}
