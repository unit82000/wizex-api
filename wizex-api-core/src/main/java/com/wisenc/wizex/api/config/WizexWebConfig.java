package com.wisenc.wizex.api.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc
@Configuration
public class WizexWebConfig implements WebMvcConfigurer{

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
	}

	/**
	 * CORS 자원공유  url지정
	 * .allowedMethods() //허용 method 지정
	 * .maxAge(시간)  pre-flight리퀘스트 캐싱 
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		Logger LOGGER  = LoggerFactory.getLogger(WizexWebConfig.class);
		LOGGER.debug("WizexWebConfig addCorsMappings");
		registry.addMapping("/**").allowedOrigins("*")
								  .maxAge(3600); //1시간
		
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		
	}

	@Override
	public Validator getValidator() {
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		return null;
	}

}
