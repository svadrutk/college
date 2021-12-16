public class BinaryBookshelfTester {
    public static void main(String[] args) {
        Attribute[] attributes = new Attribute[]{Attribute.AUTHOR, Attribute.ID, Attribute.TITLE, Attribute.PAGECOUNT};
        BinaryBookshelf bookshelf = new BinaryBookshelf(attributes);
        System.out.println(bookshelf.getAttributeOrder());
    }
}
