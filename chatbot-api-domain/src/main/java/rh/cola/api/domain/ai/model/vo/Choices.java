package rh.cola.api.domain.ai.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: Choices
 * @Author: Cola
 * @Time: 2023/3/23 1:24
 * @Description: Choices
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choices {
    /**
     * 文本
     */
    private String text;
    /**
     * index
     */
    private int index;
    /**
     * logprobs
     */
    private String logprobs;
    /**
     * finish_reason
     */
    private String finish_reason;

}
