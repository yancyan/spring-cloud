package com.star.demo01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhuYX
 * @date 2021/07/29
 */
@ToString @Getter @Setter
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "config.demo01", ignoreInvalidFields = true)
public class Demo01Props implements InitializingBean {

    private String name;
    private Integer age;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("========= >>> load config is {}", this);
    }
}
