import com.fatpo.ProducerApplication;
import com.fatpo.rabbitmq.config.RabbitMqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProducerApplication.class)
@RunWith(SpringRunner.class)
public class ProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHelloWorld() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, "boot.fatpo", "hello fatop!");
    }

}
