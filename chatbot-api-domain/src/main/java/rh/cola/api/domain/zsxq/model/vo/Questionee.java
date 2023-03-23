package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: Questionee
 * @Author: Cola
 * @Time: 2023/3/23 1:17
 * @Description: Questionee
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Questionee {
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
     * description
     */
    private String description;
    /**
     * location
     */
    private String location;

}
