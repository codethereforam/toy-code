package priv.thinkam.toycode.algorithm.old.linklist;

public class Linklist {
	private Node first;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insertFirst(int d) {
		Node node = new Node(d);
		node.setNext(first);
		first = node;
	}
	
	public Node deleteFirst() {
		Node temp = first;
		first = first.getNext();
		return temp;
	}
	
	public void display() {
		Node l = first;
		while(l != null) {
			l.display();
			System.out.print(" ");
			l = l.getNext();
		}
	}
}
