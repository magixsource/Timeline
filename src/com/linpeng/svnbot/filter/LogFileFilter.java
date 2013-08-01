package com.linpeng.svnbot.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * Log file filter
 * Check logfile is ok or illegal
 *
 * @author linpeng
 * @version 1.0
 */
public class LogFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        String fileName = pathname.getName();
        try {
            if (fileName.startsWith("svnfile_")
                    && Integer.valueOf(fileName.substring(8)) > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Illegal logfile :" + fileName);
        }
        return false;
    }

}
