package rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.utils.RabbitMqUtil;
import rabbitmq.utils.RabbitmqConstant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMqUtil.getConn();

        Channel channel = connection.createChannel();


        Map<String, String> data = new HashMap<>();
        data.put("china.hebei.shijiazhuagn.20201210", "中国河北石家庄20201210天气不错！");
        data.put("china.hunan.chagnsha.20201210", "中国湖南长沙20201210天气不错！");
        data.put("china.hunan.zhuzhou.20201211", "中国湖南株洲20201211天气不错！");


        for (Map.Entry<String, String> entry : data.entrySet()) {
            // 参数 1：交换机
            // 参数 2：路由名字
            channel.basicPublish(RabbitmqConstant.EXCHANGE_TOPIC_WEATHER, entry.getKey(), null, entry.getValue().getBytes());
        }

        channel.close();
        connection.close();

    }
}
