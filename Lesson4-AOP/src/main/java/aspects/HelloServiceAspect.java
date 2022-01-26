package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloServiceAspect {

    // The 4 simple join points and the complex 1...which probably shouldn't be used

//    @Before("execution(* services.HelloService.hello(..) )")
    public void before() {
        System.out.println("A");
    }

//    @After("execution(* services.HelloService.hello(..) )")
    public void after() {
        System.out.println("B");
    }

//    @AfterReturning("execution(* services.HelloService.hello(..) )")
    public void afterReturning() {
        System.out.println("C");
    }

//    @AfterThrowing("execution(* services.HelloService.hello(..) )")
    public void afterThrowing() {
        System.out.println("X");
    }

//    @Around("execution(* services.HelloService.hello(..) )")
    public Object around(ProceedingJoinPoint joinPoint) {
        // Must return object b/c you don't know class that is being intercepted
        System.out.println("First Thing.");
        // Since join point parameter is not used Hello method does not execute
        return "Second thing";
    }

    @Around("execution(* services.HelloService.hello(..) )")
    public Object around2(ProceedingJoinPoint joinPoint) {
        System.out.println("First Thing.");
        String result;
        try {
            String arg =  (String) joinPoint.getArgs()[0]; // intercept args
            String revArg = new StringBuilder(arg).reverse() // reverse name
                                                  .toString();
            Object obj = joinPoint.proceed(new Object[] {revArg}); // pass reversed name to Hello
            if (!(obj instanceof String) ) {
                throw new IllegalArgumentException("Did not receive a string from Hello");
            }
            String txt = (String) obj;
            result = new StringBuilder(txt).reverse()  // reverse output of hello
                                           .toString();
        } catch (Throwable t) {
            throw new RuntimeException("Caught an error from hello", t);
        }
        return result;
    }

}
