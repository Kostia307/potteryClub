package com.example.potteryclub.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.openapitools.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class StatsAspect {

    private final StatsHolder statsHolder;

    public StatsAspect(StatsHolder statsHolder) {
        this.statsHolder = statsHolder;
    }

    // pointcut 1: any method in any controller
    @Pointcut("within(com.example.potteryclub.api..*)")
    public void anyControllerMethod() {}

    // pointcut 2: getPosts method specifically
    @Pointcut("execution(* com.example.potteryclub.service.PostService.getPosts(..))")
    public void getPostsMethod() {}

    // before any controller method, record the endpoint
    @Before("anyControllerMethod()")
    public void recordEndpointCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName()
                .replace("com.example.potteryclub.api.", "")
                + "." + joinPoint.getSignature().getName();
        statsHolder.recordEndpointCall(methodName);
    }

    // after getPosts returns, inspect the list
    @AfterReturning(pointcut = "getPostsMethod()", returning = "result")
    public void inspectPostResult(JoinPoint joinPoint, Object result) {
        if (result instanceof List<?> posts) {
            for (Object item : posts) {
                if (item instanceof Post post) {
                    int len = post.getContent() != null ? post.getContent().length() : 0;
                    statsHolder.updatePostStats(len, post.getTitle(), 1);
                }
            }
        }
    }
}
