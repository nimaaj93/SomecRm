package com.farafarin.mata.web.config;

import com.farafarin.mata.common.web.CORSFilter;
import com.farafarin.mata.web.config.security.AuthorizationServerConfiguration;
import com.farafarin.mata.web.config.security.ResourceServerConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by azhdari on 10/9/2017.
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class,
                SecurityConfig.class,
                AuthorizationServerConfiguration.class,
                ResourceServerConfiguration.class,
                MethodSecurityConfig.class,
                DaoConfig.class,
                EmailConfig.class,
                SoapClientConfig.class,
                ThreadConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/rest/*", "/oauth/token"};
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addFilter("simpleCorsFilter", new CORSFilter()).addMappingForUrlPatterns(null, false, "/oauth/token");
        super.onStartup(servletContext);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("dispatchOptionsRequest", "true");
        super.customizeRegistration(registration);
    }

}
