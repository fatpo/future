import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 初始化 ICO 容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-rabbitmq-consumer.xml");
    }
}

