package ru.itis.config.web

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import ru.itis.config.app.ApplicationContextConfiguration

class ApplicationWebInitializer: AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>>? {
        return null
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    override fun getServletConfigClasses(): Array<Class<*>>? {
        return arrayOf(ApplicationContextConfiguration::class.java)
    }
}