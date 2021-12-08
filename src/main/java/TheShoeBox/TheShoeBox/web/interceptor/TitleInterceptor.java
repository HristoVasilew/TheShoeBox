package TheShoeBox.TheShoeBox.web.interceptor;

import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TitleInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String title = "THE-ShoeBox";

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                PageTitle methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);

                if (methodAnnotation != null) {
                    modelAndView
                            .addObject("title", title + " | " + methodAnnotation.value());
                }
            }
        }
    }
}
