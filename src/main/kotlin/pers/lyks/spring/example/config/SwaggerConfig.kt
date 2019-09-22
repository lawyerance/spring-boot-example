package pers.lyks.spring.example.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Value("\${springfox.swagger.enabled}")
    private val enable: Boolean = false

    @Bean
    fun createRestApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("pers.lyks.spring.example"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("API接口测试平台")
                .description("提供后台所有Restful接口, 优雅的Restful风格")
                .termsOfServiceUrl("http://127.0.0.1:8080")
                .version("1.0")
                .build()
    }

}
