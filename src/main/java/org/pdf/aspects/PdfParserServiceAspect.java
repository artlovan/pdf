package org.pdf.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;


@Aspect
@Component
public class PdfParserServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(PdfParserServiceAspect.class);

    @Pointcut("execution(* org.pdf.services.PdfParserService.*(..))")
    private void pointcutAdvice() {}

    @Around("pointcutAdvice()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        logger.info(">>> com.dt.pdf.services.PdfParserServiceImpl.mapPageContext");
        Instant start = Instant.now();
        Object proceed = jp.proceed();
        String benchmark = Duration.between(start, Instant.now()).toMillis() + " millis";
        logger.info("<<< com.dt.pdf.services.PdfParserServiceImpl.mapPageContext BENCHMARK => " + benchmark);

        return proceed;
    }
}
