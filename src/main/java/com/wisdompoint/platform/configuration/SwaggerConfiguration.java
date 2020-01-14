package com.wisdompoint.platform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author： ShaoJie
 * @data： 2019年12月10日 15:45
 * @Description： 配置 swagger 生成接口文档
 * 文档访问地址 http://127.0.0.1:8090/doc.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * 生成文档的 api
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        // DocumentationType 文件类型
        return new Docket(DocumentationType.SWAGGER_2)
                // 文档的信息
                .apiInfo(apiInfo())
                // 选择
                .select()
                // 根据哪个包下去生成文档
                .apis(RequestHandlerSelectors.basePackage("com.wisdompoint.platform.controller"))
                // 路径
                .paths(PathSelectors.any())
                .build();
    }

    /**Unstaged changes after reset:
     * api 的信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档的主题
                .title("Spring Boot中使用Swagger2构建 API 文档")
                // 文档的描述
                .description("项目使用")
                // 文档的版本
                .version("1.0")
                // 更新服务条款的网址
                // .termsOfServiceUrl()
                // 更新负责此API的人员的联系信息 这里可是 String 一段描述 也能是 Contact 联系对象
                // .contact()
                // 这个API更新许可证信息
                // .license()
                // 更新此API的许可证网址
                // .licenseUrl()
                // 添加此API的扩展
                // .extensions()
                .build();
    }


}
