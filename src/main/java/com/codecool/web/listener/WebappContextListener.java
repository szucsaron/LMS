package com.codecool.web.listener;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        registerCharacterEncodingFilter(sce);
        DataSource dataSource = putDataSourceToServletContext(sce);

    }

    private void registerCharacterEncodingFilter(ServletContextEvent sce) {
        /*
            This filter intercepts every requests and sets the character encoding correctly.
            This doesn't do magic by itself, you need to set the character encoding for each
            page directly (.html, .jsp) - preferably to UTF-8.
         */
        sce.getServletContext().addFilter("SetCharacterEncodingFilter", "org.apache.catalina.filters.SetCharacterEncodingFilter");
    }

    private DataSource putDataSourceToServletContext(ServletContextEvent sce) {
        try {
            /*
                Here we're looking up the resource defined in the web.xml
                in a JNDI context (made available by the webserver).
                The DataSource resource reference is extracted from JNDI and put into
                each servlet's context so that each could access and use it for
                handling incoming requests.
            */
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/lms");
            ServletContext servletCtx = sce.getServletContext();
            servletCtx.setAttribute("dataSource", dataSource);
            return dataSource;
        } catch (NamingException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
