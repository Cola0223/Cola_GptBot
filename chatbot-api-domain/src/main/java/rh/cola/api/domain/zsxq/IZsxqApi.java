package rh.cola.api.domain.zsxq;

import rh.cola.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @Name: IZsxqApi
 * @Author: Cola
 * @Time: 2023/3/23 1:19
 * @Description: IZsxqApi
 */
public interface IZsxqApi {
    /**
     * 检索待回答问题
     */
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    /**
     * 回答问题
     */
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
