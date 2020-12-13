package rabbitmq.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 设置主机信息
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123qwe");
        connectionFactory.setVirtualHost("demo");

        // 获取 Tcp 长连接
        Connection connection = connectionFactory.newConnection();

        // 创建通信 channel，相当于 tcp 中的虚拟连接
        Channel channel = connection.createChannel();

        // 第一个参数：队列名字
        // 第二个参数：是否自动 ACK, 推荐是false，自己签收确认消息
        // 第三个参数：消费者处理的callback
        channel.queueDeclare("helloworld", false, false, false, null);

        channel.basicConsume("helloworld", false, new Receiver(channel));

        System.out.println("消费者收到...消息：");
    }
}

class Receiver extends DefaultConsumer {
    private final Channel channel;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public Receiver(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body);
        System.out.println("消费者受到消息如下：" + message);

        System.out.println("消息的 tagId：" + envelope.getDeliveryTag());

        // false 只签收当前消息，true 表示签收之前消费者所有未签收的消息
        channel.basicAck(envelope.getDeliveryTag(), false);

    }
}
