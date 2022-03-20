package com.twocow.song.configuration.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeTraceAop {

	/**
	 * 모든 Class에 적용되는 공통 로직 
	 * 시작시간 , 종료시간 출력
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.twocow.song..*(..))")
	public Object executeTimeTrace(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		log.info("START : " + joinPoint.toString());
		try {
			Object result = joinPoint.proceed();
			return result;
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			log.info("END : " + joinPoint.toString() + " " + timeMs + "ms" );
		}
	}

}
