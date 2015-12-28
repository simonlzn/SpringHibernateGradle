package org.sphic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new GlobalCustomHandler());
  }
/*
  @Bean
  public FreeMarkerViewResolver freeMarkerViewResolver() {
      FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
      freeMarkerViewResolver.setCache(true);
      freeMarkerViewResolver.setPrefix("/template/");
      freeMarkerViewResolver.setSuffix(".ftl");
      freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
      freeMarkerViewResolver.setRequestContextAttribute("request");  
      freeMarkerViewResolver.setExposeRequestAttributes(true); 
      freeMarkerViewResolver.setExposeSpringMacroHelpers(true);
      freeMarkerViewResolver.setExposeSessionAttributes(true);
      
      System.out.println("freemarker");
      return freeMarkerViewResolver;
  }
*/
  /*
  @Bean
  public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
      FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
      factory.setTemplateLoaderPaths("classpath:templates", "src/main/resource/templates");
      factory.setDefaultEncoding("UTF-8");
      FreeMarkerConfigurer result = new FreeMarkerConfigurer();
      result.setConfiguration(factory.createConfiguration());
      return result;
  }
  */
  /*
  @Bean(name ="freemarkerConfig")	
  public FreeMarkerConfigurer freemarkerConfig() {
	  FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
	  configurer.setTemplateLoaderPath("src/main/resource/templates");
	  Map<String, Object> map = new HashMap<>();
	  map.put("xml_escape", new XmlEscape());
	  configurer.setFreemarkerVariables(map);
      return configurer;
  }
  
  @Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreUnknownPathExtensions(false).defaultContentType(MediaType.TEXT_HTML);
	}
    
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
  	   registry.freeMarker();
    }
 */     
  /*
  public FreeMarkerConfigurer freemarkerConfig() {
      FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
      //freeMarkerConfigurer.setTemplateLoaderPath("classpath:templates");
      
      //freeMarkerConfigurer.setTemplateLoaderPath("/template");
      
      
      Properties settings = new Properties();
      settings.setProperty("number_format", "0.######");
      freeMarkerConfigurer.setFreemarkerSettings(settings);
      Map variables = new java.util.HashMap<String, Object>();

      variables.put("xml_escape", new XmlEscape());
      freeMarkerConfigurer.setFreemarkerVariables(variables);
      
      return freeMarkerConfigurer;
  }
*/
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/pages/");
    resolver.setSuffix(".jsp");
    resolver.setSuffix(".html");
    
    System.out.println("viewResolver");

    
    return resolver;
  }
  
  
  

}