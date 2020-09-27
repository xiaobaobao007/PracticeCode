package AAA_doing;

/**
 * @author xiaobaobao
 * @date 2020/9/27，19:13
 * <p>
 *
 * <pre>
 *     		        		1
 *     		 21		              		22
 *     31	        32          33             34
 *  41    42
 * </pre>
 */
public class IsSubStructure {

	public static void main(String[] args) {
		TreeNode A = new TreeNode(1);
		A.left = new TreeNode(21);
		A.right = new TreeNode(22);
		A.left.left = new TreeNode(31);
		A.left.right = new TreeNode(32);
		A.right.left = new TreeNode(33);
		A.right.right = new TreeNode(34);
		A.left.left.left = new TreeNode(41);
		A.left.left.right = new TreeNode(42);

		TreeNode B = new TreeNode(22);
		B.left = new TreeNode(34);
		// B.right = new TreeNode(9);

		System.out.println(new IsSubStructure().isSubStructure(A, B));
	}

	public boolean isSubStructure(TreeNode A, TreeNode B) {
		if (A == null || B == null) {
			return false;
		}
		return is(A, B);
	}

	public boolean is(TreeNode A, TreeNode B) {
		if (isSubStructureMy(A, B)) {
			return true;
		}
		return (A.left != null && is(A.left, B)) || (A.right != null && is(A.right, B));
	}


	public boolean isSubStructureMy(TreeNode A, TreeNode B) {
		if (A == null) {
			return B == null;
		} else if (B == null) {
			return true;
		} else if (A.val != B.val) {
			return false;
		}
		return isSubStructureMy(A.left, B.left) && isSubStructureMy(A.right, B.right);
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}