package com.luna.wednesday.tools;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableMap;

/**
 * @author Tony
 */
@Order(1)
@Aspect
@Component
public class ScheduledAspect {
    private final static Logger                    logger            = LoggerFactory.getLogger(ScheduledAspect.class);

    /** 忽略日志方法 */
    private final static Map<String, List<String>> IGNORE_LOG_METHOD = ImmutableMap.<String, List<String>>builder()
        .build();

    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void scheduled() {}

    @Around("scheduled()")
    public Object doScheduledFlowAround(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String method = methodSignature.getMethod().getName();

        boolean needLog = needLog(className, method);

        if (needLog) {
            logger.info("scheduled invoke, className={}, method={}", className, method);
        }
        try {
            Object proceed = pjp.proceed();
            if (needLog) {
                logger.info("scheduled invoke success, className={}, method={}, proceed={}", className, method,
                    proceed);
            }
            return proceed;
        } catch (Throwable t) {
            logger.error("scheduled invoke error, className={}, method={}", className, method, t);
            // 吃掉异常
            return null;
        }
    }

    private boolean needLog(String className, String method) {
        if (IGNORE_LOG_METHOD.containsKey(className) == false) {
            return true;
        }

        if (IGNORE_LOG_METHOD.get(className).contains(method)) {
            return false;
        }

        return true;
    }

}
