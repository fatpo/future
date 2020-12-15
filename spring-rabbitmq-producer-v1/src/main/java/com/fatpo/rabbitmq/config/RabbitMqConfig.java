package com.fatpo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE_NAME = "boot_queue";

    // 1、声明交换机
    @Bean("bootExchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }

    // 2、声明队列
    @Bean("bootQueue")
    public Queue bootQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }


    // 3、声明 exchange 和 queue 的绑定
    @Bean
    public Binding bindingQueueAndExchange(@Qualifier("bootQueue") Queue queue,
                                           @Qualifier("bootExchange") Exchange exchange) {
        // 不带参数
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }
}
