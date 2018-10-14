package com.macadamian.car.api.rest.aspect;

import com.macadamian.car.api.rest.exception.AccessToResourceRestrictedException;
import com.macadamian.car.facade.annotation.Token;
import com.macadamian.car.persistence.user.entity.User;
import com.macadamian.car.service.user.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

@Aspect
@Component
@Order(1)
public class TokenAnnotation extends RequestAspectBase {

    private static final String HEADER_LOGIN_KEY = "login";

    @Autowired
    private UserService userService;

    //for any method or class with @Token, no matter what the return type, name, or arguments are, call this method
    @Before(value = "@within(token)")
    public void tokenValidationForClasses(final JoinPoint joinPoint, final Token token) {
        if (permit(joinPoint)) {
            return;
        }
        checkTokenValidation(token, joinPoint);
    }

    @Before(value = "@annotation(token)")
    public void tokenValidationForMethods(final JoinPoint joinPoint, final Token token) {
        checkTokenValidation(token, joinPoint);
    }

    private boolean permit(final JoinPoint joinPoint) {
        return hasAnnotation(joinPoint, PermitAll.class);
    }

    private void checkTokenValidation(final Token token, final JoinPoint joinPoint) {
        final Object[] arguments = joinPoint.getArgs();
        final String resource = ((Path) getClassAnnotation(joinPoint, Path.class)).value().replaceFirst("/", "");

        final HttpServletRequest context = getRequestContext(arguments);
        final String login = getHeaderParamByKey(HEADER_LOGIN_KEY, context);
        final String userRole = token.userRole();

        final User user = userService.getByLogin(login);
        final boolean iswAllowed = userRole.equals(user.getRole().name());
        if (!iswAllowed) {
            throw new AccessToResourceRestrictedException(this, "Unauthorized User for the  resource - %s, login - %s", resource, login);
        }
    }
}