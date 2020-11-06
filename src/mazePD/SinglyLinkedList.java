package mazePD;



public class SinglyLinkedList<E> {
	
	//Nested Class
	private static class Node <E>{
		private E element;
		private Node <E> next;
		
		public Node(E e, Node<E> n)
		{
			element = e;
			next = n;
		}
		
		public E getElement()
		{
			return element;
		}
		public <E> Node getNext()
		{
			return next;
		}
		
		public void setNext(Node<E> n)
		{
			next = n;
		}
		
	}
	//end of the node nested class
	

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	
	public SinglyLinkedList()
	{	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public E first()
	{
		if (isEmpty())
		{
			return null;
		}
		else
			return head.getElement();
	}
	
	public E last()
	{
		if (isEmpty())
		{
			return null;
		}
		else
			return tail.getElement();
	}
	
	public void addFirst(E e)
	{
		 head = new Node<>(e, head);
		if (size == 0)
		{
			tail = head;
		}
		size++;
		 
	}
	
	public void addLast(E e)
	{
		Node <E> newest = new Node<>(e, null);
		
		if (isEmpty())
		{
			head = newest;
		}
		else
			tail.setNext(newest);
		tail = newest;
		size++;
			
	}
	public E removeFirst() {
		if (isEmpty()) { return null;}
		
		E answer = head.getElement();
		head = head.getNext();
		size--;
		
		if (size == 0)
		{
			tail = null;
			
		}
	
		return answer;
	}
		
	
	
	
	
	
}

