package rh.cola.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Name: SpringTest
 * @Author: Cola
 * @Time: 2023/3/23 13:54
 * @Description: SpringTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SpringTest {

    @Value("${chatbot-api.group01.cookie}")
    private String COOKIE;
    @Value("${chatbot-api.group01.openAiKey}")
    private String OPEN_API_KEY;
    @Value("${Cola.PROXY_HOST}")
    private String PROXY_HOST;
    @Value("${Cola.PROXY_PORT}")
    private Integer PROXY_PORT;

    @Test
    public void testValue() {
        log.info("COOKIE: {}", COOKIE);
        log.info("OPEN_API_KEY: {}", OPEN_API_KEY);
        log.info("PROXY_HOST: {}", PROXY_HOST);
        log.info("PROXY_PORT: {}", PROXY_PORT);
    }
}
