package com.winowsi.studySwagger.config;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerTypePredicate;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @Description: Swagger的配置类
 * @author: zhaoyao
 * @date: 2021/08/22
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     * @param environment
     * @return
     * 默认分组
     */

    @Bean
    public Docket docket(Environment environment){

        /**
         * 设置要显示的swagger环境
         */
        Profiles profiles = Profiles.of("dev", "test");
        /**
         * 获取项目环境
         *      environment.acceptsProfiles(profiles)判断是否属于自己设定的项目环境
         */
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)//默认的值是true
                .select()
                /**
                 * RequestHandlerSelectors:配置需要扫描的接口的扫描方式
                 *      basePackage("com.winowsi.studySwagger.controller")
                 *      any()全部扫描
                 *      none()不扫描
                 *      withMethodAnnotation(GetMapping.class)扫描方法上的注解
                 *      withClassAnnotation(Controller.class)扫描类上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("com.winowsi.studySwagger.controller"))
                /**
                 * paths()过滤什么路径
                 *      @ant()过滤掉所有路径
                 *
                 */
                //.paths(PathSelectors.ant("*/winowsi/**"))
                .build();
    }
    /**
     * 配置swagger信息
     */
    private ApiInfo apiInfo(){
        Contact DEFAULT_CONTACT = new Contact("zhaoYao", "localhost/index", "winowsi@outlook.com");
        return new ApiInfo(
                "后端所有的接口",
                "所以接口的相关信息文档",
                "1.0",
                "localhost:127.0.0.1",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
    /**
     * 配置多个分组：Teacher
    */
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Teacher");
    }

}
