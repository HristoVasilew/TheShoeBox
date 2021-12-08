package TheShoeBox.TheShoeBox.config;

import TheShoeBox.TheShoeBox.web.interceptor.StatsInterceptor;
import TheShoeBox.TheShoeBox.web.interceptor.TitleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;
    private final TitleInterceptor titleInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor, TitleInterceptor titleInterceptor) {
        this.statsInterceptor = statsInterceptor;
        this.titleInterceptor = titleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.statsInterceptor);
        registry.addInterceptor(this.titleInterceptor);
    }
}
