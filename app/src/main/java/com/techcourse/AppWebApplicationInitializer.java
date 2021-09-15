package com.techcourse;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import nextstep.mvc.DispatcherServlet;
import nextstep.mvc.controller.tobe.AnnotationHandlerMapping;
import nextstep.mvc.controller.tobe.HandlerExecutionHandlerAdaptor;
import nextstep.mvc.controller.tobe.InterfaceBasedControllerHandlerAdapter;
import nextstep.web.WebApplicationInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppWebApplicationInitializer implements WebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(AppWebApplicationInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) {
        final DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        3단계 조건에 의한 deprecated
//        dispatcherServlet.addHandlerMapping(new ManualHandlerMapping());
        dispatcherServlet.addHandlerMapping(new AnnotationHandlerMapping(getClass().getPackageName()));

        dispatcherServlet.addHandlerAdaptor(new InterfaceBasedControllerHandlerAdapter());
        dispatcherServlet.addHandlerAdaptor(new HandlerExecutionHandlerAdaptor());

        final ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        log.info("Start AppWebApplication Initializer");
    }
}
