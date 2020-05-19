package com.github.damianmcdonald.springlongpolling.longpolling;


import org.springframework.web.context.request.async.DeferredResult;

public class LongPollingSession {

    private final long registrationId;
    private final DeferredResult<String> deferredResult;

    public LongPollingSession(final long registrationId, final DeferredResult<String> deferredResult) {
        this.registrationId = registrationId;
        this.deferredResult = deferredResult;
    }

    public long getRegistrationId() {
        return registrationId;
    }

    public DeferredResult<String> getDeferredResult() {
        return deferredResult;
    }
}
