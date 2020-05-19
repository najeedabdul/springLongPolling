package com.github.damianmcdonald.springlongpolling.longpolling;

import com.github.damianmcdonald.springlongpolling.persistence.model.NodeNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.logging.Logger;

@Component
public class LongPollingEventSimulator {

    private static final Logger LOGGER = Logger.getLogger(LongPollingEventSimulator.class.getName());
    private final Map<Long, LongPollingSession> longPollingMap = new HashMap<Long, LongPollingSession>();

    @Autowired
    private MessagePayloadUtil messageUtil;

    // Simulated event handler
    public void simulateIncomingNotification(final long registrationId) {
        final String randomData = messageUtil.getMessagePayload(); // keep the same payload data per cluster node
            final NodeNotification notification = new NodeNotification();
            notification.setRegistrationId(registrationId);
            notification.setTimestamp(new Date());
            notification.setNotificationPayload(randomData);
        simulateOutgoingNotification(notification);
    }

    public void simulateOutgoingNotification(final NodeNotification notification) {
            if(getPollingMap().containsKey(notification.getRegistrationId()))
            {
                getPollingMap().get(notification.getRegistrationId()).getDeferredResult().setResult(notification.getNotificationPayload());
                getPollingMap().remove(notification.getRegistrationId());
            }
    }

    public Map<Long, LongPollingSession> getPollingMap() {
        return longPollingMap;
    }
}