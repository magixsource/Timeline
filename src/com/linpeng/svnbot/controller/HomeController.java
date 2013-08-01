package com.linpeng.svnbot.controller;

import com.ctlok.springframework.web.servlet.view.rythm.RythmViewResolver;
import com.linpeng.svnbot.BaseConfigurator;
import com.linpeng.svnbot.filter.LogFileFilter;
import com.linpeng.svnbot.model.Application;
import com.linpeng.svnbot.model.Commit;
import com.linpeng.svnbot.model.SVNMessage;
import com.linpeng.svnbot.model.User;
import com.linpeng.svnbot.service.BaseEbeanService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Main Controller
 *
 * @author linpeng
 * @version 1.0
 */
@Controller
public class HomeController {
    @Autowired
    RythmViewResolver rythmViewResolver;

    @Autowired
    BaseConfigurator baseConfigurator;

    @Autowired
    BaseEbeanService svnMessageService;
    @Autowired
    BaseEbeanService userService;
    @Autowired
    BaseEbeanService applicationService;

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static final String logFilePath = "F:\\GitHub\\timeline\\log";

    public static final org.slf4j.Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/read")
    public String readFromLog() {
        File file = new File(logFilePath);
        if (file.isDirectory()) {
            try {
                File[] files = file.listFiles(new LogFileFilter());
                Set<SVNMessage> set = new HashSet<SVNMessage>();
                SVNMessage msg = null;
                for (File file2 : files) {
                    Long revesion = Long.valueOf(file2.getName().substring(8));
                    String path = file2.getAbsoluteFile().toString();
                    Commit commit = new com.linpeng.svnbot.model.SVNInfo(path,
                            revesion).getCommit();
                    msg = new SVNMessage();
                    msg.setDiff(commit.getDiff().substring(0, 250));
                    User author = (User) userService.findByName(commit
                            .getAuthor());
                    if (null == author) {
                        author = new User();
                        author.setCreateAt(new Date());
                        author.setName(commit.getAuthor());
                        userService.save(author);
                    }
                    msg.setAuthor(author);
                    msg.setLog(commit.getLog());
                    msg.setRevision(commit.getRevision());
                    String time = commit.getTime().substring(0, 19).trim();
                    msg.setTime(sdf.parse(time));
                    msg.setType(1);
                    // add it to set
                    set.add(msg);
                }
                int i = svnMessageService.saveAll(set);
                if (logger.isDebugEnabled()) {
                    logger.debug("Read logfile success ! :" + i);
                }
            } catch (ParseException e) {
                logger.error("Read logfile failure!");
            }
        }
        return "index";
    }

    @RequestMapping("/")
    public String index(Model model) throws IOException {
        List<Application> applications = (List<Application>) applicationService.findAll();
        model.addAttribute("applications", applications);
        return "index";
    }


    @RequestMapping("/app/{appId}/users")
    public String appusers(@PathVariable
                           Long appId, Model model) {
        Application app = (Application) applicationService.findById(appId);
        List<User> users = app.getUsers();
        model.addAttribute("users", users);
        return "/ajax/users";
    }

    @RequestMapping("/app/{appId}/user/{userId}/commits")
    public String messages(@PathVariable Long appId, @PathVariable Long userId, Model model) {
        List<SVNMessage> messages = (List<SVNMessage>) svnMessageService.findByApplication(userId);
        if (logger.isDebugEnabled()) {
            logger.debug("application:" + appId + " and user:" + userId + " got commit:" + messages.size());
        }
        model.addAttribute("messages", messages);
        return "/ajax/commits";
    }

}
