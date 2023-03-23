package rh.cola.api.domain.ai.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rh.cola.api.domain.ai.model.vo.Choices;

import java.util.List;

/**
 * @Name: AIAnswer
 * @Author: Cola
 * @Time: 2023/3/23 1:24
 * @Description: AIAnswer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIAnswer {

    /**
     * id
     */
    private String id;
    /**
     * object
     */
    private String object;
    /**
     * 发起者
     */
    private int created;
    /**
     * 使用的 GPT 模型
     */
    private String model;
    /**
     * choices
     */
    private List<Choices> choices;

}
