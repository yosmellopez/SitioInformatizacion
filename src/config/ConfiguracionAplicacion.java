package config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ConfiguracionAplicacion extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ConfiguracionGeneral.class, ConfiguracionSeguridad.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfiguracionWeb.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{new DelegatingFilterProxy("springSecurityFilterChain"), new OpenEntityManagerInViewFilter(), encodingFilter, new MultipartFilter()};
    }

//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        String uploadDir = System.getProperty("catalina.base") + "/webapps/Postgrado/docs/";
//        MultipartConfigElement element = new MultipartConfigElement(uploadDir, 1000000000000000L, 1000000000000000L, 0);
//        registration.setMultipartConfig(element);
//    }
}
