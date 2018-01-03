package com.musiccrawl.myexception;

/**
 * 抓取失败时抛出的异常
 */
public class FailedCrawlResultException extends RuntimeException {

    public FailedCrawlResultException() {
        super();
    }

    public FailedCrawlResultException(String message) {
        super(message);
    }

    public FailedCrawlResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedCrawlResultException(Throwable cause) {
        super(cause);
    }

    protected FailedCrawlResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
