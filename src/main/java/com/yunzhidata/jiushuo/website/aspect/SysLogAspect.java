package com.yunzhidata.jiushuo.website.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@Aspect
public class SysLogAspect {
    //@Pointcut("execution(public int com.jztey.omronhealth.service.ArithmeticCalculator.*(..))")
    //  public void declareJointPointExpression() {
    //  }
    @Pointcut("@annotation(com.yunzhidata.jiushuo.website.aspect.AsAnnotation)")
    public void annotationPointCut() {
    }
//
//    @Before("annotationPointCut()")
//    public void beforeThine(JoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//        MethodSignature signature =(MethodSignature)joinPoint.getSignature();
//        Method method = signature.getMethod();
//        AsAnnotation asAnnotation=method.getAnnotation(AsAnnotation.class);
//        int switchInt=-1;
//        if((switchInt=asAnnotation.num().getValue())==AsEnum.ONE.getValue()){
//            System.out.println("前置通知SysLogAspect---1:"+asAnnotation.num().getName());
//        }else if(switchInt==AsEnum.TWO.getValue()){
//            System.out.println("前置通知SysLogAspect---2:"+asAnnotation.num().getName());
//        }

//        Object[] args = joinPoint.getArgs();
//        System.out.println("前置通知:这是切面开始打印出来的--->The method " + methodName + " begins with " + Arrays.asList(args));
  //  }
//    @After("annotationPointCut()")
//    public void afterMethod(JoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//        System.out.println("后置通知:这是切面结束打印出来的--->The method " + methodName + " ends");
//    }

    //环绕通知
    @Around("annotationPointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("before----------------");
        //前置通知
        Object obj = proceedingJoinPoint.proceed();

        System.out.println("end----------------");
        //后置通知
        return obj;
    }

//    MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//    Method method = signature.getMethod();
//    AutoCompleteSchedule annotation = method.getAnnotation(AutoCompleteSchedule.class);
//    Long targetId = findTargetId(proceedingJoinPoint.getArgs(), annotation);
//    Object obj = proceedingJoinPoint.proceed();
//    completeSchedule(annotation, targetId);
//        return obj;
//    }



}
