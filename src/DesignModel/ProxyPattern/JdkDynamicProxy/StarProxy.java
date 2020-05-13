package DesignModel.ProxyPattern.JdkDynamicProxy;

import DesignModel.ProxyPattern.LiuDeHua;
import DesignModel.ProxyPattern.Star;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author xiaobaobao
 * @date 2020/5/13，17:09
 */
public class StarProxy implements InvocationHandler {
	// 目标类，也就是被代理对象
	private Object target;

	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 这里可以做增强
		System.out.println("收钱");

		return method.invoke(target, args);
	}

	// 生成代理类
	public Object CreatProxyedObj() {
		//ClassLoader，Class<?>[]，InvocationHandler
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	public static void main(String[] args) {
		//实现对象继承自接口
		Star ldh = new LiuDeHua();
		//建立动态代理工厂
		StarProxy proxy = new StarProxy();
		//在工厂里设置需要代理的对象
		proxy.setTarget(ldh);
		//生成增强后的对象
		Object obj = proxy.CreatProxyedObj();
		//强转为需要的对象类型
		Star star = (Star) obj;
		//使用增强后的代理类方法
		star.sing("1");
		star.dance("1");
	}

}