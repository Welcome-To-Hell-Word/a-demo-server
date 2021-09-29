package a.demo.server.aspect;

import a.demo.server.tools.TheResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TheAspect {
    @Pointcut("execution(public * a.demo.server.module.controller.*.*(..))")
    public void Pointcut(){}

    @Before(value = "Pointcut()")
    public void Before(JoinPoint joinPoint){
        System.err.println("Before");
        Signature signature=joinPoint.getSignature();
        System.out.println(signature);
        System.out.println(signature.getName());
        System.out.println(Arrays.asList(joinPoint.getArgs()));
        System.err.println("Before... ...");
    }
    @After(value = "Pointcut()")
    public void After(){
        System.err.println("After");
        System.err.println("After... ...");
    }
    @AfterReturning(value = "Pointcut()",returning = "o")
    public void AfterReturning(Object o){
        System.err.println("AfterReturning");
        System.out.println(o);
        System.err.println("AfterReturning... ...");
    }
    @AfterThrowing(value = "Pointcut()",throwing = "e")
    public void AfterThrowing(Exception e){
        System.err.println("AfterThrowing");
        System.out.println(e);
        System.err.println("AfterThrowing... ...");
    }
    @Around(value = "execution(public * a.demo.server.module.controller.*.*(..))")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint){
        System.err.println("Around");
        System.out.println(proceedingJoinPoint.getSignature().getName());
        try {
            Object[]args=proceedingJoinPoint.getArgs();
            if (args!=null&&args.length>1){
                for (int i=0;i<args.length;i++){
                    if (args[i] instanceof String&&args[i]==null){
                        args[i]="";
                    }
                }
                System.out.println(args[0]);
                Object o=proceedingJoinPoint.proceed(args);
                if (o!=null&&o instanceof TheResult)
                    return o;
                return "type exception";
            }
            Object o=proceedingJoinPoint.proceed();
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.err.println("Around... ...");
        }
        return null;
    }
}
