package com.linpeng.svnbot.model;

import java.io.UnsupportedEncodingException;

/**
 * Commit(SVN) bean
 *
 * @author linpeng
 * @version 1.0
 */
public class Commit {

    private String log;
    private String author;
    private String time;
    private String diff;
    private long revision;

    public Commit(String log, String author, String time, String diff,
                  long revision) {
        this.log = filter(log);
        this.author = filter(author);
        this.time = filter(time);
        this.diff = filter(diff);
        this.revision = revision;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = filter(log);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = filter(author);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = filter(time);
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = filter(diff);
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    // ---------------Utils--------------/

    /**
     * @param str
     * @param srcCharset
     * @param distCharset
     * @return
     */
    private String undecode(String str, String srcCharset, String distCharset) {
        try {
            return new String(str.getBytes(srcCharset), distCharset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String filter(String str) {
        return undecode(str, "gbk", "utf8");
    }

    @Override
    public String toString() {
        return "Commit{" + "log='" + log + '\'' + ", author='" + author + '\''
                + ", time='" + time + '\'' + ", diff='" + diff + '\''
                + ", revision='" + revision + '\'' + '}';
    }

    public String toJson() {
        return "";
    }

}
