package com.averyxu.DB;

import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.core.env.Environment;
        import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.transaction.PlatformTransactionManager;
        import org.springframework.transaction.annotation.EnableTransactionManagement;

        import javax.persistence.EntityManager;
        import javax.sql.DataSource;
        import java.util.HashMap;
        import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerPrimary",
        basePackages = {"com.averyxu.dao"})    // 指定该数据源操作的DAO接口包
public class ThirdlyConfig {

    @Autowired
    @Qualifier("thirdlyDataSource")
    private DataSource primaryDataSource;

    @Bean(name = "entityManagerThirdly")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryThirdly")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(getVendorProperties())
                .packages("com.averyxu.model")         //设置实体类所在位置
                .persistenceUnit("thirdlyPersistenceUnit")
                .build();
    }

    private Map getVendorProperties() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        properties.put("hibernate.ddl-auto",
                "create");
        properties.put("hibernate.physical_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
        properties.put("hibernate.implicit_naming_strategy",
                "org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy");
        return properties;
    }

    @Autowired
    private Environment env;

    @Bean(name = "transactionManagerThirdly")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}