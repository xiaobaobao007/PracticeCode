package ClassProblem;

public class ChildrenTest extends AbstractTest implements InterfaceTest{
    static {
        System.out.print("1b");
    }

    public ChildrenTest() {
        System.out.print("2b");
    }

    @Override
    public void abCon() {
        System.out.print("3b");
    }

//    @Override
//    public void inTe() {
//        System.out.println("--------------1");
//    }
//
//    @Override
//    public void inTT() {
//        System.out.println("--------------2");
//    }

    public static void main(String[] args) {
        new ChildrenTest().inTe();
        new ChildrenTest().inTT();
    }
}
