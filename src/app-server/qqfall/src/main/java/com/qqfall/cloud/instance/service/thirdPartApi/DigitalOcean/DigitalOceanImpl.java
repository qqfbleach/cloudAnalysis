package com.qqfall.cloud.instance.service.thirdPartApi.DigitalOcean;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DigitalOceanImpl implements DigitalOceanApi {

  public static Logger logger = LoggerFactory.getLogger(DigitalOceanImpl.class);

  @Override
  @Async
  public void createDroplet() {
    String authToken = "";
    DigitalOcean apiClient = new DigitalOceanClient(authToken);
    // Create a new droplet
    Droplet newDroplet = new Droplet();
    newDroplet.setName("api-client-test-host");
    newDroplet.setSize("512mb"); // setting size by slug value
    newDroplet.setRegion(
        new Region("sgp1")); // setting region by slug value; sgp1 => Singapore 1 Data center
    newDroplet.setImage(new Image("ubuntu-14-04-x64")); // setting by Image Id 1601 => centos-5-8-x64 also available in image slug value
    newDroplet.setEnableBackup(Boolean.FALSE);
    newDroplet.setEnableIpv6(Boolean.TRUE);
    newDroplet.setEnablePrivateNetworking(Boolean.TRUE);

    // Adding Metadata API - User Data
    newDroplet.setUserData(
        "#!/bin/bash  \n"
            + "sudo apt-get update \n"
            + "sudo apt install git -y\n"
            + "\n"
            + "#tool\n"
            + "sudo apt-get install libffi-dev -y\n"
            + "sudo apt-get install libssl-dev -y\n"
            + "sudo apt-get install python-dev -y\n"
            + "#pycurl\n"
            + "sudo apt-get install libcurl4-openssl-de -y\n"
            + "sudo apt-get install libssl-dev -y\n"
            + "\n"
            + "#\n"
            + "sudo apt-get install python-pip -y\n"
            + "sudo apt-get build-dep python-numpy -y\n"
            + "sudo apt-get build-dep python-scipy -y\n"
            + "sudo apt-get build-dep python-matplotlib -y\n"
            + "sudo apt-get install python-tk -y\n"
            + "\n"
            + "sudo pip install --upgrade pip\n"
            + "sudo pip install -U numpy\n"
            + "\n"
            + "sudo pip install -U scipy\n"
            + "sudo pip install -U matplotlib\n"
            + "sudo pip install -U scikit-learn\n"
            + "\n"
            + "#\n"
            + "cd /home\n"
            + "git clone https://github.com/qqfbleach/cloudAnalysis.git -b Develop\n"
            + "cd /home/cloudAnalysis/src/dataAnalysis/test\n"
            + "python run.py\n"
            + "echo \"Stack run sucess.\""); // Follow DigitalOcean documentation to prepare user_data value
    try {
      Droplet droplet = apiClient.createDroplet(newDroplet);
      logger.info("Create digitalocean droplet sucess.id:{},droplet:{}",droplet.getId(),droplet);
    } catch (DigitalOceanException e) {
      logger.error("fail to create digitalocean.", e);
      e.printStackTrace();
    } catch (RequestUnsuccessfulException e) {
      logger.error("fail to create digitalocean.", e);
      e.printStackTrace();
    }

    logger.info("createDroplet end");
  }

}
