package pers.lyks.example;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring-boot web application main method.
 *
 * @author lawyerance
 * @version 1.0 2018-08-03
 */
@SpringBootApplication(exclude = {
})
public class ServletApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServletApplication.class);
        application.run(args);
    }
}
