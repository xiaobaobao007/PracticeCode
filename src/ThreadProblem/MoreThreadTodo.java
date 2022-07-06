package ThreadProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @descript: 任务列表
 * @author: baomengyang <baomengyang@sina.cn>
 * @create: 2022-07-06 13:53
 */
public class MoreThreadTodo {

    private List<TaskAndResult> urlList;
    private CountDownLatch countDownLatch;

    /**
     * 添加任务
     *
     * @param url
     */
    public void addUrl(String url) {
        if (urlList == null) {
            urlList = new LinkedList<>();
        }
        urlList.add(new TaskAndResult(url));
    }

    /**
     * @param executorService 最好指定全局的线程池
     * @return 返回处理的结果和响应的结果
     * @throws InterruptedException
     */
    public List<TaskAndResult> todo(ExecutorService executorService) throws InterruptedException {
        if (urlList == null) {
            throw new NullPointerException("任务列表为空");
        }

        countDownLatch = new CountDownLatch(urlList.size());

        for (TaskAndResult taskAndResult : urlList) {
            executorService.submit(() -> {
                taskAndResult.result = http(taskAndResult.url);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        return urlList;
    }

    Random random = new Random();

    public Object http(String url) {
        //模拟延迟
        int delayTime = random.nextInt(5) + 1;
        try {
            Thread.sleep(delayTime * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return String.format("我是地址%s延迟%d秒后模拟处理的结果", url, delayTime);
    }

    public static class TaskAndResult {
        //请求地址
        private final String url;
        //请求结果
        private Object result;

        public TaskAndResult(String url) {
            this.url = url;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        MoreThreadTodo task = new MoreThreadTodo();
        task.addUrl("-1-");
        task.addUrl("-2-");
        task.addUrl("-3-");
        task.addUrl("-4-");
        task.addUrl("-5-");

        List<TaskAndResult> taskAndResults;
        long start = System.currentTimeMillis();
        try {
            taskAndResults = task.todo(executorService);
        } catch (InterruptedException e) {
            //todo 异常处理
            return;
        }

        System.out.printf("总耗时：%dms\n", System.currentTimeMillis() - start);

        for (TaskAndResult result : taskAndResults) {
            System.out.println(result.result);
        }

        executorService.shutdownNow();
    }

}
