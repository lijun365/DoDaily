package org.freefly.dodaily.userservice.service.Impl;

import org.freefly.dodaily.userservice.entity.User;
import org.freefly.dodaily.userservice.mapper.IUserServiceDao;
import org.freefly.dodaily.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserServiceDao dao;

    @Override
    public int Register(User user) {
        return dao.Register(user);
    }

    @Override
    public boolean Login(User user) {
        return false;
    }

    @Override
    public User getUserByName(String name) {
        return dao.getUserByName(name);
    }

    @Override
    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return dao.updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return dao.deleteUser(id);
    }

    @Override
    public List<String> getAllUserName() {
        return dao.getAllUserName();
    }

    @Override
    public int getNumberOfUser() {
        return dao.getNumberOfUser();
    }
}
