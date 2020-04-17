package io.github.ttallaemideul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import io.github.ttallaemideul.sample.thymeleaf.HelloDialect;
import io.github.ttallaemideul.thymeleaf.TlmdDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
		registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	/**
	 * 타임리프 속성 생성
	 * @return
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix("classpath:templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
		// TODO: Template cache is true by default. Set to false if you want
		// templates to be automatically updated when modified.
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	/**
	 * 타임리프 dialect 추가
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setEnableSpringELCompiler(true); // Compiled SpringEL should speed up executions
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(layoutDialect());	// 레이아웃 관리
		templateEngine.addDialect(springSecurityDialect());	// 스프링 시큐리티 dialect
		templateEngine.addDialect(new HelloDialect()); // 샘플 dialect
		templateEngine.addDialect(new TlmdDialect()); // tlmd dialect
		return templateEngine;
	}
	
	/**
	 * 레이아웃 템플릿 처리 dialect (nz.net.ultraq.thymeleaf)
	 * @return
	 */
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}
	
	/**
	 * spring-security thymeleaf dialect
	 * @return
	 */
	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(0);
		return viewResolver;
	}
}
