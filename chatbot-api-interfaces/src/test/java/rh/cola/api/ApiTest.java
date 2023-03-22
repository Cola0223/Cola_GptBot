package rh.cola.api;

import lombok.extern.slf4j.Slf4j;
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

import java.io.IOException;

/**
 * @Name: ApiTest
 * @Author: Cola
 * @Time: 2023/3/22 22:38
 * @Description: ApiTest
 */
@Slf4j
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get =
                new HttpGet("https://api.zsxq.com/v2/groups/88885828885512/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie",
                "zsxqsessionid=97c03173301c277ea1eac84c06b74c52; " +
                        "abtest_env=product; sajssdk_2015_cross_new_user=1; " +
                        "zsxq_access_token=CE710491-BD36-C8E6-5B0C-F3A4C539579A_A65ABE64B14CA91F; " +
                        "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218" +
                        "709cdfbdc4a-0f45a898e2875a-7a545470-921600-18709cdfbdd7c3" +
                        "%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22" +
                        "%24device_id%22%3A%2218709cdfbdc4a-0f45a898e2875a-7a545470-921600" +
                        "-18709cdfbdd7c3%22%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfYW5vbn" +
                        "ltb3VzX2lkIjoiMTg3MDljZGZiZGM0YS0wZjQ1YTg5OGUyODc1YS03YTU0NTQ3MC" +
                        "05MjE2MDAtMTg3MDljZGZiZGQ3YzMiLCIkaWRlbnRpdHlfY29va2llX2lkIjoiM" +
                        "Tg3MDlkMTcyNGYxZDEtMGY4YzMzZjhmYTA3Yjk4LTdhNTQ1NDcwLTkyMTYwMC0" +
                        "xODcwOWQxNzI1MDg0OSJ9%22%2C%22history_login_id" +
                        "%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%7D");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            log.info("result = {}", result);
        } else {
            log.error("code:{}", response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post =
                new HttpPost("https://api.zsxq.com/v2/topics/88885828885512/answer");
        post.addHeader("cookie",
                "zsxqsessionid=97c03173301c277ea1eac84c06b74c52; " +
                        "abtest_env=product; sajssdk_2015_cross_new_user=1; " +
                        "zsxq_access_token=CE710491-BD36-C8E6-5B0C-F3A4C539579A_A65ABE64B14CA91F; " +
                        "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2218" +
                        "709cdfbdc4a-0f45a898e2875a-7a545470-921600-18709cdfbdd7c3" +
                        "%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%7D%2C%22" +
                        "%24device_id%22%3A%2218709cdfbdc4a-0f45a898e2875a-7a545470-921600" +
                        "-18709cdfbdd7c3%22%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfYW5vbn" +
                        "ltb3VzX2lkIjoiMTg3MDljZGZiZGM0YS0wZjQ1YTg5OGUyODc1YS03YTU0NTQ3MC" +
                        "05MjE2MDAtMTg3MDljZGZiZGQ3YzMiLCIkaWRlbnRpdHlfY29va2llX2lkIjoiM" +
                        "Tg3MDlkMTcyNGYxZDEtMGY4YzMzZjhmYTA3Yjk4LTdhNTQ1NDcwLTkyMTYwMC0" +
                        "xODcwOWQxNzI1MDg0OSJ9%22%2C%22history_login_id" +
                        "%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
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
}
