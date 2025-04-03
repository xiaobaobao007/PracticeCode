package per.bmy.ThreadProblem;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;


public class Main {
    //    public static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
//    public static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
//    private static ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(5);

    public static void tick() {
        System.out.println("我被执行了");
    }

    static void errorMethod() {
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println((System.currentTimeMillis() - L) / 100);
        try {
            throw new Exception("123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
//		service.execute(AttributeKeyTest::doqwe);
//		Future<String> submit = service.submit(new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				if (1 == 1) {
//					throw new Exception("666");
//				}
//				return "123";
//			}
//		});

        Future<String> submit = service.submit(Main::errorMethod, "123");

//		System.out.println("+++++++++");
        System.out.println(submit.get());


//        executorService.schedule(Main::tick, 10, TimeUnit.SECONDS);
//        int i = 10;
//        while (i >= -5) {
//            System.out.println(i--);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Future future = executorService.submit(new Callable() {
//            public Object call() throws Exception {
//                System.out.println("Starting");
//                Thread.sleep(1000);
//                System.out.println("Ending");
//                return "Callable Result";
//            }
//        });
        Map<Integer, Integer> map = new HashMap<>();

        Random random = new Random();
        executorService.execute(() -> {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = random.nextInt(100);
                map.put(i, i);
                System.out.println("++++++++=" + i + ":::" + map.size());
            }
        });

        executorService.execute(() ->
        {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int i = random.nextInt(100);
                map.remove(i);
                System.out.println("---------=" + i + ":::" + map.size());
            }
        });
        executorService.execute(() ->
        {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = 0;
                for (Integer value : map.values()) {
                    if (value != null) {
                        i++;
                    } else {
                        i++;
                    }
                }
                System.out.println("===========" + i);
            }
        });

//        Future future = executorService.submit(() -> {
//            System.out.println("Starting");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Ending");
//        });
//        System.out.println(future.isCancelled());
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

    static void forEach(Consumer<? super List> action) {
        Objects.requireNonNull(action);

    }

    static class Test {
        private int a;
        private int b;

        public Test(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
