package com.github.damianmcdonald.springlongpolling;

import com.github.damianmcdonald.springlongpolling.longpolling.LongPollingEventSimulator;
import com.github.damianmcdonald.springlongpolling.longpolling.LongPollingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UiController {


    private static final Logger LOGGER = Logger.getLogger(UiController.class.getName());

    @Autowired
    LongPollingEventSimulator simulator;

    @RequestMapping("/register/{registrationId}")
    @ResponseBody
    public DeferredResult<String> registerClient(@PathVariable("registrationId") final long registrationId) {
        LOGGER.log(Level.INFO, "Registering client for registrationId id: " + registrationId);
        final DeferredResult<String> deferredResult = new DeferredResult<>();
        simulator.getPollingMap().put(registrationId, new LongPollingSession(registrationId, deferredResult));
        return deferredResult;
    }

    @RequestMapping("/simulate/{registrationId}")
    @ResponseBody
    public String simulateEvent(@PathVariable("registrationId") final long registrationId) {
        LOGGER.log(Level.INFO, "Simulating event for registrationId id: " + registrationId);
        simulator.simulateIncomingNotification(registrationId);
        return "Simulating event for registrationId Id: " + registrationId;
    }
}


