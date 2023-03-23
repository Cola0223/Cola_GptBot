package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: Group
 * @Author: Cola
 * @Time: 2023/3/23 1:18
 * @Description: Group
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    /**
     * group_id
     */
    private String group_id;
    /**
     * name
     */
    private String name;
    /**
     * type
     */
    private String type;


}
