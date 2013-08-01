package com.linpeng.svnbot.model;

import javax.persistence.*;
import java.util.List;

/**
 * Application Model
 *
 * @author linpeng
 * @version 1.0
 */
@Entity
@Table(name = "application")
public class Application extends GenericModel {
    @Id
    @GeneratedValue
    public Long id;
    public String name;
    public String title;
    public String summary;

    @ManyToMany
    public List<User> users;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
