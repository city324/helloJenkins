//package com.wakeup.hellojenkins.config;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
//import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
//import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
//import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
//import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
//import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
//import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
//@Configuration // 标明是配置类
//@EnableSwagger2 //开启swagger功能
//@EnableWebMvc
//public class SwaggerConfig2 {
//  @Bean
//  public Docket createRestApi() {
//    return new Docket(DocumentationType.SWAGGER_2)  // DocumentationType.SWAGGER_2 固定的，代表swagger2
////                .groupName("分布式任务系统") // 如果配置多个文档的时候，那么需要配置groupName来分组标识
//        .apiInfo(apiInfo()) // 用于生成API信息
//        .select() // select()函数返回一个ApiSelectorBuilder实例,用来控制接口被swagger做成文档
//        .apis(RequestHandlerSelectors.basePackage("com.wakeup.hellojenkins.controller")) // 用于指定扫描哪个包下的接口
//        .paths(PathSelectors.any())// 选择所有的API,如果你想只为部分API生成文档，可以配置这里
//        .build();
//  }
//
//  /**
//   * 用于定义API主界面的信息，比如可以声明所有的API的总标题、描述、版本
//   * @return
//   */
//  private ApiInfo apiInfo() {
//    return new ApiInfoBuilder()
//        .title("hello_jenkins项目API") //  可以用来自定义API的主标题
//        .description("hello_jenkins项目SwaggerAPI管理") // 可以用来描述整体的API
//        .termsOfServiceUrl("") // 用于定义服务的域名
//        .version("1.0") // 可以用来定义版本。
//        .build(); //
//  }
//
//  /**
//   * 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
//   **/
//  @Bean
//  public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
//    List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
//    Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
//    allEndpoints.addAll(webEndpoints);
//    allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
//    allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
//    String basePath = webEndpointProperties.getBasePath();
//    EndpointMapping endpointMapping = new EndpointMapping(basePath);
//    boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
//    return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
//  }
//  private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
//    return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
//  }
//}
