package rabbitmq.routing;

import com.rabbitmq.client.*;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sina {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取 tcp长连接
        Connection connection = RabbitMqUtil.getConn();
        // 获取虚拟连接
        final Channel channel = connection.createChannel();

        // 声明队列信息
        channel.queueDeclare(RabbitmqConstant.QUEUE_SINA, false, false, false, null);

        // 队列绑定交换机
        // 参数 1：队列名， 参数 2：交换机名， 参数 3：路由 key，只有在 direct，routing key 模式
        channel.queueBind(RabbitmqConstant.QUEUE_SINA, RabbitmqConstant.EXCHANGE_ROUTING_WEATHER, "china.hebei.shijiazhuagn.20201210");
        channel.queueBind(RabbitmqConstant.QUEUE_SINA, RabbitmqConstant.EXCHANGE_ROUTING_WEATHER, "china.hunan.chagnsha.20201210");

        channel.basicQos(1);
        channel.basicConsume(RabbitmqConstant.QUEUE_SINA, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("新浪天气收到气象信息：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }
}
