package per.bmy.ThreadProblem.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xiaobaobao
 * @date 2020/5/26，21:46
 */
public class LockDemoTest implements Lock {

    private TestQueuedSync sync;

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        sync.acquire(1);
    }

    /**
     * 可中断的获取锁
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    /**
     * 尝试非阻塞式获取锁
     */
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    /**
     * 尝试非阻塞式获取锁
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquire(1);
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    /**
     * 是否有同步队列线程
     */
    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    /**
     * 锁是否被占用
     */
    public boolean isLock() {
        return sync.isHeldExclusively();
    }

    private static class TestQueuedSync extends AbstractQueuedSynchronizer {
        /**
         * 独占式获取同步状态
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 独占式释放同步状态
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 同步状态是否被占用
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 返回一个Condition，每个condition都包含了一个condition队列
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }
}