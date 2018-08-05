package com.qqfall.cloud.apiKey.dao.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public enum HostType {

	linode,digitalocean,auto
}
