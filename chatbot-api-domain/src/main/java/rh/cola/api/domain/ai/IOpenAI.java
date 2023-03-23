package rh.cola.api.domain.ai;

import java.io.IOException;

/**
 * @Name: IOpenAI
 * @Author: Cola
 * @Time: 2023/3/23 1:23
 * @Description: IOpenAI
 */
public interface IOpenAI {

    /**
     * 通过 GPT 进行回答问题
     */
    String doChatGPT(String openAiKey, String question) throws IOException;

}
