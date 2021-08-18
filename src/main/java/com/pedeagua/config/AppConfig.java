package com.pedeagua.config;  
  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
  
@Configuration 
@ComponentScan("com.pedeagua") 
@Import(DBConfig.class)
@EnableWebMvc   
public class AppConfig extends WebMvcConfigurerAdapter  {  
	@Bean
	public VelocityConfigurer velocityConfig() {
	    VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
	    velocityConfigurer.setResourceLoaderPath("/WEB-INF/");
	    return velocityConfigurer;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		VelocityViewResolver viewResolver = new VelocityViewResolver();
		
		viewResolver.setViewClass(VelocityView.class);
		viewResolver.setCache(true);
		viewResolver.setPrefix("");
		viewResolver.setSuffix(".html");
		viewResolver.setExposeSpringMacroHelpers(true);

		registry.viewResolver(viewResolver);
	}
	
//    @Bean  
//    public InternalResourceViewResolver viewResolver() {  
//	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();  
//        resolver.setPrefix("/WEB-INF/view/");  
//        resolver.setSuffix(".jsp");
//        return resolver;  
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }    
}  
