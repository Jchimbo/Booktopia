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
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import static site.jeremichimbo.api.TomcatDatabaseConfig.getLocalContainerEntityManagerFactoryBean;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "site.jeremichimbo.model.openlib",
        entityManagerFactoryRef = "bookEntityManager",
        transactionManagerRef = "bookTransactionManager")
public class OpenLibDatabaseConfig {
    @Autowired
    private Environment env;
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.openlib-datasource")
    public DataSource bookDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean bookEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(bookDataSource());
        em.setPackagesToScan(new String[] { "site.jeremichimbo.api.openlib" });
        return getLocalContainerEntityManagerFactoryBean(em, env);
    }
    @Primary
    @Bean
    public PlatformTransactionManager bookTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(bookEntityManager().getObject());
        return transactionManager;
    }

}
