package rabbitmq.workqueue;

import com.rabbitmq.client.*;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SMSSender3 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConn();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(RabbitmqConstant.QUEUE_SMS, false, false, false, null);

        // 处理完一个取一个，如果不写，就是平均分配消息给所有的消费者，无视它的实际性能（处理能力）
        // maximum number of messages that the server will deliver, 0表示无限制
        //channel.basicQos(1);

        channel.basicConsume(RabbitmqConstant.QUEUE_SMS, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String jsonSMS = new String(body);
                System.out.println("消费者 3-短信发送成功：" + jsonSMS);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
