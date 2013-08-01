package com.linpeng.svnbot.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Generic Model Bean.
 *
 * @author linpeng
 * @version 1.0
 * @since 13-7-31
 */
public class GenericModel implements Serializable {
    @Id
    @GeneratedValue
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object _key() {
        return getId();
    }
}
