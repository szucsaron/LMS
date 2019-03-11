package com.codecool.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets deployed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
