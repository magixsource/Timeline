package com.linpeng.svnbot.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.linpeng.svnbot.model.GenericModel;
import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.linpeng.svnbot.model.SVNMessage;
import com.linpeng.svnbot.service.BaseEbeanService;

/**
 * Svn message service
 *
 * @author linpeng
 * @version 1.0
 */
@Service("svnMessageService")
public class SVNMessageServiceImpl extends BaseServiceImpl {

    public SVNMessageServiceImpl() {
        super(SVNMessage.class);
    }

    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Map findAllMap() {
        List<SVNMessage> list = Ebean.find(SVNMessage.class).findList();
        Set<String> nameSet = generateSetFrom(list, "name");
        Set<String> daySet = generateSetFrom(list, "day");
        Map<String, Map<String, List<SVNMessage>>> resultMap = generateMessageMap(
                nameSet, daySet, list);
        return resultMap;
    }

    private Set<String> generateSetFrom(List<SVNMessage> list, String flag) {
        Set<String> result = new HashSet<String>();
        if ("name".equalsIgnoreCase(flag)) {
            for (SVNMessage msg : list) {
                result.add(msg.getAuthor().getName());
            }
        } else if ("day".equalsIgnoreCase(flag)) {
            for (SVNMessage msg : list) {
                result.add(sdf.format(msg.getTime()));
            }
        }
        return result;
    }

    private Map<String, Map<String, List<SVNMessage>>> generateMessageMap(
            Set<String> nameSet, Set<String> daySet, List<SVNMessage> list) {
        Map<String, Map<String, List<SVNMessage>>> resultMap = new HashMap<String, Map<String, List<SVNMessage>>>(
                nameSet.size());
        Map<String, List<SVNMessage>> map = null;
        for (String key : nameSet) {
            List<SVNMessage> subList = filterList(key, list);
            map = filterMap(daySet, subList);
            resultMap.put(key, map);
        }
        return resultMap;
    }

    private Map<String, List<SVNMessage>> filterMap(Set<String> daySet,
                                                    List<SVNMessage> list) {
        Map<String, List<SVNMessage>> result = new HashMap<String, List<SVNMessage>>();
        for (String day : daySet) {
            List<SVNMessage> subList = new ArrayList<SVNMessage>();
            for (SVNMessage message : list) {
                if (day != sdf.format(message.getTime())) {
                    subList.add(message);
                }
            }
            result.put(day, subList);
        }

        return result;
    }

    private List<SVNMessage> filterList(String key, List<SVNMessage> list) {
        List<SVNMessage> subList = new ArrayList<SVNMessage>();
        for (SVNMessage message : list) {
            if (null != message.getAuthor()
                    && key.equalsIgnoreCase(message.getAuthor().getName())) {
                subList.add(message);
            }
        }
        return subList;
    }

    @Override
    public List<SVNMessage> findByAuthor(String name) {
        return Ebean.find(SVNMessage.class).where("author.name=:name")
                .setParameter("name", name).findList();
    }

    @Override
    public List<SVNMessage> findByApplication(Long id) {
        return Ebean.find(SVNMessage.class).where("author.id=:id")
                .setParameter("id", id).findList();
    }


}
