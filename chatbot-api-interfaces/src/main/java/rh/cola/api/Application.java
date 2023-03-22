package rh.cola.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Name: Application
 * @Author: Cola
 * @Time: 2023/3/22 22:34
 * @Description: Application
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Cola_GptBot 项目启动成功");
    }
}
