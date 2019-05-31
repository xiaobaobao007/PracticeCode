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

    @Override
    public void inTe() {

    }
}
