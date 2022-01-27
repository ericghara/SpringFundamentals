package org.ericghara.service;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

@Service
@Primary
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.DEFAULT)
public class RandomNumberService implements NumberService {

    /*
    Scopes:
    SCOPE_APPLICATION: Singleton, always the same
    SCOPE_SESSION: Same instance per user per session (timeout ~25 min)
    SCOPE_REQUEST: create a new instance with each request
     */

    /*
    * TARGET_CLASS: proxy injects by class target
    * INTERFACES: injects by interface
    * DEFAULT: prefers interface if available, if not injects by class
    * */

    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
