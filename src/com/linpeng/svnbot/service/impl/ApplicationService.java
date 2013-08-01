package com.linpeng.svnbot.service.impl;

import com.avaje.ebean.Ebean;
import com.linpeng.svnbot.model.Application;
import com.linpeng.svnbot.service.BaseEbeanService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * application service
 *
 * @author linpeng
 * @version 1.0
 * @since: 13-7-31
 */
@Service("applicationService")
public class ApplicationService extends BaseServiceImpl {

    public ApplicationService() {
        super(Application.class);
    }

}
