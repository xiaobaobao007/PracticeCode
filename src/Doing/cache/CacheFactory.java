package Doing.cache;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CacheFactory {

	//定义获取代理对象方法
	private Object getJDKProxy(Object target) {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
			System.out.println("JDK动态代理，监听开始！");
			System.out.println(Arrays.toString(method.getDeclaredAnnotations()));
			System.out.println(Arrays.deepToString(method.getParameterAnnotations()));
			Object result = method.invoke(target, args);
			System.out.println("JDK动态代理，监听结束！");
			return result;
		});
	}

	public static void main(String[] args) {
		CacheFactory jdkProxy = new CacheFactory();//实例化JDKProxy对象
		Cache user = (Cache) jdkProxy.getJDKProxy(new CacheDemo());//获取代理对象
		user.getOne();
	}

}