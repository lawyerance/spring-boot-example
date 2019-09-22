package pers.lyks.spring.example.config

import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.TransactionManagementConfigurer

import javax.sql.DataSource

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = ["pers.lyks.spring.**.mapper"])
class MybatisConfig : TransactionManagementConfigurer {

    @javax.annotation.Resource
    private val dataSource: DataSource? = null

    @Bean
    override fun annotationDrivenTransactionManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean(name = ["sqlSessionFactory"])
    fun createSqlSessionFactory(): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        if (null == dataSource) {
            throw NullPointerException("The dataSource must not be null")
        }
        bean.setDataSource(dataSource)
        // 配置类型别名
        bean.setTypeAliasesPackage("com.zsx.entity")


        try {
            // 配置类型别名
            bean.setTypeAliasesPackage("pers.lyks.spring.*.model")

            // 配置mapper的扫描，找到所有的mapper.xml映射文件
            val resources = PathMatchingResourcePatternResolver()
                    .getResources("classpath*:pers/lyks/spring/**/mapper/**Mapper.xml")
            bean.setMapperLocations(resources)
            return bean.getObject()
        } catch (e: Exception) {
            throw RuntimeException("Error getting the bean of 'sqlSessionFactory'", e)
        }

    }

    @Bean
    fun sqlSessionTemplate(sqlSessionFactory: SqlSessionFactory): SqlSession {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}
