package com.fatpo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 白起老师
 */
@Component
public class RabbitMqListener {

    //定义方法进行信息的监听   RabbitListener中的参数用于表示监听的是哪一个队列
    @RabbitListener(queues = "boot_queue")
    public void ListenerQueue(Message message) {
        System.out.println("message:" + new String(message.getBody()));
    }
}

