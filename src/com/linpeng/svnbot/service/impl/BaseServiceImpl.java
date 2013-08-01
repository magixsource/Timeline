package com.linpeng.svnbot.service.impl;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxType;
import com.avaje.ebean.annotation.Transactional;
import com.linpeng.svnbot.model.GenericModel;
import com.linpeng.svnbot.service.BaseEbeanService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Ebean service interface Base and abstract implement
 *
 * @author linpeng
 * @version 1.0
 * @since 13-7-31
 */
public abstract class BaseServiceImpl implements BaseEbeanService {

    private Class injectClass;

    public BaseServiceImpl() {

    }

    protected BaseServiceImpl(
            Class<? extends GenericModel> injectClass) {
        if (null == injectClass
                || !GenericModel.class
                .isAssignableFrom(injectClass)) {
            throw new UnsupportedOperationException("Model "
                    + injectClass.getName()
                    + " is not extends GenericModel");
        }
        this.injectClass = injectClass;
    }

    @Override
    @Transactional(type = TxType.REQUIRES_NEW)
    public void save(GenericModel obj) {
        Ebean.save(obj);
    }

    @Override
    public int saveAll(Collection<?> c) {
        return Ebean.save(c);
    }

    @Override
    public void delete(GenericModel obj) {
        Ebean.save(obj);
    }

    @Override
    public Object findById(Object id) {
        return Ebean.find(injectClass, id);
    }

    @Override
    public List<? extends GenericModel> findAll() {
        return Ebean.find(injectClass).findList();
    }

    @Override
    public Map findAllMap() {
        return null;
    }

    @Override
    public List<? extends GenericModel> findByAuthor(String name) {
        return null;
    }

    @Override
    public List<? extends GenericModel> findByApplication(Long id) {
        return null;
    }

    @Override
    public Object findByName(String name) {
        return null;
    }

    @Override
    public List<GenericModel> fetch(int offset, int length, String orderBy, String orderDirection, List<String> properties, String keywords, String where) {
        return null;
    }

    @Override
    public List<GenericModel> find(String where, List<String> properties) {
        return null;
    }
}
