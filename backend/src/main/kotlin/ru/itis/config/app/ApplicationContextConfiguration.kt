package ru.itis.config.app

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.multipart.MultipartResolver
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.util.*
import javax.persistence.EntityManagerFactory


@EnableWebMvc
@EnableTransactionManagement
@Configuration
@ComponentScan("ru.itis")
@PropertySource("classpath:application.properties")
open class ApplicationContextConfiguration {

    @Bean
    open fun gson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
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
    open fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
        return mapper
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


    @Bean
    open fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    open fun multipartResolver() = CommonsMultipartResolver()
}