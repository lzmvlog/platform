package com.wisdompoint.platform.configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author： ShaoJie
 * @data： 2019年12月25日 14:50
 * @Description： 配置druid
 */
@Configuration
public class DruidConfiguration {

    // 配置 druid 数据监控
    // 配置一个管理后台的 servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParam = new HashMap<>();
        // ResourceServlet
        initParam.put("loginUsername", "zdhc");
        initParam.put("loginPassword", "123456");

        // 配置初始化参数
        bean.setInitParameters(initParam);
        return bean;
    }

    // 配置一个监控的 filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        return bean;
    }
}
