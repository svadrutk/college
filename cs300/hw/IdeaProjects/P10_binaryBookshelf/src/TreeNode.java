public class TreeNode<T> extends Object {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode (T data) {
        this.data = data;
    }
    public TreeNode (T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public TreeNode<T> getLeft() {
        return this.left;
    }
    public TreeNode<T> getRight() {
        return this.right;
    }
    public T getData() {
        return this.data;
    }
    public void setLeft(TreeNode<T> left) { this.left = left; }
    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
    public String toString() {
        return getData().toString();
    }
}
