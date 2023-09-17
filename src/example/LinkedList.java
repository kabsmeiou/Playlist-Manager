package example;

//custom linked list class instead of the linked list collection in java.util
//This is specifically designed for both Playlists and Songs.
//This uses generics for minimal code duplication/redundancy
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    public LinkedList() {
        this.head = null;
    }

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    public void setHead(Node<T> newHead) {
        this.head = newHead;
    }

    public void setTail(Node<T> newTail) {
        this.tail = newTail;
    }

    public void insert(T data) {

        Node<T> newNode = new Node<>(data);

        if (head == null) {

            this.head = newNode;

        } else {

            tail.setNext(newNode);

        }

        this.tail = newNode;
    }

    public void remove(Node<T> nodeToRemove) {

        if (nodeToRemove == null || this.head == null) { //when our linked list is empty

            return;

        }

        if (this.head == nodeToRemove) {        //if head is the node to be removed then simply set the next node to be the head

            this.head = this.head.getNext();
            return;

        }

        Node<T> current = this.head;
        Node<T> previous = null;        //we have to track the previous node because the previous node's next() will be responsible for
                                        //the deletion of the current node that we're in
        while (current != null) {

            if (current == nodeToRemove) {

                previous.setNext(current.getNext());
                return;

            }

            previous = current;
            current = current.getNext();

        }
    }

    public Node<T> find(int index, LinkedList<T> mainList) { //method to find the node at index i

        int iterator = 1;
        Node<T> playlistNode = mainList.getHead();

        while(playlistNode != null) {

            if (iterator == index) {

                return playlistNode;

            }

            playlistNode = playlistNode.getNext();
            iterator++;
        }

        return null;
    }

    public void display() {

        Node<T> current = head;
        int index = 1;

        while (current != null) {

            System.out.println(index + ". " + current.getData());
            current = current.getNext();
            index++;

        }
    }
}
