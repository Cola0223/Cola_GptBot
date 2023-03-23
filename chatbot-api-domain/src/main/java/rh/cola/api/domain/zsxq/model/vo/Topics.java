package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: Topics
 * @Author: Cola
 * @Time: 2023/3/23 1:16
 * @Description: Topics
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topics {
    /**
     * topic_id
     */
    private String topic_id;
    /**
     * group
     */
    private Group group;
    /**
     * type
     */
    private String type;
    /**
     * question
     */
    private Question question;
    /**
     * answered
     */
    private boolean answered;
    /**
     * likes_count
     */
    private int likes_count;
    /**
     * rewards_count
     */
    private int rewards_count;
    /**
     * comments_count
     */
    private int comments_count;
    /**
     * reading_count
     */
    private int reading_count;
    /**
     * readers_count
     */
    private int readers_count;
    /**
     * digested
     */
    private boolean digested;
    /**
     * sticky
     */
    private boolean sticky;
    /**
     * create_time
     */
    private String create_time;
    /**
     * user_specific
     */
    private UserSpecific user_specific;

}
