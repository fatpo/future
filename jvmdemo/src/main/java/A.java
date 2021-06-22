import lombok.SneakyThrows;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.concurrent.ThreadPoolExecutor;

public class A {


    public static void main(String[] args) throws InterruptedException {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RankService.class);

        beanDefinitionBuilder.addPropertyValue("id", "1");
        beanDefinitionBuilder.addPropertyValue("name", "张三");
    }


}

