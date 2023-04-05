package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	@Around("execution(* org.zerock.*.service.*ServiceImpl.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info("\n+==========================================================================================");
		
		// 실행되는 클래스 
		log.info(" *AOP 실행 객체 :" + pjp.getTarget());
		log.info(" *AOP 실행 메소드 :" + pjp.getSignature());
		
		// 넘어가는 데이터 
		log.info(" *AOP 전달 데이터:" + Arrays.toString(pjp.getArgs()));
		
		// 처리 내용 
		// 처리 결과 저장 객체
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		log.info(" *AOP 처리 결과 데이터  : " + result);
		
		long end = System.currentTimeMillis();
		
		log.info("AOP Time : " + (end - start));
		
		log.info("\n+==========================================================================================");
		
		return result;
	}
	
}
