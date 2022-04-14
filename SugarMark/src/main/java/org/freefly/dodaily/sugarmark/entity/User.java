package org.freefly.dodaily.sugarmark.entity;

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
    private int role;
    private String email;

    public User(String name, String password, String nickname) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }

    public User(int id, String name, String password, String nickname) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.nickname = nickname;
    }
}
