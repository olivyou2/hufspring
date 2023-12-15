package com.olivyou2.hufsattend.bean;

import com.olivyou2.hufsattend.lib.Hufs.HufsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HufsClientBean {
    @Bean
    public HufsClient getHufsClient(){
        return new HufsClient();
    }
}
