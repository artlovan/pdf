package org.pdf.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;


@Aspect
@Component
public class PdfTemplateCoordinatesAspect {
    private static final Logger logger = LoggerFactory.getLogger(PdfTemplateCoordinatesAspect.class);

    @Pointcut("execution(* org.pdf.core.PdfTemplateCoordinates.mapFields(..))")
    private void pointcutAdvice() {}

    @Around("pointcutAdvice()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        logger.info(">>> com.dt.pdf.core.PdfTemplateCoordinates.mapFields");
        Instant start = Instant.now();
        Object proceed = jp.proceed();
        String benchmark = Duration.between(start, Instant.now()).toMillis() + " millis";
        logger.info("<<< com.dt.pdf.core.PdfTemplateCoordinates.mapFields BENCHMARK => " + benchmark);

        return proceed;
    }
}
