package cn.zjy.dayong.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/5/28
 * Time:10:27
 *
 * 切面类.里面负责处理一些其他类的方法调用前后要做的事
 */
@Aspect //自定义切面
@Component
public class AspectDome {

    /**
     * 日志输出
     */
    Logger logger = LoggerFactory.getLogger(AspectDome.class);

    //@Before 是在所拦截方法执行之前执行一段逻辑。
//    @Before("execution(* cn.zjy.dayong.demo.aop.AopService.*(..))")
//    public void before(JoinPoint joinPoint){
//        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
//        Method method = methodSignature.getMethod();
//        System.out.println("想" + method.getName() + "身上有钱没?");
//        System.out.println("找神仙借点钱!");
//    }

    //@Around是可以同时在所拦截方法的前后执行一段逻辑。
//    @Around("execution(* cn.zjy.dayong.demo.aop.AopService.*(..))")
//    public void around(ProceedingJoinPoint point){
//        System.out.println("3D立体环绕1");
//        try {
//            point.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        System.out.println("3D立体环绕2");
//    }

    /**
     * 切入点:哪个类要用切面
     */
    @Pointcut("execution(* cn.zjy.dayong.demo.aop.AopService.*(..))")
    public void webLog(){

    }

    /**
     * 环绕通知
     * @param point
     * @return
     */
    @Around(value = "webLog()" )
    public Object arround(ProceedingJoinPoint point){
        logger.info("1、Around：方法环绕开始.....");
        try {
            Object object = point.proceed();
            logger.info("3、Around：方法环绕结束，结果是 :" + object);  //object就是切面方法的返回值
            return object;
        } catch (Throwable e) {
            logger.error(point.getSignature() + " 出现异常： ", e);
            return null;
        }
    }

    @Before(value = "webLog()")
    public void before(JoinPoint point){
        logger.info("2、Before：方法执行开始...");
        ServletRequestAttributes attributes =  (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL:{}", request.getRequestURL().toString());  //http://localhost:2080/aop/eat
        logger.info("HTTP_METHOD: {}", request.getMethod());    //GET
        logger.info("IP: {}", request.getRemoteAddr());     //0:0:0:0:0:0:0:1
        logger.info("CLASS_METHOD: {}", point.getSignature().getDeclaringTypeName()
                      + point.getSignature().getName());    //cn.zjy.dayong.demo.aop.AopServiceeat
        logger.info("ARGS: {}", Arrays.toString(point.getArgs()));
    }

    /**
     * 方法结束后, 不管是抛出异常或者正常退出都会执行
     */
    @After(value = "webLog()")
    public void after(){
        logger.info("4、After：方法最后执行.....");
    }

    /**
     * 方法执行结束，增强处理
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.info("5、AfterReturning：方法的返回值 : " + ret);
    }
}
