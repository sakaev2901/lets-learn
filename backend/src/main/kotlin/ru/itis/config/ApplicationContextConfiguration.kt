package ru.itis.config

import com.google.gson.Gson
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jca.support.LocalConnectionFactoryBean
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.*
import javax.persistence.EntityManagerFactory


//@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan("ru.itis")
open class ApplicationContextConfiguration {

    @Bean
    open fun gson(): Gson {
        return Gson()
    }

    @Bean
    open fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(corsRegistry: CorsRegistry) {
                corsRegistry.addMapping("/**")
            }
        }
    }

    @Bean
    open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL)
        val entityManagerFactory = LocalContainerEntityManagerFactoryBean()
        entityManagerFactory.dataSource = hikariDataSource()
        entityManagerFactory.setPackagesToScan("ru.itis")
        entityManagerFactory.jpaVendorAdapter = hibernateJpaVendorAdapter
        entityManagerFactory.setJpaProperties(additionalProperties())
        return entityManagerFactory
    }

    @Bean
    open fun transactionManager(entityManagerFactory: EntityManagerFactory) = JpaTransactionManager().apply { this.entityManagerFactory = entityManagerFactory }

    @Bean
    open fun hikariDataSource() = HikariDataSource(hikariConfig())

    @Bean
    open fun hikariConfig() = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/lets_learn"
        username = "postgres"
        password = "elvin2901"
        driverClassName = "org.postgresql.Driver"
    }

    @Bean
    open fun additionalProperties() = Properties()
            .apply { setProperty("hibernate.hbm2ddl.auto", "update") }
            .apply { setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect") }
            .apply { setProperty("hibernate.show_sql", "true") }

}