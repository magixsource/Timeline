package com.linpeng.svnbot.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * SVN information bean
 *
 * @author linpeng
 * @version 1.0
 */
public class SVNInfo {

    private String svnServer;
    private long revision;
    private Commit commit;

    private BufferedReader reader;

    public SVNInfo(String svnServer, long revision) {
        this.svnServer = svnServer;
        this.revision = revision;
    }

    public String toString() {
        return null == getCommit() ? "" : getCommit().toString();
    }

    private File getLogFile() {
        return new File(getLogFilePath());
    }

    private String getLogFilePath() {
        return svnServer;
    }

    public Commit getCommit() {
        if (null == commit) {
            try {
                reader = new BufferedReader(new FileReader(getLogFile()));
                String readBuffer = "";
                int lineNumber = 1;
                String log = "";
                String author = "";
                String time = "";
                String diff = "";

                while ((readBuffer = reader.readLine()) != null) {
                    if (lineNumber == 1) {
                        log = readBuffer;
                    } else if (lineNumber == 2) {
                        author = readBuffer;
                    } else if (lineNumber == 3) {
                        time = readBuffer;
                    } else {
                        diff += readBuffer + "\r\n";
                    }
                    lineNumber++;
                }
                commit = new Commit(log, author, time, diff, revision);
                reader.close();
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        return commit;
    }

}
