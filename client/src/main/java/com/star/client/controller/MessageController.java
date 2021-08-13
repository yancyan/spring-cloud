package com.star.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 *
 生产者发出后保证到达了MQ。
 MQ收到消息保证分发到了消息对应的Exchange。
 Exchange分发消息入队之后保证消息的持久性。
 消费者收到消息之后保证消息的正确消费。

1. 生产者发送消息到MQ失败
 为了解决这个问题，RabbitMQ引入了事务机制和发送方确认机制（publisher confirm），由于事务机制过于耗费性能所以一般不用，这里我着重讲述发送方确认机制。
 Tip：消息确认失败不只有消息没发过去会触发，消息发过去但是找不到对应的Exchange，也会触发。

2. MQ接收失败或者路由失败

 生产者的发送消息处理好了之后，我们就可以来看看MQ端的处理，MQ可能出现两个问题
 - 消息找不到对应的Exchange。
 - 找到了Exchange但是找不到对应的Queue。
 这两种情况都可以用RabbitMQ提供的mandatory参数来解决，它会设置消息投递失败的策略，有两种策略：自动删除或返回到客户端。
设置为返回到客户端(true是返回客户端，false是自动删除)。


 3. 消息入队之后MQ宕机
 到这一步基本都是一些很小概率的问题了，比如MQ突然宕机了或者被关闭了，这种问题就必须要对消息做持久化，以便MQ重新启动之后消息还能重新恢复过来。
 消息的持久化要做，但是不能只做消息的持久化，还要做队列的持久化和Exchange的持久化。

 创建Exchange和队列时只要设置好持久化，发送的消息默认就是持久化消息。

 设置持久化时一定要将Exchange和队列都设置上持久化：
 单单只设置Exchange持久化，重启之后队列会丢失。单单只设置队列的持久化，重启之后Exchange会消失，既而消息也丢失，所以如果不两个一块设置持久化将毫无意义。
 Tip： 这些都是MQ宕机引起的问题，如果出现服务器宕机或者磁盘损坏则上面的手段统统无效，必须引入镜像队列，做异地多活来抵御这种不可抗因素。

4. 消费者无法正常消费
 最后一步会出问题的地方就在消费者端了，不过这个解决问题的方法我们之前的文章已经说过了，就是消费者的消息确认。

 */
@Slf4j
@RestController
public class MessageController {


    @Autowired
    RabbitTemplate rabbitTemplate;


    @PostMapping("/message-1")
    public void publisherConfirm() {

            User user = new User();
            user.setName("message-name");

            log.info("Message content : " + user);

            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("queue-name-1",user,correlationData);
            log.info("消息发送完毕。");

            //Tip：消息确认失败不只有消息没发过去会触发，消息发过去但是找不到对应的Exchange，也会触发。
            rabbitTemplate.setConfirmCallback((correlationData1, ack, cause) -> {
                log.info("CorrelationData content : " + correlationData1);
                log.info("Ack status : " + ack);
                log.info("Cause content : " + cause);
                if(ack){
                    log.info("消息成功发送，订单入库，更改订单状态");
                }else{
                    log.info("消息发送失败："+ correlationData1 +", 出现异常："+cause);
                }
            });

            log.info("消息发送完毕, 请求返回，，，");
    }

    @PostMapping("/message-2")
    public void sendAndReturn() {
        User user = new User();
        user.setName("sendAndReturn");
        log.info("Message content : " + user);
        rabbitTemplate.setReturnsCallback(message -> {
            log.info("被退回的消息为：{}", message);
        });

        rabbitTemplate.convertAndSend("fail",user);
        log.info("消息发送完毕。");
    }

}
