package ThreadProblem;

public class Main {
    public static void main(String[] args) {
        thread t = new thread("1");
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
