package pers.lyks.spring.example.config;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lawyerance
 * @version 1.0 2018-08-06
 */
@Configuration
public class CasConfig {

    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AuthenticationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", "http://127.0.0.1/login");
        initParameters.put("serverName", "http://192.26.4.28:8080");
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(2);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    @Bean
    public FilterRegistrationBean ValidationFilterRegistrationBean(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", "http://127.0.0.1");
        initParameters.put("serverName", "http://192.26.4.28:8080");
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(1);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    @Bean
    public FilterRegistrationBean casHttpServletRequestWrapperFilter(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new HttpServletRequestWrapperFilter());
        authenticationFilter.setOrder(3);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");// 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }

    @Bean
    public FilterRegistrationBean casAssertionThreadLocalFilter(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AssertionThreadLocalFilter());
        authenticationFilter.setOrder(4);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");//
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
}
