package rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConn();
        String input = new Scanner(System.in).next();

        Channel channel = connection.createChannel();

        // 参数 1：交换机
        // 参数 2：路由名字，暂时用不到
        channel.basicPublish(RabbitmqConstant.EXCHANGE_WEATHER, "", null, input.getBytes());

        channel.close();
        connection.close();

    }
}
