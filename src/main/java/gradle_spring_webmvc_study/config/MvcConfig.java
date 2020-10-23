package gradle_spring_webmvc_study.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import gradle_spring_webmvc_study.interceptor.AuthCheckInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{

	// DispatcherServlet의매핑경로를'/'주었을때, JSP/HTML/CSS 등을올바르게처리하기위한설정추가
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// JSP를 통해서 컨트롤러의 실행결과를 보여주기 위한 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main");
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("message.label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor())
			.addPathPatterns("/edit/**")
			.excludePathPatterns("/edit/help/**");
	}
	
	
}