package TheShoeBox.TheShoeBox.config;
//
//import TheShoeBox.TheShoeBox.service.ShoeService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = false)
//public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

//    private MethodSecurityExpressionHandler methodSecurityExpressionHandler;
//
//    @Override
//    protected org.springframework.security.access.expression.method.MethodSecurityExpressionHandler createExpressionHandler() {
//        return methodSecurityExpressionHandler;
//    }

//    @Bean
//    public MethodSecurityExpressionHandler createExpressionHandler(ShoeService userService) {
//        return new MethodSecurityExpressionHandler(userService);
//    }
//}
