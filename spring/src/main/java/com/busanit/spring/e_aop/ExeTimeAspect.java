package com.busanit.spring.e_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// 공통기능(시간계산)을 제공하는 횡단 관심사
@Aspect
public class ExeTimeAspect {
    // PointCut : 적용 가능 지점
    @Pointcut("execution(public * com.busanit.spring.e_aop..*(..))")
    private void publicTarget(){
    }
    // Advice의 범위 Around : joinPoint의 전과 후에 수행
    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.nanoTime();
        try {
        Object result = joinPoint.proceed();

        return result;
        } finally {
            long end = System.nanoTime();
            // 시그니처 : 메서드 이름, 파라미터
            Signature signature = joinPoint.getSignature();

            // 시그니처에서 이름확인
            // 실행 인자
            System.out.println(signature.getName()
                            + joinPoint.getArgs().toString()
                            + "실행시간 : "
                            + (end-start));
        }


    }

}
