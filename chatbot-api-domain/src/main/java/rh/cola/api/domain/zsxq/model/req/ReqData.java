package rh.cola.api.domain.zsxq.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: ReqData
 * @Author: Cola
 * @Time: 2023/3/23 1:16
 * @Description: ReqData
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqData {
    /**
     * text
     */
    private String text;
    /**
     * image_ids
     */
    private String[] image_ids = new String[]{};
    /**
     * silenced
     */
    private boolean silenced;

    public ReqData(String text, boolean silenced) {
        this.text = text;
        this.silenced = silenced;
    }
}
