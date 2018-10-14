package com.macadamian.car.api.rest.aspect;

import com.macadamian.car.api.rest.exception.ApiRuntimeException;
import com.macadamian.car.service.common.exception.LoggerAwareServiceRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Optional;

class RequestAspectBase {

    HttpServletRequest getRequestContext(final Object[] arguments) {
        for (int i = arguments.length - 1; i >= 0; i--) {
            if (arguments[i] instanceof HttpServletRequest) {
                return (HttpServletRequest) arguments[i];
            }
        }
        throw new LoggerAwareServiceRuntimeException(this, "HttpServletRequest context was not found.");
    }

    String getHeaderParamByKey(final String key, final HttpServletRequest request) {
        final String headerParam = request.getHeader(key);
        if (StringUtils.isEmpty(headerParam)) {
            throw new LoggerAwareServiceRuntimeException(this, "No Header param is specified for key - %s ", key);
        }
        return headerParam;
    }

    boolean hasAnnotation(final JoinPoint joinPoint, final Class<?> classType) {
        final Annotation[] annotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations();
        return Arrays.stream(annotations).anyMatch(a -> a.annotationType().equals(classType));
    }

    Annotation getMethodAnnotation(final JoinPoint joinPoint, final Class<?> classType) {
        final Annotation[] annotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations();
        final Optional<Annotation> annotationOption = Arrays.stream(annotations).filter(f -> f.annotationType().equals(classType)).findFirst();
        if (!annotationOption.isPresent()) {
            throw new ApiRuntimeException("The following type of Annotation not found in the method annotations list, -> " + classType.getName());
        }
        return annotationOption.get();
    }

    Annotation getClassAnnotation(final JoinPoint joinPoint, final Class<?> classType) {
        final Annotation[] annotations = ((MethodSignature) joinPoint.getSignature()).getDeclaringType().getAnnotations();
        final Optional<Annotation> annotationOption = Arrays.stream(annotations).filter(f -> f.annotationType().equals(classType)).findFirst();
        if (!annotationOption.isPresent()) {
            throw new ApiRuntimeException("The following type of Annotation not found in the class annotations list, -> " + classType.getName());
        }
        return annotationOption.get();
    }

    String getMethodName(final JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
    }
}
