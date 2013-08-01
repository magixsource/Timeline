package com.linpeng.svnbot.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.linpeng.svnbot.model.GenericModel;
import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;
import com.linpeng.svnbot.model.User;
import com.linpeng.svnbot.service.BaseEbeanService;

/**
 * User service
 *
 * @author linpeng
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl {

    public UserServiceImpl() {
        super(User.class);
    }

    @Override
    public Object findByName(String name) {
        return Ebean.find(User.class).where("name=:name").setParameter("name",
                name).findUnique();
    }

}
