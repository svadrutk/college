import com.sun.source.tree.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BinaryBookshelf {
    private TreeNode<Book> root;
    private int size;
    private Attribute[] sortList;

    public BinaryBookshelf(Attribute[] sortList) {
        if(sortList.length != 4 || sortList[0] != Attribute.AUTHOR) {
            throw new IllegalArgumentException("Input parameter is not a four-element array or does not start with Attribute.AUTHOR.");
        } else {
            this.sortList = sortList;
            this.size = 0;
        }
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public String getAttributeOrder() {
        String output = "";
        for(int i = 1; i < 5; i++) {
            output = output + i + ":" + sortList[i-1].toString() + " ";
        }
        return output;
    }
    public boolean contains(Book book) {
        if(book.equals(root)) {
            return true;
        }
        else {
            return containsHelper(book, root);
        }
    }
    protected boolean containsHelper(Book book, TreeNode<Book> current) {
        Attribute compare = null;
        for(int i = 0; i < sortList.length; i++) {
            if(book.equals(current)) {
                return true;
            }
            else if( book.compareTo(current.getData(), sortList[i]) != 0) {
                compare = sortList[i];
                break;
            }
        }
        assert compare != null;
        if(book.compareTo(current.getData(), compare) < 0 && current.getLeft() != null) {
            containsHelper(book, current.getLeft());
        } else if(book.compareTo(current.getData(), compare) > 0 && current.getRight() != null) {
            containsHelper(book, current.getRight());
        }
        return false;
    }
    public ArrayList<Book> getBooksByAuthor(String authorName) {

        if(root.getLeft() != null) {
            getBooksByAuthorHelper(authorName, root.getLeft());
        }
        return books;
    }
    protected ArrayList<Book> getBooksByAuthorHelper(String authorName,
        TreeNode<Book> current) {

        ArrayList<Book> books = new ArrayList<>();
        if(root.getData().getAuthor().equals(authorName)) {
            books.add(root.getData());
        }

    }
}
