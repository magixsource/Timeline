package com.linpeng.svnbot.exception;

/**
 * Exception that application information is not exists
 *
 * @author linpeng
 * @version 1.0
 */
public class ApplicationNotExistsException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ApplicationNotExistsException() {
        super();
    }

    public ApplicationNotExistsException(String msg) {
        super(msg);
    }

}