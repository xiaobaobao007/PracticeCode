package DesignModel.ProxyPattern.CglibProxy;

import DesignModel.ProxyPattern.JdkDynamicProxy.StarProxy;
import DesignModel.ProxyPattern.LiuDeHua;
import DesignModel.ProxyPattern.Star;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Code Generator Library
 */
public class CglibProxy implements MethodInterceptor {
	// 根据一个类型产生代理类，此方法不要求一定放在MethodInterceptor中
	public Object CreatProxyedObj(Class<?> clazz) {

		Enhancer enhancer = new Enhancer();

		enhancer.setSuperclass(clazz);

		enhancer.setCallback(this);

		return enhancer.create();
	}

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		// 这里增强
		System.out.println("收钱");

		return arg3.invokeSuper(arg0, arg2);
	}


	//spring默认使用jdk动态代理，如果类没有接口，则使用cglib。
	public static void main(String[] args) {
		int times = 10000;

		Star ldh = new LiuDeHua();
		StarProxy proxy = new StarProxy();
		proxy.setTarget(ldh);

		long time1 = System.currentTimeMillis();
		Star star = (Star) proxy.CreatProxyedObj();
		long time2 = System.currentTimeMillis();
		System.out.println("jdk创建时间：" + (time2 - time1));

		CglibProxy proxy2 = new CglibProxy();
		long time5 = System.currentTimeMillis();
		Star star2 = (Star) proxy2.CreatProxyedObj(LiuDeHua.class);
		long time6 = System.currentTimeMillis();
		System.out.println("cglib创建时间：" + (time6 - time5));

		long time3 = System.currentTimeMillis();
		for (int i = 1; i <= times; i++) {
			star.sing("ss");

			star.dance("ss");
		}
		long time4 = System.currentTimeMillis();
		System.out.println("jdk执行时间" + (time4 - time3));

		long time7 = System.currentTimeMillis();
		for (int i = 1; i <= times; i++) {
			star2.sing("ss");

			star2.dance("ss");
		}

		long time8 = System.currentTimeMillis();

		System.out.println("cglib执行时间" + (time8 - time7));
	}
}