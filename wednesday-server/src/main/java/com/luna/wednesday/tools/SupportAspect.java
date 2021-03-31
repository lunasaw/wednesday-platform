package com.luna.wednesday.tools;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

/**
 * @Description support层切片
 * @author wenyuan.ww
 * @date 2019年10月16日 下午7:39:43
 */
@Order(1)
@Aspect
@Component
public class SupportAspect {
    private final static Logger                    logger            = LoggerFactory.getLogger(SupportAspect.class);

    /** 忽略日志方法 */
    private final static Map<String, List<String>> IGNORE_LOG_METHOD = ImmutableMap.<String, List<String>>builder()
        .put("UserSupport", ImmutableList.of("getUserIdBySessionKey"))
        .build();

    @Pointcut("execution(public * com.iteknical.wednesday.support.*.*(..)) || execution(public * com.iteknical.wednesday.wrapper.*.*(..))")
    public void support() {}

    @Before("support()")
    public void doSupportBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String method = methodSignature.getMethod().getName();

        if (needLog(className, method) == false) {
            return;
        }

        Object[] args = joinPoint.getArgs();

        logger.info("support invoke, className={}, method={}, param={}", className, method, JSON.toJSONString(args));
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
