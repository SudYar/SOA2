package com.mastertheboss.jaxrs.backend.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Настройки {@link DataSource}, {@link EntityManagerFactory},
 * {@link org.springframework.transaction.TransactionManager} для общей БД.
 *
 * @author Anatoly Laptev (Anatoly.Laptev@lanit-tercom.ru) created on 01.12.2022.
 */
@Configuration
@EnableJpaRepositories(
		basePackages = "com.mastertheboss.jaxrs.backend.domain.repository"
)
public class PrimaryDSConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "userDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSource userDataSource(DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.jpa")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	/**
	 * Создаем {@link EntityManagerFactory} для основной БД.
	 *
	 * @param dataSource    основной датасоурс
	 * @param jpaProperties настройки энити менеджера
	 * @return созданная фабрика.
	 */
	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
																	   JpaProperties jpaProperties) {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		em.setDataSource(dataSource);
		em.setPackagesToScan("com.mastertheboss.jaxrs.backend.domain.entity");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties properties = new Properties();
		properties.putAll(jpaProperties.getProperties());
		em.setJpaProperties(properties);

		return em;
	}

	@Bean(name = "transactionManager")
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
