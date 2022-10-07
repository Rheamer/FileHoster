package Rheamer.Storage.config;

import Rheamer.Storage.models.RequestInfo;
import Rheamer.Storage.services.LoggerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@NoArgsConstructor
@AllArgsConstructor
public class LogInterceptor implements HandlerInterceptor {
    @Autowired
    private LoggerService loggerService;

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            @Nullable Exception ex) {
        if (ex != null){
            ex.printStackTrace();
        }
        loggerService.saveRequest(RequestInfo.of(request));
    }
}
