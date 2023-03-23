package rh.cola.api.domain.ai.service;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rh.cola.api.domain.ai.IOpenAI;
import rh.cola.api.domain.ai.model.aggregates.AIAnswer;
import rh.cola.api.domain.ai.model.vo.Choices;

import java.io.IOException;
import java.util.List;

/**
 * @Name: OpenAI
 * @Author: Cola
 * @Time: 2023/3/23 1:25
 * @Description: OpenAI
 */
@Slf4j
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenAIApi implements IOpenAI {

    @Value("${Cola.PROXY_HOST}")
    private String PROXY_HOST;
    @Value("${Cola.PROXY_PORT}")
    private Integer PROXY_PORT;

    /**
     * 通过 GPT 进行回答问题
     *
     * @param openAiKey openAiKey
     * @param question  question
     * @return String
     * @throws IOException IOException
     */
    @Override
    public String doChatGPT(String openAiKey, String question) throws IOException {

        /*创建代理对象*/
        HttpHost proxyHost = new HttpHost(PROXY_HOST, PROXY_PORT);
        /*创建客户端*/
        CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(proxyHost).build();
        /*创建 POST 请求*/
        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + openAiKey);

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + question + "\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        /*发起请求*/
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            AIAnswer aiAnswer = JSON.parseObject(jsonStr, AIAnswer.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = aiAnswer.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getText());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("AI 回答问题失败 ErrCodeIs " + response.getStatusLine().getStatusCode());
        }
    }
}
