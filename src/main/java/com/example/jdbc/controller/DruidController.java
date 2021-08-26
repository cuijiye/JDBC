package com.example.jdbc.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidController {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控功能,ServletRegistrationBean类似于web.xml
    //因为SpringBoot内置了servlet容器， 所以没有web.xml， 用ServletRegistrationBean进行替代
    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //后台需要有人登录，账号密码
        Map<String, String> initParameters = new HashMap<>();

        initParameters.put("loginUsername","admin");//初始化可以登录的账户密码
        initParameters.put("loginPassword","111111");
        initParameters.put("allow","");//允许谁可以访问，空为所有人都可以访问
//        initParameters.put("allow","localhost");只有localhost可以访问
//        initParameters.put("cjy","192.168.123.123");//禁止谁访问
        registrationBean.setInitParameters(initParameters);//设置初始化参数
        return registrationBean;
    }

    //filter过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        //可以过滤的属性可以在new WebStatFilter()中查看
        Map<String, String> initParameters = new HashMap<>();
        //js css不进行过滤
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }
}
