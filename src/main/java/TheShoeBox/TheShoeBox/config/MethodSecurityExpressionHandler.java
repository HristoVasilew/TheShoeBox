package TheShoeBox.TheShoeBox.config;

import TheShoeBox.TheShoeBox.service.ShoeService;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;


public class MethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final ShoeService shoeService;

    public MethodSecurityExpressionHandler(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        OwnerSecurityExpressionRoot root = new OwnerSecurityExpressionRoot(authentication);

        root.setShoeService(shoeService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(new AuthenticationTrustResolverImpl());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}