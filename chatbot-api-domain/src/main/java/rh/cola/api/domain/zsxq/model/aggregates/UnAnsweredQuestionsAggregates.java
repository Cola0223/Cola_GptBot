package rh.cola.api.domain.zsxq.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rh.cola.api.domain.zsxq.model.res.RespData;

/**
 * @Name: UnAnsweredQuestionsAggregates
 * @Author: Cola
 * @Time: 2023/3/23 1:15
 * @Description: UnAnsweredQuestionsAggregates
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnAnsweredQuestionsAggregates {
    /**
     * succeeded
     */
    private boolean succeeded;
    /**
     * resp_data;
     */
    private RespData resp_data;

}
