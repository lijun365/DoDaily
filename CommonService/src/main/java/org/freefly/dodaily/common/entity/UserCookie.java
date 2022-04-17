package org.freefly.dodaily.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCookie {
    private Integer userId;
    private String cookie;
    private Date datetime;
}
