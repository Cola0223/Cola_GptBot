package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: UserSpecific
 * @Author: Cola
 * @Time: 2023/3/23 1:17
 * @Description: UserSpecific
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSpecific {
    /**
     * liked
     */
    private boolean liked;
    /**
     * subscribed
     */
    private boolean subscribed;

}
