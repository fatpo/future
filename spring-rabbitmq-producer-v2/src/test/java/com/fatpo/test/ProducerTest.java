package com.fatpo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testConfirm() {
        // 定义回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm 方法被执行了....");
                if (ack) {
                    System.out.println("到达exchange 接收消息成功！");
                } else {
                    System.out.println("没有到达exchange 接收消息失败了...");

                }
            }
        });

        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "你好啊");

        // 静止 5 秒，等callback
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testReturnWithWrongRoutingKey() {
        // 定义回调
        // 这里必须设置强制性
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("收到回退的消息啦！！！" + new String(message.getBody()));
                System.out.println("回退码：" + replyCode);
                System.out.println("回退文本：" + replyText);
                System.out.println("交换机：" + exchange);
                System.out.println("路由：" + routingKey);
            }
        });

        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm_error", "你好啊");
        System.out.println("生产者发送了消息...");
        System.out.println("理论上来说会收到回退码！");

        // 静止 5 秒，等callback
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQos() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "你好啊" + i);
            System.out.println("发送消息..." + i);
        }
    }
}
