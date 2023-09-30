package site.jeremichimbo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "site.jeremichimbo.model.tomcat",
        entityManagerFactoryRef = "TomcatEntityManager",
        transactionManagerRef = "TomcatTransactionManager")
public class TomcatDatabaseConfig {
    @Autowired
    private Environment env;
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource TomcatDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean TomcatEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(TomcatDataSource());
        em.setPackagesToScan(new String[] { "site.jeremichimbo.api.tomcat" });

        return getLocalContainerEntityManagerFactoryBean(em, env);
    }

    static LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(LocalContainerEntityManagerFactoryBean em, Environment env) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager TomcatTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(TomcatEntityManager().getObject());
        return transactionManager;
    }

}
