package org.freefly.dodaily.userservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.freefly.dodaily.common.entity.UserCookie;
import org.springframework.stereotype.Repository;

@Repository
public interface ICookieDao {

    public int insertCookie(@Param("cookie") UserCookie userCookie);

    public UserCookie selectCookie(@Param("id") int id);

    public int deleteCookies(@Param("id") int id);
}
