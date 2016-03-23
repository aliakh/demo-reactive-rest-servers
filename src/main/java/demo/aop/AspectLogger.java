package demo.aop;

import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class AspectLogger {

    private static final Logger LOG = LoggerFactory.getLogger(AspectLogger.class);

    @Around("execution(* demo..*Controller.*(..)) || execution(* demo..*Service+.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final String method = joinPoint.getSignature().getDeclaringTypeName() + " " + joinPoint.getSignature().getName() + " " + Arrays.toString(joinPoint.getArgs());
        final Stopwatch stopwatch = Stopwatch.createStarted();

        LOG.debug("Before: " + method);

        Object result = joinPoint.proceed();

        LOG.debug("After:  " + method + " in " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
        return result;
    }

}
