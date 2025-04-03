package per.bmy;

public class Front_Middle_Behind {

    class TreeNode {
        int data;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
    }

    //前序遍历递归算法
    void pre_order(TreeNode Node) {
        if (Node == null) return;
        System.out.println(Node.data);
        pre_order(Node.left);
        pre_order(Node.right);
    }

    //中序遍历递归算法
    void middle_order(TreeNode Node) {
        if (Node == null) return;
        middle_order(Node.left);
        System.out.println(Node.data);
        middle_order(Node.right);
    }

    //后序遍历递归算法
    void post_order(TreeNode Node) {
        if (Node == null) return;
        post_order(Node.left);
        post_order(Node.right);
        System.out.println(Node.data);
    }
}
