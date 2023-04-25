package stu.cmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import stu.cmq.utils.SpringContextHolder;

/**
 * @author kamifeng
 * @date 15:24
 */

@SpringBootApplication()
@MapperScan("stu.cmq.mapper")
public class StudyElfApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyElfApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
