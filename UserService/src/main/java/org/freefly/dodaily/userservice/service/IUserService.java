package org.freefly.dodaily.userservice.service;

import org.freefly.dodaily.userservice.entity.User;

public interface IUserService {

    public int Register(User user);

    public boolean Login(User user);

    public User getUserByName(String name);

    public User getUserById(int id);

    public int updateUser(User user);

    public int deleteUser(int id);
}
