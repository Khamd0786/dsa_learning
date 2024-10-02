package tree;

public class PathQuestions {

    public static void main(String[] args) {

    }

    public static boolean isPathExist(TreeNode root, int[] path) {
        return isPathExist(root, path, 0);
    }

    private static boolean isPathExist(TreeNode node, int[] path, int index) {
        if (node == null) {
            return false;
        }

        if (index >= path.length || node.val != path[index]) {
            return false;
        }

        if (node.left == null && node.right == null && index == path.length - 1) {
            return true;
        }
 
        boolean left = isPathExist(node.left, path, index + 1);
        boolean right = isPathExist(node.right, path, index + 1);

        return left || right;
    }


    private class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
