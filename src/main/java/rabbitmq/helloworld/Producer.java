package rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取 Tcp 长连接
        Connection connection = RabbitMqUtil.getConn();

        // 创建通信 channel，相当于 tcp 中的虚拟连接
        Channel channel = connection.createChannel();

        // 第一个参数：队列名字
        // 第二个参数：是否持久化，MQ 停掉，数据就丢失
        // 第三个参数：是否队列私有化，false 所有的消费者都能访问，true 只有第一次拥有它的消费者才能一直用于
        // 第四个参数：是否自动删除，false 表示连接停掉后，不会自动删除这个队列
        channel.queueDeclare(RabbitmqConstant.QUEUE_HELLOWORLD, false, false, false, null);

        String message = "hello fatpo";
        // 第一个参数：交换机信息 ， exchange
        // 第二个参数：队列名
        // 第三个参数：额外属性
        // 第四个参数：message 的 byte 数组
        channel.basicPublish("", RabbitmqConstant.QUEUE_HELLOWORLD, null, message.getBytes());

        channel.close();
        connection.close();
        System.out.println("生产者发布成功...消息：" + message);
    }
}
