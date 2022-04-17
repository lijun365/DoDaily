package org.freefly.dodaily.userservice.service.Impl;

import org.freefly.dodaily.common.entity.UserCookie;
import org.freefly.dodaily.userservice.mapper.ICookieDao;
import org.freefly.dodaily.userservice.service.ICookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService implements ICookie {

    @Autowired
    private ICookieDao dao;

    @Override
    public int insertCookie(UserCookie userCookie) {
        return dao.insertCookie(userCookie);
    }

    @Override
    public UserCookie selectCookie(int id) {
        return dao.selectCookie(id);
    }

    @Override
    public int deleteCookies(int id) {
        return dao.deleteCookies(id);
    }
}
