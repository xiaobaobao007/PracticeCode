package ThreadProblem;

public class Main {
    public static void main(String[] args) {
        threadFactory factory = new threadFactory();
        factory.newThread(new thread("1"));
    }
}
