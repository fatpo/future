package rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtil {
    public static Connection getConn() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 设置主机信息
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123qwe");
        connectionFactory.setVirtualHost("demo");

        // 获取 Tcp 长连接
        return connectionFactory.newConnection();
    }
}
