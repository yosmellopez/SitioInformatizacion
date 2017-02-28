package config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import utiles.MapeadorObjetos;
import utiles.ServicioInicio;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"servicios"})
@EnableJpaRepositories(basePackages = {"jpaSpringData"})
@EnableTransactionManagement
@EnableSpringDataWebSupport
@PropertySource(name = "configuracion", value = {"/WEB-INF/configuracion.properties"})
public class ConfiguracionGeneral {
    
    @Autowired
    Environment environment;

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/informatizacion");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword(environment.getProperty("password"));
        return driverManagerDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.FALSE);
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        Properties properties = new Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
//        properties.put("hibernate.c3p0.min_size", "5");
//        properties.put("hibernate.c3p0.max_size", "20");
//        properties.put("hibernate.c3p0.timeout", "300");
//        properties.put("hibernate.c3p0.max_statements", "50");
//        properties.put("hibernate.c3p0.idle_test_period", "3000");
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("clases");
        factory.setPersistenceUnitName("Sitio Informatizacion");
        factory.setJpaProperties(properties);
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean(name = "messageSource")
    public MessageSource configureMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }

    @Bean
    public MapeadorObjetos mapeadorObjetos() {
        return new MapeadorObjetos();
    }

    @Bean
    public ServicioInicio servicioInicio() {
        return new ServicioInicio();
    }
//
//    @Bean
//    public EncriptadorContrasena encriptadorContrasena() {
//        return new EncriptadorContrasena();
//    }
//
//    @Bean
//    public DireccionServidor direccionServidor() {
//        return new DireccionServidor();
//    }
}
