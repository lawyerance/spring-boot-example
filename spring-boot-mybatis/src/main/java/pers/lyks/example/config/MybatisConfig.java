package pers.lyks.example.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"pers.lyks.**.mapper"})
public class MybatisConfig implements TransactionManagementConfigurer {

    @javax.annotation.Resource
    private DataSource dataSource;

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        if (null == dataSource) {
            throw new NullPointerException("The dataSource must not be null");
        }
        bean.setDataSource(dataSource);
        // 配置类型别名
        bean.setTypeAliasesPackage("com.zsx.entity");


        try {
            // 配置类型别名
            bean.setTypeAliasesPackage("pers.lyks.*.model");

            // 配置mapper的扫描，找到所有的mapper.xml映射文件
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath*:pers/lyks/**/mapper/**Mapper.xml");
            bean.setMapperLocations(resources);
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("Error getting the bean of 'sqlSessionFactory'", e);
        }
    }

    @Bean
    public SqlSession sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
