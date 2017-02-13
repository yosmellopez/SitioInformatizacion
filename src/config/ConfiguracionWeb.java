package config;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import utiles.MapeadorObjetos;

@Configuration
@ComponentScan(basePackages = {"control"})
@EnableWebMvc
@EnableAspectJAutoProxy
public class ConfiguracionWeb extends WebMvcConfigurerAdapter implements ApplicationContextAware{

    @Autowired
    MapeadorObjetos mapeadorObjetos;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(mapeadorObjetos);
        converters.add(converter);
        super.configureMessageConverters(converters);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/videos/**", "/plantilla/**", "/img/**", "/icons/**", "/font/**", "/fonts/**", "/flaty/**", "/docs/**", "/app/**", "/theme-classic/**", "/recursos/**")
                .addResourceLocations("/videos/", "/plantilla/", "/img/", "/icons/", "/font/", "/fonts/", "/flaty/", "/docs/", "/app/", "/theme-classic/", "/recursos/")
                .setCacheControl(CacheControl.noCache());
    }
//
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(true).ignoreAcceptHeader(true).useJaf(false).defaultContentType(MediaType.TEXT_HTML).
//                mediaType("html", MediaType.TEXT_HTML).
//                mediaType("htm", MediaType.TEXT_HTML).
//                mediaType("xml", MediaType.APPLICATION_XML).
//                mediaType("do", MediaType.MULTIPART_FORM_DATA).
//                mediaType("json", MediaType.APPLICATION_JSON);
//        super.configureContentNegotiation(configurer); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Bean(name = "contentNegotiatingViewResolver")
//    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//        List<ViewResolver> resolvers = new LinkedList<>();
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//        viewResolver.setViewClass(JstlView.class);
//        resolvers.add(viewResolver);
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setContentNegotiationManager(manager);
//        resolver.setViewResolvers(resolvers);
//        return resolver;
//    }
    
    private static final String UTF8 = "UTF-8";

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(UTF8);
        return resolver;
    }

    private TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        Set<IDialect> additionalDialects = new LinkedHashSet<>();
        additionalDialects.add(new SpringSecurityDialect());
        engine.setAdditionalDialects(additionalDialects);
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/WEB-INF/vista/");
        resolver.setSuffix(".html");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        AuthenticationPrincipalArgumentResolver resolver = new AuthenticationPrincipalArgumentResolver();
//        PageableHandlerMethodArgumentResolver phmar = new PageableHandlerMethodArgumentResolver();
//        phmar.setOneIndexedParameters(true);
//        phmar.setSizeParameterName("limit");
//        SpecificationArgumentResolver sar = new SpecificationArgumentResolver();
//        argumentResolvers.add(resolver);
//        argumentResolvers.add(phmar);
//        argumentResolvers.add(sar);
//        super.addArgumentResolvers(argumentResolvers);
//    }
//
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//
//    @Bean
//    public JavaMailSenderImpl mailSenderImpl() {
//        JavaMailSenderImpl impl = new JavaMailSenderImpl();
//        impl.setHost("10.22.1.2");
//        impl.setPort(25);
//        impl.setUsername("yosmellp");
//        impl.setPassword("miultpassword3*");
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", true);
//        properties.put("mail.smtp.starttls.enable", false);
//        impl.setJavaMailProperties(properties);
//        return impl;
//    }
}
