package com.star.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhuYX
 * @date 2021/08/02
 */
@Slf4j
@Getter @Setter @ToString
@Configuration
@ConfigurationProperties(prefix = "sta.common")
public class CommonProps implements InitializingBean {


    private String messageType;

    @Override
    public void afterPropertiesSet() {
        log.info("load CommonProps is {}", this);
    }
}
