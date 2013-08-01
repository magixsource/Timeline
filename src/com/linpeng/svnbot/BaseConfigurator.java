package com.linpeng.svnbot;

/**
 * Application configuration next to do
 *
 * @author linpeng
 * @version -1.0v
 */
public class BaseConfigurator {

    private String mode;// dev or production
    private int version;

    public boolean isDev() {
        return mode == null ? true : "dev".equalsIgnoreCase(mode);
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
