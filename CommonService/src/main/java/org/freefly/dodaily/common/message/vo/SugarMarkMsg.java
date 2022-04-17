package org.freefly.dodaily.common.message.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.freefly.dodaily.common.entity.SugarMark;
import org.freefly.dodaily.common.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugarMarkMsg {

    private String name;
    private User user;
    private SugarMark sugarMark;
}
