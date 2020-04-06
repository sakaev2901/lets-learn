package ru.itis.config

import com.google.gson.Gson
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@EnableWebMvc
@Configuration
@ComponentScan("ru.itis")
open class ApplicationContextConfiguration {

    @Bean
    open fun gson():Gson {
        return Gson()
    }


}