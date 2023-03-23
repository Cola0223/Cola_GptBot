package rh.cola.api.application.job;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import rh.cola.api.domain.ai.IOpenAI;
import rh.cola.api.domain.zsxq.IZsxqApi;
import rh.cola.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import rh.cola.api.domain.zsxq.model.vo.Topics;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @Name: ChatbotTask
 * @Author: Cola
 * @Time: 2023/3/23 1:29
 * @Description: ChatbotTask 任务检索类
 */
@Slf4j
public class ChatbotTask implements Runnable {

    private final String groupName;
    private final String groupId;
    private final String cookie;
    private final String openAiKey;
    private final boolean silenced;

    private final IZsxqApi zsxqApi;
    private final IOpenAI openAIApi;

    public ChatbotTask(String groupName, String groupId, String cookie, String openAiKey, IZsxqApi zsxqApi, IOpenAI openAIApi, boolean silenced) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.openAiKey = openAiKey;
        this.zsxqApi = zsxqApi;
        this.openAIApi = openAIApi;
        this.silenced = silenced;
    }

    @Override
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                log.info("{} 随机打烊中", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                log.info("{} 22:00 -- 7:00 AI 不工作", groupName);
                return;
            }

            /*
             * 通过 zsxqApi 中的 queryUnAnsweredQuestionsTopicId() 方法检索待回答问题
             * */
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            log.info("{} 检索结果：{}", groupName, JSON.toJSONString(unAnsweredQuestionsAggregates));
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                log.info("{} 本次检索未查询到待会答问题", groupName);
                return;
            }

            /*
             * 通过 openAIApi 中的 doChatGPT() 获取问题的答案
             * */
            Topics topic = topics.get(topics.size() - 1);
            String answer = openAIApi.doChatGPT(openAiKey, topic.getQuestion().getText().trim());

            /*
             * 通过 zsxqApi 中的 answer() 方法回答问题
             * */
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, silenced);
            log.info("{} 编号：{} 问题：{} 回答：{} 状态：{}", groupName, topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (Exception e) {
            log.error("{} 自动回答问题异常", groupName, e);
        }
    }

}

