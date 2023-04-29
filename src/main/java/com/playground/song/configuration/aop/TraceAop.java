package com.playground.song.configuration.aop;

import com.playground.song.configuration.GlobalConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP - 시작, 종료 공통 로직
 * 참고 URL) https://twocowsong.tistory.com/54
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TraceAop {

	private GlobalConfig globalConfig;

	/**
	 * 모든 패키지에 적용되는 시작, 종료 공통 로직
	 * 시작시간 , 종료시간 출력
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.playground.song..*(..))")
	public Object executeTimeTrace(ProceedingJoinPoint joinPoint) throws Throwable {
		// 시작 전 사용될 내용
		// long start = System.currentTimeMillis();
		// log.info("START : " + joinPoint.toString());
		try {
			// 본문 내용 시작
			Object result = joinPoint.proceed();
			return result;
		} finally {
			// 종료 후 사용될 내용
			// long finish = System.currentTimeMillis();
			// long timeMs = finish - start;
			// log.info("END : " + joinPoint.toString() + " " + timeMs + "ms" );
		}
	}

	/**
	 * Sql mapper xml 시작 전 찾아가기 쉽게 하기위한 파일명.메소드명 로그 출력
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.playground.song.mvc.admin.repository..*(..))")
	public Object queryExecute(ProceedingJoinPoint joinPoint) throws Throwable {
		if (globalConfig.isLocal()) {
			log.info(joinPoint.toString());
		}
		Object result = joinPoint.proceed();
		return result;
	}

}
