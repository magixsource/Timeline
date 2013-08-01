package com.linpeng.svnbot.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.linpeng.svnbot.model.GenericModel;
import com.linpeng.svnbot.model.SVNMessage;

/**
 * Basic service base on Ebean
 * Some interface is not supported
 *
 * @author linpeng
 * @version 1.0
 */
public interface BaseEbeanService {

    public void save(GenericModel obj);

    public int saveAll(Collection<?> c);

    public void delete(GenericModel obj);

    public Object findById(Object id);

    public List<? extends GenericModel> findAll();

    public Map findAllMap();

    public List<? extends GenericModel> findByAuthor(String name);

    public List<? extends GenericModel> findByApplication(Long id);

    public Object findByName(String name);

    public List<GenericModel> fetch(int offset, int length,
                                    String orderBy, String orderDirection, List<String> properties,
                                    String keywords, String where);

    public List<GenericModel> find(String where,
                                   List<String> properties);

}
