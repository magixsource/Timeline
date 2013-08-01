package com.linpeng.svnbot.model;

import java.util.Date;

import javax.persistence.*;

/**
 * Simple User Bean
 *
 * @author linpeng
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class User extends GenericModel {

    @Id
    @GeneratedValue
    public Long id;

    public String name;// unique

    @Temporal(TemporalType.DATE)
    public Date createAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
