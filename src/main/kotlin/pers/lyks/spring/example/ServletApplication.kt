package pers.lyks.spring.example

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 *
 * @author lawyerance
 * @version 1.0 2019-09-22
 */
@SpringBootApplication(exclude = [MybatisAutoConfiguration::class])
open class ServletApplication

fun main(args: Array<String>) {
    runApplication<ServletApplication>(*args)
}


