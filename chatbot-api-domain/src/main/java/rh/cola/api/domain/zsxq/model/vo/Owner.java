package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: Owner
 * @Author: Cola
 * @Time: 2023/3/23 1:18
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    /**
     * user_id
     */
    private String user_id;
    /**
     * name
     */
    private String name;
    /**
     * avatar_url
     */
    private String avatar_url;
    /**
     * location
     */
    private String location;


}
