package com.star.demo02;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhuYX
 * @date 2021/07/29
 */
@EnableDiscoveryClient(autoRegister = false)
@SpringBootApplication(scanBasePackages = "com.star")
@EnableFeignClients(basePackages = {
        "com.star.demo01.business.api"
})
public class Demo02Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Demo02Application.class)
                .applicationStartup(new BufferingApplicationStartup(2048))
                .logStartupInfo(true)
                .run(args);
    }

    @Bean
    public ResourceConfig jerseyConfig(ApplicationContext applicationContext) {
        var config = new ResourceConfig();
        config.registerFinder(new PackageNamesScanner(new String[]{
                "com.star.demo02.business.api"
        }, true));

        return config;
    }
}
