package mazePD;

public class LinkedStack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	public LinkedStack() {}
	public int size() {return list.getSize();}
	public boolean isEmpty() {return list.isEmpty();}
	public void push(E element) {list.addFirst(element);}
	public E top() {return list.first();}
	public E pop() {return list.removeFirst();}
	
}