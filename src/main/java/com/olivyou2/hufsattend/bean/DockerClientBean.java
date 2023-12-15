package com.olivyou2.hufsattend.bean;

import com.olivyou2.hufsattend.lib.Docker.DockerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientBean {
    @Bean
    public DockerClient getDockerClient(){
        return new DockerClient("http://hattend.suplitter.com:2375");
    }
}
