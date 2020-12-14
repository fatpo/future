package rabbitmq.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class FanoutListener1 implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("FanoutListener1 收到消息啦~~~" + message.toString());
    }
}
