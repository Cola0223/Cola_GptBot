package rh.cola.api.domain.zsxq.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rh.cola.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @Name: RespData
 * @Author: Cola
 * @Time: 2023/3/23 1:16
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespData {
    /**
     * topics
     */
    private List<Topics> topics;

}
