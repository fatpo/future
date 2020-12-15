package com.fatpo.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class QosListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("######################");

        long messageId = message.getMessageProperties().getDeliveryTag();
        System.out.println("QosListener 收到消息：" + new String(message.getBody()));
        System.out.println("QosListener 收到消息ID：" + messageId);
        System.out.println("QosListener 正在处理业务...");
        Thread.sleep(3000);
        System.out.println("处理完毕...");

        channel.basicAck(messageId, true);
    }
}


