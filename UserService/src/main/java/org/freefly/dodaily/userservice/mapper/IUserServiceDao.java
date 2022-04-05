package org.freefly.dodaily.userservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.freefly.dodaily.userservice.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserServiceDao {

    public int Register(@Param("user") User user);

    public User getUserByName(@Param("name") String name);

    public User getUserById(@Param("id") int id);

    public int updateUser(@Param("user") User user);

    public int deleteUser(@Param("id") int id);
}
