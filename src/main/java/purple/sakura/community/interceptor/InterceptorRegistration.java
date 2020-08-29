package purple.sakura.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import purple.sakura.community.interceptor.customization.SessionInterceptor;


/* 拦截器统一配置添加, 单独配置的拦截只有一个第一个生效果 */
@Configuration
public class InterceptorRegistration extends WebMvcConfigurationSupport {
    @Autowired
    SessionInterceptor sessionInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( sessionInterceptor ).addPathPatterns("/**");

    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

}
