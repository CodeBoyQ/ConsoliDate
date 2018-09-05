package com.codeboyq;

public class ConsoliDateException extends Exception {

    private static final long serialVersionUID = -4353093866899178794L;

    public ConsoliDateException(String message) {
        super (message);
    }

    public ConsoliDateException(Throwable cause) {
        super (cause);
    }

    public ConsoliDateException(String message, Throwable cause) {
        super (message, cause);
    }

}
