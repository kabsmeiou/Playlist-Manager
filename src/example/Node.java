package example;


//A node class that can accept both Song and Playlist object
//using generics (<T>). This will allow us to lessen code duplicates
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {

        this.data = data;
        this.next = null;

    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

