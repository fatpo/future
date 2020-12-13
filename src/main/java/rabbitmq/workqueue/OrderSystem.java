package rabbitmq.workqueue;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class OrderSystem {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConn();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitmqConstant.QUEUE_SMS, false, false, false, null);

        for (int i = 0; i < 100; i++) {
            Sms sms = new Sms("fatpo" + i, "1509999" + i, "车票预订");
            String json = new Gson().toJson(sms);
            channel.basicPublish("", RabbitmqConstant.QUEUE_SMS, null, json.getBytes());
        }

        System.out.println("发送成功！");
        channel.close();
        connection.close();
    }
}
