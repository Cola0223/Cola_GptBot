package rh.cola.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.stereotype.Service;
import rh.cola.api.domain.zsxq.IZsxqApi;
import rh.cola.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import rh.cola.api.domain.zsxq.model.req.AnswerReq;
import rh.cola.api.domain.zsxq.model.req.ReqData;
import rh.cola.api.domain.zsxq.model.res.AnswerRes;

import java.io.IOException;

/**
 * @Name: ZsxqApi
 * @Author: Cola
 * @Time: 2023/3/23 1:20
 * @Description: ZsxqApi
 */
@Slf4j
@Service
public class ZsxqApi implements IZsxqApi {
    /**
     * 检索待回答问题
     *
     * @param groupId groupId
     * @param cookie  cookie
     * @return UnAnsweredQuestionsAggregates
     * @throws IOException IOException
     */
    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"
                + groupId + "/topics?scope=unanswered_questions&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            log.info("拉取提问数据：groupId：{} jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("查询待回答问题失败： ErrCodeIs = " + response.getStatusLine().getStatusCode());
        }
    }

    /**
     * 回答问题
     *
     * @param groupId  groupId
     * @param cookie   cookie
     * @param topicId  topicId
     * @param text     text
     * @param silenced silenced
     * @return boolean
     * @throws IOException IOException
     */
    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        post.addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");

        /*构建回答模板*/
        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.toJSONString(answerReq);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            log.info("回答问题结果：groupId：{} topicId：{} jsonStr：{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("回答问题失败 ErrCodeIs = " + response.getStatusLine().getStatusCode());
        }
    }
}
