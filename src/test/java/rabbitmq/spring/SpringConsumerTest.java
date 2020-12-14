package rabbitmq.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringConsumerTest {

    public static void main(String[] args) {
        //初始化IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/main/resources/spring-rabbitmq-consumer.xml");
        System.out.println("初始化 IOC 容器成功！" + ctx.getApplicationName());

    }
}

