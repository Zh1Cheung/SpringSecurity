/**
 * 
 */
package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Date;

/**
 * 使用AOP进行拦截
 *
 * @author : Zh1Cheung 945503088@qq.com
 * @date : 2018/8/13 2:22
 */


/**
 *
 * 如果我们自定的Interceptor的preHandler方法返回的是false，分发任务就会截止，不再继续执行下面的代码，
 * 而下面的一行代码正是将前端携带的参数进行映射的逻辑，也就是说，preHandler方法不会接触到前端携带来的参数，
 * 也就是说拦截器无法处理参数。所以这里引进AOP进行拦截。
 */


/**
 * AOP的核心概念解释：
 * 描述AOP常用的一些术语有通知(Adivce)、切点（Pointcut）、连接点（Join point）、切面（Aspect）、引入（Introduction）、织入（Weaving）
 * 通知（Advice）
 * 通知分为五中类型：
 * Before：在方法被调用之前调用
 * After：在方法完成后调用通知，无论方法是否执行成功
 * After-returning：在方法成功执行之后调用通知
 * After-throwing：在方法抛出异常后调用通知
 * Around：通知了好、包含了被通知的方法，在被通知的方法调用之前后调用之后执行自定义的行为
 *
 * -连接点（Join point）
 * 连接点是一个应用执行过程中能够插入一个切面的点。比如：方法调用、方法执行、字段设置/获取、异常处理执行、类初始化、甚至是for循环中的某个点。理论上, 程序执行过程中的任何时点都可以作为作为织入点, 而所有这些执行时点都是Joint point，但 Spring AOP 目前仅支持方法执行 (method execution)。
 *
 * 切点（Pointcut）
 * 通知（advice）定义了切面何时，那么切点就是定义切面“何处” 描述某一类 Joint points, 比如定义了很多 Joint point,
 * 对于 Spring AOP 来说就是匹配哪些方法的执行。
 *
 * 切面（Aspect）
 * 切面是切点和通知的结合。通知和切点共同定义了关于切面的全部内容，它是什么时候，在何时和何处完成功能。
 *
 * 引入（Introduction）
 * 引用允许我们向现有的类添加新的方法或者属性
 *
 * 织入（Weaving）
 * 组装方面来创建一个被通知对象。这可以在编译时完成（例如使用AspectJ编译器），也可以在运行时完成。
 * Spring和其他纯Java AOP框架一样，在运行时完成织入。
 *
 * 上面的概念有点生涩难懂，总结一个核心内容：切面 = 切点 + 通知。
 *
 *
 *
 */
















//有拦截器Interceptor对API进行拦截的时候是有缺陷的，因为无法获取前端访问API的时候所携带的参数的
//@Aspect
//@Component

/**
 *@param  @Around定义了环绕通知，也就是定义了何时使用切面，
 * 表达式"execution(* com.lemon.security.web.controller.UserController.*(..))"定义了再哪里使用。
 *
 *
 * ProceedingJoinPoint对象的proceed()方法表示执行被拦截的方法，
 * 它有一个Object类型的返回值，是原有方法的返回值，后期使用的时候往往需要强转。
 *
 *
 */
public class TimeAspect {
	
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is "+arg);
		}
		
		long start = new Date().getTime();
		
		Object object = pjp.proceed();
		
		System.out.println("time aspect 耗时:"+ (new Date().getTime() - start));
		
		System.out.println("time aspect end");
		
		return object;
	}

}

