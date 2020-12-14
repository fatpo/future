package rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/resources/spring-rabbitmq-producer.xml")
public class SpringProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void testHelloWorld() {
        // 第一个参数是队列名
        rabbitTemplate.convertAndSend("spring_queue", "hello world spring....");
    }


    /**
     * 发送fanout消息
     */
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "spring fanout....");
    }

    /**
     * 发送routing消息
     */
    @Test
    public void testRouting() {
        rabbitTemplate.convertAndSend("spring_direct_exchange", "info", "spring routing....");
    }

    /**
     * 发送topic消息
     */
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "fatpo.hahaha", "spring topic....");
    }
}