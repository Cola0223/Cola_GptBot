package rh.cola.api.domain.zsxq.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name: AnswerRes
 * @Author: Cola
 * @Time: 2023/3/23 1:16
 * @Description: AnswerRes
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRes {
    /**
     * succeeded
     */
    private boolean succeeded;

    public boolean isSucceeded() {
        return succeeded;
    }

}
