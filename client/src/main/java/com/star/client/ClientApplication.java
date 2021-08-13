package com.star.client;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhuYX
 * @date 2021/07/30
 */
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class)
                .run(args);
    }

    @Bean
    public DirectExchange directExchange() {
        // 三个构造参数：name durable autoDelete
        return new DirectExchange("directExchange", false, false);
    }

    @Bean
    public Queue erduo() {
        // 其三个参数：durable exclusive autoDelete
        // 一般只设置一下持久化即可
        return new Queue("erduo",true);
    }

}
