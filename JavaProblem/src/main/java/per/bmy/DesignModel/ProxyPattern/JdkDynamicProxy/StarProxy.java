package per.bmy.DesignModel.ProxyPattern.JdkDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import per.bmy.DesignModel.ProxyPattern.LiuDeHua;
import per.bmy.DesignModel.ProxyPattern.Star;

/**
 * jdk动态代理
 *
 * @author xiaobaobao
 * @date 2020/5/13，17:09
 */
public class StarProxy implements InvocationHandler {

    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("收钱");
        return method.invoke(target, args);
    }

    // 生成代理类
    public Object CreatProxyedObj() {
        //ClassLoader，Class<?>[]，InvocationHandler
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        Star ldh = new LiuDeHua();
        StarProxy proxy = new StarProxy();
        proxy.setTarget(ldh);

        Object obj = proxy.CreatProxyedObj();
        Star star = (Star) obj;
        star.sing("1");
        star.dance("1");
    }

}