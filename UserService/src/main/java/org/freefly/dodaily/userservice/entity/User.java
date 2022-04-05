package org.freefly.dodaily.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String password;
    private String nickname;
    private Integer phone;
    private Short phoneRegion;
    private Byte role;
    private String email;
}
