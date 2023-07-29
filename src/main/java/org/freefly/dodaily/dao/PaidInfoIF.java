package org.freefly.dodaily.dao;

import org.freefly.dodaily.entity.PaidInfo;
import org.freefly.dodaily.entity.PaidInfoSea;
import org.springframework.stereotype.Repository;

/**
 * interface for paid info service
 *
 * interface to be used by relevant service,
 * set and get info with database(mysql).
 * Implementation is dependent on mybatis.
 *
 * @author freefly365
 * @date 2023-07-29
 * */
@Repository
public interface PaidInfoIF {

    int insert(PaidInfo pi);
    int insertList(PaidInfo[] pia);
    PaidInfo[] getList(PaidInfoSea pis);
    int update(PaidInfo pi);
    int delete(PaidInfo pi);
    int doCount(PaidInfo pi);
}
