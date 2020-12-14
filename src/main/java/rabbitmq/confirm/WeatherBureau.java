package rabbitmq.confirm;

import com.rabbitmq.client.*;
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
        data.put("us.hsd.ld.20201211", "美国天气1不错！");
        data.put("us.hsd.ld.20201212", "美国天气2不错！");
        data.put("us.hsd.ld.20201213", "美国天气3不错！");
        data.put("aaaus.hsd.ld.20201213", "美国天气34不错！");


        // 开启 confime 监听模式
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {

            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息 " + deliveryTag + " 被 broker 接收！！！");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("不开心，消息 " + deliveryTag + "被 broker 拒绝！！！");
            }
        });

        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return r) {
                System.out.println("================");
                System.out.println("return 编码" + r.getReplyCode() + "-Return 描述：" + r.getReplyText());
                System.out.println("交换机" + r.getExchange() + "- routing key：" + r.getRoutingKey());
                System.out.println("================");
            }
        });


        for (Map.Entry<String, String> entry : data.entrySet()) {
            // 参数 1：交换机
            // 参数 2：路由名字
            // 参数 3：mandatory，如果 true 就是无法正常投递就要 return 生产者，false 则无声的放弃消息
            channel.basicPublish(RabbitmqConstant.EXCHANGE_TOPIC_WEATHER, entry.getKey(), true, null, entry.getValue().getBytes());
        }

//        channel.close();
//        connection.close();

    }
}
