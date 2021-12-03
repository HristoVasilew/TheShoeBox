package TheShoeBox.TheShoeBox.config;

import TheShoeBox.TheShoeBox.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

//    @Autowired
//    private MethodSecurityExpressionHandler methodSecurityExpressionHandler;
//
//    @Override
//    protected org.springframework.security.access.expression.method.MethodSecurityExpressionHandler createExpressionHandler() {
//        return methodSecurityExpressionHandler;
//    }
//
//    @Bean
//    public MethodSecurityExpressionHandler createExpressionHandler(ShoeService userService) {
//        return new TheShoeBox.TheShoeBox.config.MethodSecurityExpressionHandler(userService);
//    }
}
