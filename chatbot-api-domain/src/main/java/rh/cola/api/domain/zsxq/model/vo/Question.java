package rh.cola.api.domain.zsxq.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: Question
 * @Author: Cola
 * @Time: 2023/3/23 1:17
 * @Description: Question
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    /**
     * owner
     */
    private Owner owner;
    /**
     * questionee
     */
    private Questionee questionee;
    /**
     * text
     */
    private String text;
    /**
     * expired
     */
    private boolean expired;
    /**
     * anonymous
     */
    private boolean anonymous;
    /**
     * owner_detail
     */
    private OwnerDetail owner_detail;
    /**
     * owner_location
     */
    private String owner_location;

}
