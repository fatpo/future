package com.fatpo.listener;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class AckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("######################");

        long messageId = message.getMessageProperties().getDeliveryTag();
        System.out.println("AckListener 监听到消息：" + new String(message.getBody()));
        System.out.println("AckListener 监听到消息ID：" + messageId);
        try {
            System.out.println("AckListener 正在处理业务...");

            // 人为制造 exception
            // int r = 5 / 0;
            Thread.sleep(3000);

            channel.basicAck(messageId, false);
        } catch (Exception e) {
            System.out.println(e.toString());
            // 第三个参数：true 则重新丢到队列中，等于不签收！queue不为空，会重新触发 listener，基本上等于死循环
            channel.basicNack(messageId, false, true);
        }
    }
}
