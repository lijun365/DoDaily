package org.freefly.dodaily.entity;

import lombok.Data;

/**
 * Entity for Paid service
 * @usage new PaidInfo
 *
 * the instance to reflect the paid info.
 * it will be stored to the database also be created as a container for the data from database
 *
 * @author freefly365
 * @date 2023-07-25
 * */

@Data
public class PaidInfo {
    private Integer id;
    private PaidCategory paidCategory;
    private Double expense;
    private String createTime;
    private String updateTime;

}
