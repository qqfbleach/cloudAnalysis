package com.qqfall.cloud.apiKey.dao.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qqfall.cloud.apiKey.dao.entity.ApiKeyEntity;

public interface ApiKeyDao extends MongoRepository<ApiKeyEntity, Long>{

}
