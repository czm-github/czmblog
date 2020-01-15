package zm.blog.community.czmblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("zm.blog.community.czmblog.mapper")
@SpringBootApplication
public class CzmblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(CzmblogApplication.class, args);
    }

}
