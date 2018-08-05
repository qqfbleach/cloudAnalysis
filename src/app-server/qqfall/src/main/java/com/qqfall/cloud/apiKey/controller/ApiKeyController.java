package com.qqfall.cloud.apiKey.controller;

import com.qqfall.cloud.apiKey.dao.api.ApiKeyDao;
import com.qqfall.cloud.apiKey.dao.entity.ApiKeyEntity;
import com.qqfall.cloud.core.common.DataResponse;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cloud/v1/apikey")
public class ApiKeyController {

  public static Logger log = LoggerFactory.getLogger(ApiKeyController.class);

  @Autowired
  private ApiKeyDao apiKeyDao;

  @RequestMapping(method = RequestMethod.GET)
  public DataResponse getAll() {
    log.info("[apiKeyController]begin to get all");
    List<ApiKeyEntity> apiKeyDtoList = new ArrayList<>();
    apiKeyDtoList=apiKeyDao.findAll();
    DataResponse dataResponse = new DataResponse();
    dataResponse.setData(apiKeyDtoList);
    return dataResponse;
  }

  @RequestMapping(method = RequestMethod.POST)
  public DataResponse saveApiKey(@RequestBody ApiKeyEntity apiKeyEntity) {
    apiKeyDao.save(apiKeyEntity);
    DataResponse dataResponse = new DataResponse();
    dataResponse.setData(apiKeyEntity);
    return dataResponse;
  }

}
