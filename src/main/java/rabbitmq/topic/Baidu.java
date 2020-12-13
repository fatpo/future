package rabbitmq.topic;

import com.rabbitmq.client.*;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Baidu {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取 tcp长连接
        Connection connection = RabbitMqUtil.getConn();
        // 获取虚拟连接
        final Channel channel = connection.createChannel();

        // 声明队列信息
        channel.queueDeclare(RabbitmqConstant.QUEUE_BAIDU, false, false, false, null);

        // 队列绑定交换机
        // 参数 1：队列名， 参数 2：交换机名， 参数 3：路由 key，只有在 xxx 模式
        channel.queueBind(RabbitmqConstant.QUEUE_BAIDU, RabbitmqConstant.EXCHANGE_TOPIC_WEATHER, "china.hunan.#");

        channel.basicQos(1);
        channel.basicConsume(RabbitmqConstant.QUEUE_BAIDU, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("百度天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
