package rh.cola.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Name: ApiTest
 * @Author: Cola
 * @Time: 2023/3/22 22:38
 * @Description: ApiTest
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ApiTest {

    @Value("${chatbot-api.group01.cookie}")
    private String COOKIE;
    @Value("${chatbot-api.group01.openAiKey}")
    private String OPEN_API_KEY;
    @Value("${Cola.PROXY_HOST}")
    private String PROXY_HOST;
    @Value("${Cola.PROXY_PORT}")
    private Integer PROXY_PORT;


    /**
     * 检索待回答问题
     *
     * @throws IOException IOException
     */
    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get =
                new HttpGet("https://api.zsxq.com/v2/groups/88885828885512/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie", COOKIE);
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            log.info("result = {}", result);
        } else {
            log.error("code:{}", response.getStatusLine().getStatusCode());
        }

    }

    /**
     * 回答问题
     *
     * @throws IOException IOException
     */
    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post =
                new HttpPost("https://api.zsxq.com/v2/topics/88885828885512/answer");
        post.addHeader("cookie", COOKIE);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"ZsxqApiTest \\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            log.info("result = {}", result);
        } else {
            log.error("code:{}", response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 使用 GPT 进行查找问题答案
     *
     * @throws IOException IOException
     */
    @Test
    public void answerByOpenAi() throws IOException {
        /*创建代理对象*/
        HttpHost proxyHost = new HttpHost(PROXY_HOST, PROXY_PORT);
        /*创建客户端*/
        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxyHost).build();
        /*创建 POST 请求*/
        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + OPEN_API_KEY);

        String question = "say Hello";
        
        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + question + "\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        /*发起请求*/
        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            log.info("result = {}", result);
        } else {
            log.error("code:{}", response.getStatusLine().getStatusCode());
        }
    }
}
