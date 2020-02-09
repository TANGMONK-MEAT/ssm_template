package com.zwl.aop.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/6 17:19
 * 日志切面类
 */
@Aspect
@Component
public class LogUtils implements Ordered {
    @Override
    public int getOrder() {
        return 0;
    }
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 切入点表达式
     */
    @Pointcut("execution(public * com.zwl.aop.service.serviceImpl.MyMathCalculator.*(..))")
    private void pointCut(){}

    /**
     * 目标方法执行前运行
     * @param joinPoint
     */
    @Before("pointCut()")
    public static void logStart(JoinPoint joinPoint){
        System.out.println("【"+joinPoint.getSignature()+"】方法开始执行，用的参数列表【"+ Arrays.asList(joinPoint.getArgs())+"】");
    }

    /**
     * 目标方法正常结束，返回的时候运行
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "pointCut()",returning = "result")
    public static void logReturn(JoinPoint joinPoint,Object result){
        System.out.println("【"+joinPoint.getSignature()+"】方法正常执行完成，计算结果是："+result);
    }

    /**
     * 目标方法出现异常时，运行
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "execution(public * com.zwl.aop.service.serviceImpl.MyMathCalculator.*(..)))",throwing = "e")
    public static void logException(JoinPoint joinPoint, Exception e) {
        System.out.println("【"+joinPoint.getSignature()+"】方法执行出现异常了，异常信息是："+e.getCause()+"；这个异常已经通知测试小组进行排查");
    }

    /**
     * 目标方法最终结束时，运行
     * @param joinPoint
     */
    @After("pointCut()")
    public static void logEnd(JoinPoint joinPoint) {
        System.out.println("【"+joinPoint.getSignature()+"】方法最终结束了");
    }

    /**
     * 环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;//结果
        //前置通知
        log.info("【"+joinPoint.getSignature()+"】方法开始执行，用的参数列表【"+ Arrays.asList(joinPoint.getArgs())+"】");
        try{
            //执行目标方法
            result = joinPoint.proceed();
            //返回通知
            log.info("【"+joinPoint.getSignature()+"】方法正常执行完成，计算结果是："+result);
        } catch (Throwable throwable) {
            //异常通知
            log.info("【"+joinPoint.getSignature()+"】方法执行出现异常了，异常信息是："+throwable.getCause()+"；这个异常已经通知测试小组进行排查");
            throw throwable;//抛出异常
        }
        //后置通知(无论连接点是正常返回还是抛出异常，后置通知都会执行)
        log.info("【"+joinPoint.getSignature()+"】方法最终结束了");
        return result;
    }

}
