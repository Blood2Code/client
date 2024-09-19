package org.task;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.task.config.AppConfig;

public class App {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Create a web application context
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);

        // Create a context and add it to Tomcat
        org.apache.catalina.Context context = tomcat.addContext("", null);

        // Create and register the DispatcherServlet
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        Tomcat.addServlet(context, "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/", "dispatcher");

        // Start the Tomcat server
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
