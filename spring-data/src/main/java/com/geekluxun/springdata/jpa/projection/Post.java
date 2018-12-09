package com.geekluxun.springdata.jpa.projection;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: luxun
 * @Create: 2018-12-09 14:52
 * @Description:
 * @Other:
 */
@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Column
    private String createdBy;

    @Column
    private Date createdOn;
}
