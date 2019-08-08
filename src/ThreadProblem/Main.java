package ThreadProblem;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
public class Main {
    //        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static ExecutorService executorService = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //        threadFactory factory = new threadFactory();
//        factory.newThread(new thread("1"));

//        Future future = executorService.submit(new Callable() {
//            public Object call() throws Exception {
//                System.out.println("Starting");
//                Thread.sleep(1000);
//                System.out.println("Ending");
//                return "Callable Result";
//            }
//        });
        System.out.println(-1 << 29);
        Future future = executorService.submit(() -> {
            System.out.println("Starting");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ending");
        });
        System.out.println(future.isCancelled());
//        Set<Callable<String>> callables = new HashSet<Callable<String>>();
//
//        callables.add(new Callable<String>() {
//            public String call() throws Exception {
//                Thread.sleep(700);
//                return "Task 1";
//            }
//        });
//        callables.add(() -> {
//            Thread.sleep(800);
//            return "Task 2";
//        });
//        callables.add(new Callable<String>() {
//            public String call() throws Exception {
//                Thread.sleep(900);
//                return "Task 3";
//            }
//        });
//
//        String result = executorService.invokeAny(callables);
//
//        System.out.println("result = " + result);
//
        executorService.shutdown();
    }
}
