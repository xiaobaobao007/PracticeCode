package per.bmy.DesignModel.ProxyPattern.CglibProxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import per.bmy.DesignModel.ProxyPattern.LiuDeHua;
import per.bmy.DesignModel.ProxyPattern.Star;

/**
 * <dependency>
 * <groupId>cglib</groupId>
 * <artifactId>cglib</artifactId>
 * <version>2.2.2</version>
 * </dependency>
 */
public class CglibProxy implements MethodInterceptor {

    public Object CreatProxyedObj(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object target, Method method, Object[] param, MethodProxy methodProxy) throws Throwable {
        System.out.println("收钱");
        return methodProxy.invokeSuper(target, param);
    }

    public static void main(String[] args) {

        CglibProxy proxy2 = new CglibProxy();
        Star star2 = (Star) proxy2.CreatProxyedObj(LiuDeHua.class);
        star2.sing("ss");
        star2.dance("ss");

//		int times = 10000;
//
//		Star ldh = new LiuDeHua();
//		StarProxy proxy = new StarProxy();
//		proxy.setTarget(ldh);
//
//		long time1 = System.currentTimeMillis();
//		Star star = (Star) proxy.CreatProxyedObj();
//		long time2 = System.currentTimeMillis();
//		System.out.println("jdk创建时间：" + (time2 - time1));
//
//		CglibProxy proxy2 = new CglibProxy();
//		long time5 = System.currentTimeMillis();
//		Star star2 = (Star) proxy2.CreatProxyedObj(LiuDeHua.class);
//		long time6 = System.currentTimeMillis();
//		System.out.println("cglib创建时间：" + (time6 - time5));
//
//		long time3 = System.currentTimeMillis();
//		for (int i = 1; i <= times; i++) {
//			star.sing("ss");
//
//			star.dance("ss");
//		}
//		long time4 = System.currentTimeMillis();
//		System.out.println("jdk执行时间" + (time4 - time3));
//
//		long time7 = System.currentTimeMillis();
//		for (int i = 1; i <= times; i++) {
//			star2.sing("ss");
//
//			star2.dance("ss");
//		}
//
//		long time8 = System.currentTimeMillis();
//
//		System.out.println("cglib执行时间" + (time8 - time7));
    }
}