package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: OwnerDetail
 * @Author: Cola
 * @Time: 2023/3/23 1:18
 * @Description: OwnerDetail
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDetail {
    /**
     * questions_count
     */
    private int questions_count;
    /**
     * join_time
     */
    private String join_time;


}
