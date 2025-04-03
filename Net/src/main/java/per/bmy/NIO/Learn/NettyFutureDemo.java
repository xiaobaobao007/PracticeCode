package per.bmy.NIO.Learn;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutorGroup;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by arthur.dy.lee on 2019-10-20.
 */
public class NettyFutureDemo {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);//创建执行线程池SingleThreadEventExecutor
        DefaultPromise<Integer> f = (DefaultPromise<Integer>) group.submit(new Callable<Integer>() { //创建Callable
            //方法即使抛出了异常也需要再get得到结果才能获得异常结果
            @Override
            public Integer call() throws Exception {
                System.out.println("执行耗时操作...");
                timeConsumingOperation();
                return 100;
            }
        });

        Channel channel = new NioServerSocketChannel();
        ChannelPromise channelPromise = channel.voidPromise();

//		f.setSuccess(666)

        try {
            System.out.println(f.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //传统获得结果
//		try {
//			System.out.println(f.get(1000, TimeUnit.MILLISECONDS));
//		} catch (ExecutionException | TimeoutException e) {
//			e.printStackTrace();
//		}

        //添加匿名监听者
//		f.addListener((FutureListener<Object>) objectFuture -> {
//			System.out.println("计算结果:：" + objectFuture.get()); //promise.get获取集果
//		});
    }

    static void timeConsumingOperation() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
