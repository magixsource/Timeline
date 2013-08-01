package com.linpeng.svnbot.model;

import java.util.Date;

import javax.persistence.*;

/**
 * SVN Message bean
 *
 * @author linpeng
 * @version 1.0
 */
@Entity
@Table(name = "svnmessage")
public class SVNMessage extends GenericModel {

    @Id
    @GeneratedValue
    public Long id;

    @Column(length = 1000)
    private String log;

    @ManyToOne
    private User author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Column(length = 1000)
    private String diff;
    /**
     * 1.commit 2.delete 3.marge 4.other ...
     */
    private int type;

    /**
     * version
     */
    private long revision;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

}
