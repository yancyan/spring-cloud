package com.star;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhuYX
 * @date 2021/07/29
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.star")
public class Demo01Application {


}
