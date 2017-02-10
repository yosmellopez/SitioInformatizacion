package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import utiles.AutenticacionExitosa;
import utiles.AutenticacionFallida;
import utiles.ManejadorLogout;
import utiles.ServicioInicio;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private ServicioInicio customUserDetailsService;
    
    @Autowired
    ReloadableResourceBundleMessageSource messageSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
        registry.authenticationProvider(authenticationProvider());
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/plantilla/**", "/img/**", "/icons/**", "/font/**", "/fonts/**", "/flaty/**", "/docs/**", "/app/**", "/theme-classic/**", "/recursos/**");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/admin/**").hasAuthority("Administrador").and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/login_check").defaultSuccessUrl("/inicio.html").successHandler(autenticacionExitosa()).failureHandler(autenticacionFallida())
                .usernameParameter("usuario").passwordParameter("password").and()
                .logout().logoutSuccessHandler(manejadorLogout()).logoutUrl("/salir.html").logoutSuccessUrl("/login.html").and()
                .exceptionHandling().accessDeniedPage("/denegado.html");
        http.headers().defaultsDisabled().cacheControl();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setMessageSource(messageSource);
        return provider;
    }
    
    @Bean
    public SaltSource saltSource() throws Exception {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("usuario");
        saltSource.afterPropertiesSet();
        return saltSource;
    }
    
    @Bean
    public AutenticacionExitosa autenticacionExitosa() {
        return new AutenticacionExitosa();
    }

    @Bean
    public AutenticacionFallida autenticacionFallida() {
        AutenticacionFallida fallida = new AutenticacionFallida();
        fallida.setUsernameParameter("usuario");
        fallida.setDefaultFailureUrl("/login.html?error");
        return fallida;
    }
    
    @Bean
    public ManejadorLogout manejadorLogout() {
        return new ManejadorLogout();
    }
}
